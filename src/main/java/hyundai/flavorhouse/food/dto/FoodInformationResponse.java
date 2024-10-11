package hyundai.flavorhouse.food.dto;

import hyundai.flavorhouse.food.entity.Food;
import hyundai.flavorhouse.menu.dto.MenuDto;
import hyundai.flavorhouse.menu.entity.Menu;
import java.util.List;
import java.util.stream.Collectors;

public record FoodInformationResponse(
        Long id,
        String name,
        String address,
        List<MenuDto> menus
) {

    public static FoodInformationResponse fromEntity(Food food, List<Menu> menus) {
        // Menu 리스트를 MenuDto 리스트로 변환
        List<MenuDto> menuDtos = menus.stream()
                .map(MenuDto::fromEntity)
                .collect(Collectors.toList());

        // FoodInformationResponse 객체 생성
        return new FoodInformationResponse(
                food.getId(),
                food.getName(),
                food.getAddress(),
                menuDtos
        );
    }
}
