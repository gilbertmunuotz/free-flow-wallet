package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.*;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.mappers.UserMapper;
import free_flow_wallet.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserDto> getUserByEmail(Authentication authentication) {
        String email = authentication.getName(); // from JWT "sub"

        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PostMapping("/update")
    public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto, Authentication authentication) {
        String email = authentication.getName(); // extracted from JWT
        User updateUser = userService.updateUserInfo(email, userUpdateRequestDto);
        return ResponseEntity.ok(UserMapper.toDto(updateUser));
    }
}