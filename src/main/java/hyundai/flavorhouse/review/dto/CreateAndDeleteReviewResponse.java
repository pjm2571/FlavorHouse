package hyundai.flavorhouse.review.dto;

public record CreateAndDeleteReviewResponse(
        String message

) {

    public static CreateAndDeleteReviewResponse of(String message) {
        return new CreateAndDeleteReviewResponse(message);
    }
}
