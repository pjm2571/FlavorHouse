package hyundai.flavorhouse.review.controller;

import hyundai.flavorhouse.review.dto.CreateReviewRequest;
import hyundai.flavorhouse.review.dto.CreateAndDeleteReviewResponse;
import hyundai.flavorhouse.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/review")
    public ResponseEntity<?> createReview(
            @RequestBody CreateReviewRequest request
    ) {
        reviewService.createReview(request);

        return ResponseEntity.ok(CreateAndDeleteReviewResponse.of("리뷰 생성 완료"));
    }

    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long reviewId
    ) {
        reviewService.deleteReview(reviewId);

        return ResponseEntity.ok(CreateAndDeleteReviewResponse.of("리뷰 삭제 완료"));
    }

    @GetMapping("/food/{foodId}/reviews")
    public ResponseEntity<?> getAllReviews(
            @PathVariable Long foodId,
            @RequestParam Integer offset,
            @RequestParam Integer limit
    ) {
        return ResponseEntity.ok(
                reviewService.getAllReviews(foodId, PageRequest.of(offset, limit)));
    }

}
