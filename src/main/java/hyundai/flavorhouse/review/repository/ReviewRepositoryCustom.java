package hyundai.flavorhouse.review.repository;

import hyundai.flavorhouse.review.entity.Review;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewRepositoryCustom {
    Slice<Review> findSliceByFoodId(Long foodId, Pageable pageable);

}
