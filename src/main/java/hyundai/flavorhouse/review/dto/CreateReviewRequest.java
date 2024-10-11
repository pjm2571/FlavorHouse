package hyundai.flavorhouse.review.dto;

public record CreateReviewRequest(
        Long foodId,
        String content,
        Double score
) {

}
