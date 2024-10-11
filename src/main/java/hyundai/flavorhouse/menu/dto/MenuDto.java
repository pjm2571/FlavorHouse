package hyundai.flavorhouse.menu.dto;

import hyundai.flavorhouse.menu.entity.Menu;

public record MenuDto(
        String name,
        Integer price
) {

    public static MenuDto fromEntity(Menu menu) {
        return new MenuDto(
                menu.getName(),
                menu.getPrice()
        );
    }
}
