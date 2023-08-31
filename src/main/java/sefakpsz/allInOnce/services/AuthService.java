package sefakpsz.allInOnce.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import sefakpsz.allInOnce.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sefakpsz.allInOnce.daos.Auth.AuthLoginDao;
import sefakpsz.allInOnce.daos.Auth.AuthRegisterDao;
import sefakpsz.allInOnce.daos.Auth.AuthResponseDao;
import sefakpsz.allInOnce.enums.User.Role;
import sefakpsz.allInOnce.repositories.UserRepository;
import sefakpsz.allInOnce.utils.jwt.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDao signUp(AuthRegisterDao request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDao.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponseDao signIn(AuthLoginDao request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);

        return AuthResponseDao.builder()
                .token(jwtToken)
                .build();
    }
}

