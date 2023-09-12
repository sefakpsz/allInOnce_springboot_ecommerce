package sefakpsz.allInOnce.daos.Auth;

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
public class AuthRegisterDao {
    @MyNotBlank
    private String firstname;

    @MyNotBlank
    private String lastname;

    @Email
    private String email;

    @MyNotBlank
    private String password;
}
