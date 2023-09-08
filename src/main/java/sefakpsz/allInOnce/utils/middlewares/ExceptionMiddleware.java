package sefakpsz.allInOnce.utils.middlewares;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sefakpsz.allInOnce.utils.constants.messages;
import sefakpsz.allInOnce.utils.results.ErrorResult;
import sefakpsz.allInOnce.utils.results.Result;

//@ControllerAdvice
@RestControllerAdvice
public class ExceptionMiddleware {

    @ExceptionHandler(value = {RuntimeException.class, VirtualMachineError.class})
    public ResponseEntity<Result> handleRuntimeException() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ErrorResult(messages.error));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResult(messages.wrong_password));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(EntityNotFoundException ex) {
        var entityName = ex.getMessage().split(" ")[3].split("\\.")[3];

        return ResponseEntity.status(HttpStatus.OK).body(
                new ErrorResult(entityName + " Couldn't Found!"));
    }
}



