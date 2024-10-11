package hyundai.flavorhouse.food.dto;

import hyundai.flavorhouse.menu.dto.MenuDto;
import java.util.List;

public record CreateAndEditFoodRequest(
        String name,
        String address,
        List<MenuDto> menus
) {

}
