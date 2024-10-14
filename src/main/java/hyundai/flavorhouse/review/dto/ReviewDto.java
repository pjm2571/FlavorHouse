package hyundai.flavorhouse.review.dto;

import hyundai.flavorhouse.menu.dto.MenuDto;
import hyundai.flavorhouse.review.entity.Review;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Slice;

public record ReviewDto(
        Double avgScore,
        Slice<Review> reviews,
        ReviewDtoPage page
) {

    public static ReviewDto of(Double avgScore, Slice<Review> reviews, ReviewDtoPage reviewDtoPage) {
        return new ReviewDto(
                avgScore,
                reviews,
                reviewDtoPage
        );
    }

}
