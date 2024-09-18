package uz.pdp.SimpleLoginProject.controllers;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SimpleLoginProject.dtos.ErrorBodyDto;
import uz.pdp.SimpleLoginProject.dtos.LoginDto;
import uz.pdp.SimpleLoginProject.dtos.UserCreateDto;
import uz.pdp.SimpleLoginProject.exceptions.UsernameOrPasswordWrong;
import uz.pdp.SimpleLoginProject.model.User;
import uz.pdp.SimpleLoginProject.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User register(@RequestBody UserCreateDto userDto) {
        User newUser = User.builder()
                .fullName(userDto.fullName())
                .username(userDto.username())
                .password(userDto.password())
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
