package hyundai.flavorhouse.menu.repository;

import hyundai.flavorhouse.menu.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
