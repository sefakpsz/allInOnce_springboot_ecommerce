package sefakpsz.allInOnce.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.User.UserChangePasswordDao;

import sefakpsz.allInOnce.daos.User.UserDao;
import sefakpsz.allInOnce.entities.User;
import sefakpsz.allInOnce.repositories.UserRepository;
import sefakpsz.allInOnce.utils.constants.messages;
import sefakpsz.allInOnce.utils.functions.GettingUser;
import sefakpsz.allInOnce.utils.jwt.JwtService;
import sefakpsz.allInOnce.utils.results.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public Result ChangePassword(UserChangePasswordDao userChangePasswordDao) {
        var user = GettingUser.Get();

        boolean isPasswordCorrect = passwordEncoder.matches(userChangePasswordDao.getOldPassword(), user.getPassword());

        if (!isPasswordCorrect)
            return new ErrorResult(messages.wrong_password);

        String encodedNewPassword = passwordEncoder.encode(userChangePasswordDao.getNewPassword());

        user.setPassword(encodedNewPassword);

        repository.save(user);

        return new SuccessResult(messages.success);
    }

    public DataResult<UserDao> GetMyInfo() {
        var user = GettingUser.Get();

        var userDao = new UserDao();
        userDao.setId(user.getId());
        userDao.setFirstname(user.getFirstname());
        userDao.setLastname(user.getLastname());
        userDao.setEmail(user.getEmail());
        userDao.setRole(user.getRole());

        return new SuccessDataResult(userDao, messages.success);
    }

    public Result Delete(Integer userId) {
        var user = repository.findUserById(userId);
        if (user == null)
            return new ErrorResult(messages.user_not_found);

        repository.delete(user);

        return new SuccessResult(messages.success);
    }
}
