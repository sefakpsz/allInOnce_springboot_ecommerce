package sefakpsz.allInOnce.dtos.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.utils.validations.MyNotBlank.MyNotBlank;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryCreateDto {
    @MyNotBlank
    private String title;

    @MyNotBlank
    private String imageURL;
}
