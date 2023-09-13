package sefakpsz.allInOnce.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.utils.validations.MyNotBlank.MyNotBlank;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryUpdateDto {

    @NotNull
    private Integer categoryId;

    @NotBlank
    private String title;

    @MyNotBlank
    private String imageURL;
}
