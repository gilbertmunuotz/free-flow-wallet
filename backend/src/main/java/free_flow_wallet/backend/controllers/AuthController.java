package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.*;
import free_flow_wallet.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody RegisterRequestDto registerRequestDto) {
        try {
            UserDto savedUser = userService.registerUser(registerRequestDto);
            return ResponseEntity.ok(savedUser);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Email already exists! Please use another email.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@Valid @RequestBody AuthRequestDto authRequestDto) {
        LoginResponseDto loggedInUser = userService.loginUser(authRequestDto);
        return ResponseEntity.ok(loggedInUser);
    }
}