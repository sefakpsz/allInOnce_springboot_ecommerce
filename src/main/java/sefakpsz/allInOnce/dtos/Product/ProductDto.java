package sefakpsz.allInOnce.dtos.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.dtos.Category.CategoryDto;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Integer id;
    private String title;
    private Float price;
    private String imageURL;
    private CategoryDto category;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
