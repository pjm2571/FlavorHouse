package hyundai.flavorhouse.review.entity;

import hyundai.flavorhouse.base.entity.BaseEntity;
import hyundai.flavorhouse.review.dto.CreateReviewRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "food_id")
    private Long foodId;
    private String content;
    private Double score;

    public static Review createFromRequest(CreateReviewRequest request) {
        return Review.builder()
                .foodId(request.foodId())
                .content(request.content())
                .score(request.score())
                .build();
    }
}
