package sefakpsz.allInOnce.daos.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserChangePasswordDao {
    private String oldPassword;
    private String newPassword;
}
