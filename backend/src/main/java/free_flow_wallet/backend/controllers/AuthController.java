package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.*;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.mappers.UserMapper;
import free_flow_wallet.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto, Authentication authentication) {
        String email = authentication.getName(); // extracted from JWT
        User updateUser = userService.updateUserInfo(email, userUpdateRequestDto);
        return ResponseEntity.ok(UserMapper.toDto(updateUser));
    }
}