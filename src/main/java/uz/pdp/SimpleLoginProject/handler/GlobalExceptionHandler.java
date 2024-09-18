package uz.pdp.SimpleLoginProject.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.SimpleLoginProject.dtos.ErrorBodyDto;
import uz.pdp.SimpleLoginProject.exceptions.UsernameOrPasswordWrong;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UsernameOrPasswordWrong.class, RuntimeException.class})
    public ErrorBodyDto AllExceptionBody(HttpServletRequest request, UsernameOrPasswordWrong exception){
        return new ErrorBodyDto(
                exception.getStatus().value(),
                request.getRequestURI(),
                request.getRequestURL().toString(),
                exception.getClass().toString(),
                exception.getMessage(),
                LocalDateTime.now()
        );
    }
}
