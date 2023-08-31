package sefakpsz.allInOnce.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import sefakpsz.allInOnce.enums.User.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table()
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
