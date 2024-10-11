package hyundai.flavorhouse.menu.repository;

import hyundai.flavorhouse.menu.entity.Menu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findAllByFoodId(Long foodId);

    void deleteAllByFoodId(Long foodId);
}
