package sefakpsz.allInOnce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(mappedBy = "products")
    List<Order> orders;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime modifiedDate = LocalDateTime.now();
}
