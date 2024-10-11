package hyundai.flavorhouse.food.service;

import hyundai.flavorhouse.food.dto.CreateAndEditFoodRequest;
import hyundai.flavorhouse.food.dto.FoodResponse;
import hyundai.flavorhouse.food.entity.Food;
import hyundai.flavorhouse.food.repository.FoodRepository;
import hyundai.flavorhouse.menu.dto.MenuDto;
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

    @Transactional
    public FoodResponse postFood(CreateAndEditFoodRequest request) {
        // Request로 가져온 값을 통해 Food Entity 저장
        Food savedFood = foodRepository.save(Food.createFromRequest(request));

        // Food Entity를 저장한 후, 할당받은 food Id를 Menu Entity에 넣어서 생성
        List<Menu> menus = request.menus().stream()
                .map(menuDto -> Menu.createFromRequest(savedFood.getId(), menuDto))
                .toList();

        // Menu Entity 들을 repository에 저장
        List<Menu> savedMenus = menuRepository.saveAll(menus);

        // 저장된 Food Entity, Menu Entities 를 바탕으로 response dto 생성
        return FoodResponse.of(savedFood, savedMenus);
    }
}
