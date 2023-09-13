package sefakpsz.allInOnce.dtos.Auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.utils.validations.MyNotBlank.MyNotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthLoginDto {
    @Email
    private String email;

    @MyNotBlank
    private String password;
}
