package hyundai.flavorhouse.food.dto;

import hyundai.flavorhouse.food.entity.Food;
import java.time.LocalDateTime;

public record FoodViewResponse(
        Long id,
        String name,
        String address,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static FoodViewResponse of(Food food) {
        return new FoodViewResponse(
                food.getId(),
                food.getName(),
                food.getAddress(),
                food.getCreatedAt(),
                food.getUpdatedAt()
        );
    }
}
