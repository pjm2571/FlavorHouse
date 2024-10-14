package hyundai.flavorhouse.review.dto;

public record ReviewDtoPage(
        Integer offset,
        Integer limit
) {

    public static ReviewDtoPage of(Integer offset, Integer limit) {
        return new ReviewDtoPage(offset, limit);
    }
}
