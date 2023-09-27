package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import sefakpsz.allInOnce.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.dtos.Auth.AuthLoginDto;
import sefakpsz.allInOnce.dtos.Auth.AuthRegisterDto;
import sefakpsz.allInOnce.dtos.Auth.AuthResponseDto;
import sefakpsz.allInOnce.enums.User.Role;
import sefakpsz.allInOnce.repositories.UserRepository;
import sefakpsz.allInOnce.utils.constants.Messages;
import sefakpsz.allInOnce.utils.jwt.JwtService;
import sefakpsz.allInOnce.utils.results.DataResult;
import sefakpsz.allInOnce.utils.results.ErrorDataResult;
import sefakpsz.allInOnce.utils.results.SuccessDataResult;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public DataResult<AuthResponseDto> signUp(AuthRegisterDto request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return new SuccessDataResult<AuthResponseDto>(AuthResponseDto.builder()
                .token(jwtToken)
                .build(), Messages.success.toString());
    }

    public DataResult<AuthResponseDto> signIn(AuthLoginDto request) {
        var user = repository.findByEmail(request.getEmail());

        if (user.toString().equals("Optional.empty"))
            return new ErrorDataResult<AuthResponseDto>(null, Messages.email_not_found.toString());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user.orElseThrow());

        return new SuccessDataResult<AuthResponseDto>(AuthResponseDto.builder()
                .token(jwtToken)
                .build(), Messages.success.toString());
    }
}

