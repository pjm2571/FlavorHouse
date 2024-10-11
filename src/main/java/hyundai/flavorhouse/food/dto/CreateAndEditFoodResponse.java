package hyundai.flavorhouse.food.dto;

import hyundai.flavorhouse.food.entity.Food;
import hyundai.flavorhouse.menu.dto.MenuDto;
import hyundai.flavorhouse.menu.entity.Menu;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record CreateAndEditFoodResponse(
        Long id,
        String name,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<MenuDto> menus
) {

    public static CreateAndEditFoodResponse of(Food food, List<Menu> menus) {
        List<MenuDto> menuDtos = menus.stream()
                .map(MenuDto::fromEntity) // DTO 변환 책임을 DTO에게 위임
                .collect(Collectors.toList());

        return new CreateAndEditFoodResponse(
                food.getId(),
                food.getName(),
                food.getAddress(),
                food.getCreatedAt(),
                food.getUpdatedAt(),
                menuDtos
        );
    }

}
