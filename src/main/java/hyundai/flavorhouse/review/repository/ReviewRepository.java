package hyundai.flavorhouse.review.repository;

import hyundai.flavorhouse.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewRepositoryCustom {

}
