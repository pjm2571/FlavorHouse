package hyundai.flavorhouse.menu.entity;

import hyundai.flavorhouse.base.entity.BaseEntity;
import hyundai.flavorhouse.menu.dto.MenuDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Menu extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "food_id")
    private Long foodId;
    private String name;
    private Integer price;

    public static Menu createFromRequest(Long foodId, MenuDto request) {
        return Menu.builder()
                .foodId(foodId)
                .name(request.name())
                .price(request.price())
                .build();
    }

}
