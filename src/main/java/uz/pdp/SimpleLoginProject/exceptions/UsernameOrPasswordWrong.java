package uz.pdp.SimpleLoginProject.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "User not found")
public class UsernameOrPasswordWrong extends RuntimeException {

    private final HttpStatus status;
    public UsernameOrPasswordWrong(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
