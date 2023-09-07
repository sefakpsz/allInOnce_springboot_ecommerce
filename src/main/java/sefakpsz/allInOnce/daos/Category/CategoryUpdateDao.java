package sefakpsz.allInOnce.daos.Category;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDao {
    private Integer categoryId;
    private String title;
    private String imageURL;
}
