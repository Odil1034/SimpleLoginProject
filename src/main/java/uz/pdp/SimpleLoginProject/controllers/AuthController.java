package uz.pdp.SimpleLoginProject.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SimpleLoginProject.dtos.LoginDto;
import uz.pdp.SimpleLoginProject.dtos.UserCreateDto;
import uz.pdp.SimpleLoginProject.exceptions.UsernameOrPasswordWrong;
import uz.pdp.SimpleLoginProject.model.User;
import uz.pdp.SimpleLoginProject.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserCreateDto userDto) {
        String encode = passwordEncoder.encode(userDto.password());
        User newUser = User.builder()
                .fullName(userDto.fullName())
                .username(userDto.username())
                .password(encode)
                .build();
        return userRepository.save(newUser);
    }

    @GetMapping("/login")
    public User login(@RequestBody LoginDto loginDto) {
        return userRepository.findFirstByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword())
                .orElseThrow(() -> new UsernameOrPasswordWrong(
                        "Username or password incorrect", HttpStatus.UNAUTHORIZED));
    }
}
