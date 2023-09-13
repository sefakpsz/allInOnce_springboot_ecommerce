package sefakpsz.allInOnce.dtos.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.dtos.Product.ProductDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private String title;
    private String imageURL;

    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
