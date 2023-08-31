package sefakpsz.allInOnce.daos.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterDao {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
}
