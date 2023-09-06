package sefakpsz.allInOnce.daos.User;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.enums.User.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
    private String firstname;
    private String lastname;
    private String email;
    private Role role;
}
