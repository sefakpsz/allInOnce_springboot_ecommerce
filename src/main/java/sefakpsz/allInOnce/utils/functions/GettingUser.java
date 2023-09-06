package sefakpsz.allInOnce.utils.functions;

import org.springframework.security.core.context.SecurityContextHolder;
import sefakpsz.allInOnce.entities.User;

public class GettingUser {

    public static User Get() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
