package sefakpsz.allInOnce.utils.middlewares;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sefakpsz.allInOnce.utils.constants.messages;
import sefakpsz.allInOnce.utils.results.ErrorResult;
import sefakpsz.allInOnce.utils.results.Result;

//@ControllerAdvice
@RestControllerAdvice
public class ExceptionMiddleware {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Result> handleExpiredJwtException(ExpiredJwtException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResult(messages.invalid_token));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResult(messages.wrong_password));
    }


}



