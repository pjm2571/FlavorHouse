package hyundai.flavorhouse.food.dto;

public record FoodDeleteResponse(
        String message
) {

    public static FoodDeleteResponse of(String message) {
        return new FoodDeleteResponse(message);
    }
}
