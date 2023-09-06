package sefakpsz.allInOnce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@Table(name = "product_orders")
@Embeddable
public class ProductOrders implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "product_id")
    Integer productId;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
