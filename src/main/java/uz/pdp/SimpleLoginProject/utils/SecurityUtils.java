package uz.pdp.SimpleLoginProject.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import uz.pdp.SimpleLoginProject.model.User;
import uz.pdp.SimpleLoginProject.repository.UserRepository;
import uz.pdp.SimpleLoginProject.service.CustomUserDetailsService;

import java.util.Random;

@Component
public class SecurityUtils {

    private final UserRepository userRepository;

    public SecurityUtils(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        return userRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
*/
   /* public int getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        User user = userRepository.findFirstByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        return user.getId();
    }*/

    // user exist
    public int getUserId() {
        return new Random().nextInt(100);
    }
}