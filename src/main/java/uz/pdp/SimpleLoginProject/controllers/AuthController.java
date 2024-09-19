package uz.pdp.SimpleLoginProject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uz.pdp.SimpleLoginProject.dtos.AuthenticationDto;
import uz.pdp.SimpleLoginProject.dtos.UserCreateDto;
import uz.pdp.SimpleLoginProject.model.User;
import uz.pdp.SimpleLoginProject.repository.UserRepository;
import uz.pdp.SimpleLoginProject.utils.JwtTokenUtils;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtils jwtTokenUtils;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtTokenUtils jwtTokenUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtils = jwtTokenUtils;
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

    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationDto> authentication(@RequestParam("username")String username, @RequestParam("password")String password){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        String accessToken = jwtTokenUtils.generateToken(username);
        return ResponseEntity.ok(new AuthenticationDto(accessToken));
    }
}
