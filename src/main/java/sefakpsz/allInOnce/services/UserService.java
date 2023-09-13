package sefakpsz.allInOnce.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.dtos.User.UserChangePasswordDto;

import sefakpsz.allInOnce.dtos.User.UserDto;
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

    public Result ChangePassword(UserChangePasswordDto userChangePasswordDto) {
        var user = GettingUser.Get();

        boolean isPasswordCorrect = passwordEncoder.matches(userChangePasswordDto.getOldPassword(), user.getPassword());

        if (!isPasswordCorrect)
            return new ErrorResult(messages.wrong_password);

        String encodedNewPassword = passwordEncoder.encode(userChangePasswordDto.getNewPassword());

        user.setPassword(encodedNewPassword);

        repository.save(user);

        return new SuccessResult(messages.success);
    }

    public DataResult<UserDto> GetMyInfo() {
        var user = GettingUser.Get();

        var userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());

        return new SuccessDataResult(userDto, messages.success);
    }

    public Result Delete(Integer userId) {
        var user = repository.findUserById(userId);
        if (user == null)
            return new ErrorResult(messages.user_not_found);

        repository.delete(user);

        return new SuccessResult(messages.success);
    }
}
