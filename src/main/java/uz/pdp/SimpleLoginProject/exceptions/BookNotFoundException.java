package uz.pdp.SimpleLoginProject.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BookNotFoundException extends RuntimeException {

    private final HttpStatus status;
    public BookNotFoundException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
