package sefakpsz.allInOnce.daos.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.daos.Product.ProductDao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDao {
    private Integer id;
    private String title;
    private String imageURL;
    private List<ProductDao> products = new ArrayList<>();

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
