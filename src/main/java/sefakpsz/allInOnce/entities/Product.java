package sefakpsz.allInOnce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue
    private Integer id;
    private String title;
    private Float price;
    private String imageURL;

    @ManyToOne(
            cascade = {
                    CascadeType.REFRESH
            })
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @ManyToMany(
            cascade = {
                    CascadeType.REMOVE
            },
            mappedBy = "products")
    Set<Order> orders;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
