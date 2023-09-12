package sefakpsz.allInOnce.daos.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.utils.validations.MyNotBlank.MyNotBlank;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateDao {
    @NotNull
    private Integer id;

    @MyNotBlank
    private String title;

    @NotNull
    private Float price;

    @MyNotBlank
    private String imageURL;

    @NotNull
    private Integer categoryId;
}
