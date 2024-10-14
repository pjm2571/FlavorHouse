package hyundai.flavorhouse.review.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hyundai.flavorhouse.review.entity.QReview;
import hyundai.flavorhouse.review.entity.Review;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Slice<Review> findSliceByFoodId(Long foodId, Pageable pageable) {
        QReview review = QReview.review;

        List<Review> reviews = jpaQueryFactory.select(review)
                .from(review)
                .where(review.foodId.eq(foodId))
                .offset((long) pageable.getPageNumber() * pageable.getPageSize())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return new SliceImpl<>(
                reviews.stream().limit(pageable.getPageSize()).toList(),
                pageable,
                reviews.size() > pageable.getPageSize() // hasNext
        );
    }

    @Override
    public Double getAvgScoreByFoodId(Long foodId) {
        QReview review = QReview.review;

        // QueryDSL을 사용한 평균 평점 계산
        Double averageScore = jpaQueryFactory
                .select(review.score.avg()) // score 컬럼의 평균값
                .from(review)
                .where(review.foodId.eq(foodId))
                .fetchOne(); // 단일 값 반환

        return averageScore != null ? averageScore : 0.0; // null이면 0.0 반환
    }
}
