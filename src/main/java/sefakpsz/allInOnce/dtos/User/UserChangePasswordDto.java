package sefakpsz.allInOnce.dtos.User;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.utils.validations.MyNotBlank.MyNotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordDto {
    @MyNotBlank
    private String oldPassword;

    @MyNotBlank
    private String newPassword;
}
