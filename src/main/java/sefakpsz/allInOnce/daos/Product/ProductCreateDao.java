package sefakpsz.allInOnce.daos.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDao {
    private String title;
    private Float price;
    private String imageURL;
    private Integer categoryId;
}
