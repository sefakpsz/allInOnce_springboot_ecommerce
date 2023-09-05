package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.User.UserChangePasswordDao;
import sefakpsz.allInOnce.repositories.UserRepository;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.ErrorDataResult;
import sefakpsz.allInOnce.utils.results.SuccessDataResult;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final Object authentication = SecurityContextHolder.getContext().getAuthentication();

    public DataResult ChangePassword(UserChangePasswordDao userChangePasswordDao) {
        if (authentication != null) {
            return new SuccessDataResult(authentication, "yes");

        }

        return new ErrorDataResult(null, "unauthorized");
    }
}
