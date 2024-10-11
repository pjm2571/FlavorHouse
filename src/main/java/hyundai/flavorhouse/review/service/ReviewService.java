package hyundai.flavorhouse.review.service;

import hyundai.flavorhouse.food.entity.Food;
import hyundai.flavorhouse.food.repository.FoodRepository;
import hyundai.flavorhouse.review.dto.CreateReviewRequest;
import hyundai.flavorhouse.review.entity.Review;
import hyundai.flavorhouse.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final FoodRepository foodRepository;
    private final ReviewRepository reviewRepository;

    public void createReview(CreateReviewRequest request) {
        // 전달받은 foodId에 대한 유효성 검증
        if (!foodRepository.existsById(request.foodId())) {
            throw new IllegalArgumentException("맛집 정보가 유효하지 않습니다.");
        }

        Review savedReview = reviewRepository.save(Review.createFromRequest(request));

        // 추가
    }

    public void deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(IllegalArgumentException::new);

        reviewRepository.delete(review);
    }
}
