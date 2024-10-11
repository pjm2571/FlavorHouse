package hyundai.flavorhouse.food.service;

import hyundai.flavorhouse.food.dto.CreateAndEditFoodRequest;
import hyundai.flavorhouse.food.dto.CreateAndEditFoodResponse;
import hyundai.flavorhouse.food.dto.FoodInformationResponse;
import hyundai.flavorhouse.food.entity.Food;
import hyundai.flavorhouse.food.repository.FoodRepository;
import hyundai.flavorhouse.menu.entity.Menu;
import hyundai.flavorhouse.menu.repository.MenuRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    private final MenuRepository menuRepository;

    @Transactional(readOnly = true)
    public List<FoodInformationResponse> getFoods() {
        // foodRepository에 있는 모든 food 엔티티 조회
        List<Food> foods = foodRepository.findAll();

        return foods.stream()
                .map(food -> {
                    // foodId에 해당하는 메뉴 리스트를 가져온다
                    List<Menu> menus = menuRepository.findAllByFoodId(food.getId());

                    // FoodInformationResponse의 정적 팩토리 메서드를 사용하여 객체 생성
                    return FoodInformationResponse.fromEntity(food, menus);

                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FoodInformationResponse getFoodById(Long foodId) {

        // findById 메소드로 엔티티 조회 -> 없는 경우 예외 발생시킴
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        List<Menu> menus = menuRepository.findAllByFoodId(foodId);

        return FoodInformationResponse.fromEntity(food, menus);
    }

    @Transactional
    public CreateAndEditFoodResponse postFood(CreateAndEditFoodRequest request) {
        // Request로 가져온 값을 통해 Food Entity 저장
        Food savedFood = foodRepository.save(Food.createFromRequest(request));

        // Food Entity를 저장한 후, 할당받은 food Id를 Menu Entity에 넣어서 생성
        List<Menu> menus = request.menus().stream()
                .map(menuDto -> Menu.createFromRequest(savedFood.getId(), menuDto))
                .toList();

        // Menu Entity 들을 repository에 저장
        List<Menu> savedMenus = menuRepository.saveAll(menus);

        // 저장된 Food Entity, Menu Entities 를 바탕으로 response dto 생성
        return CreateAndEditFoodResponse.of(savedFood, savedMenus);
    }

    @Transactional
    public CreateAndEditFoodResponse editFood(Long foodId, CreateAndEditFoodRequest request) {
        // 1) foodId에 해당하는 모든 menu들을 지움
        menuRepository.deleteAllByFoodId(foodId);

        // 2) foodId에 해당하는 food 엔티티 가져옴
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        // 3) food entity 수정
        food.changeNameAndAddress(request.name(), request.address());

        // 4) foodId에 해당하는 메뉴들을 생성
        List<Menu> menus = request.menus().stream()
                .map(menuDto -> Menu.createFromRequest(food.getId(), menuDto))
                .toList();

        // 5) Menu Entity 들을 repository에 저장
        List<Menu> savedMenus = menuRepository.saveAll(menus);

        // 6) food save
        Food savedFood = foodRepository.save(food);

        // 저장된 Food Entity, Menu Entities 를 바탕으로 response dto 생성
        return CreateAndEditFoodResponse.of(savedFood, savedMenus);
    }

    @Transactional
    public void deleteFood(Long foodId) {
        // 1) foodId에 해당하는 모든 menu들을 지움
        menuRepository.deleteAllByFoodId(foodId);

        // 2) foodId에 해당하는 food Entity 조회
        Food food = foodRepository.findById(foodId).orElseThrow(IllegalArgumentException::new);

        // 3) 해당 객체 삭제
        foodRepository.delete(food);
    }
}
