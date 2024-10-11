package hyundai.flavorhouse.food.entity;

import hyundai.flavorhouse.base.entity.BaseEntity;
import hyundai.flavorhouse.food.dto.CreateAndEditFoodRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "food")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    public void changeNameAndAddress(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public static Food createFromRequest(CreateAndEditFoodRequest request) {
        return Food.builder()
                .name(request.name())
                .address(request.address())
                .build();
    }
}
