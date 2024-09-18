package uz.pdp.SimpleLoginProject.utils;
/*
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;*/
import org.springframework.stereotype.Component;
import uz.pdp.SimpleLoginProject.model.User;

import java.util.Random;

@Component
public class SecurityUtils {
    // user exist

    public int getUser() {
        /*Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        return user.getId();*/
        return new Random().nextInt(100);
    }
}