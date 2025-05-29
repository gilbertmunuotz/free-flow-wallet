package free_flow_wallet.backend.services.impl;

import free_flow_wallet.backend.dtos.*;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.entities.Wallet;
import free_flow_wallet.backend.exceptions.EmailAlreadyExistsException;
import free_flow_wallet.backend.exceptions.EmailNotFoundException;
import free_flow_wallet.backend.exceptions.UserNotFoundException;
import free_flow_wallet.backend.exceptions.WrongCredentials;
import free_flow_wallet.backend.mappers.UserMapper;
import free_flow_wallet.backend.repositories.UserRepository;
import free_flow_wallet.backend.services.UserService;
import free_flow_wallet.backend.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // Interface to talk to DB and invoke all methods
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDto registerUser(RegisterRequestDto registerRequestDto) {

        // Check if email exists BEFORE saving
        if (userRepository.existsByEmail(registerRequestDto.getEmail())) {
            throw new EmailAlreadyExistsException("Email already exists! Please use another email.");
        }

        //  Convert DTO to Entity
        User user = UserMapper.toEntity(registerRequestDto);

        // 👉 Hash the password here
        String hashedPassword = passwordEncoder.encode(registerRequestDto.getPassword());
        user.setPassword(hashedPassword);

        // Create wallet and link to user
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(0.0));
        wallet.setUser(user); // set the user in wallet
        user.setWallet(wallet); // set the wallet in user (bidirectional)

        // Save Entity to DB
        User saveUser = userRepository.save(user);

        // Convert Entity back to DTO and return
        return UserMapper.toDto(saveUser);
    }

    @Override
    public LoginResponseDto loginUser(AuthRequestDto authRequestDto) {
        // Check first if email exists
        User user = userRepository.findByEmail(authRequestDto.getEmail())
                .orElseThrow(() -> new EmailNotFoundException("Email Not Found!"));

        //  Match password (hashed)
        if (!passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
            throw new WrongCredentials("Wrong Credentials");
        }

        // Issue JWT Token Here
        String token = JwtUtil.generateToken(user.getEmail());

        // Return basic user info
        return new LoginResponseDto(
                user.getId(),
                user.getEmail(),
                token
        );
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @Override
    public User updateUserInfo(String email, UserUpdateRequestDto updateDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        if (updateDto.getFullName() != null && !updateDto.getFullName().isBlank()) {
            user.setFullName(updateDto.getFullName());
        }

        if (updateDto.getEmail() != null && !updateDto.getEmail().isBlank() && !updateDto.getEmail().equals(user.getEmail())) {
            user.setEmail(updateDto.getEmail());
        }

        if (updateDto.getPassword() != null && !updateDto.getPassword().isBlank()) {
            user.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }

        if (updateDto.getPin() != null && !updateDto.getPin().isBlank()) {
            user.setPin(updateDto.getPin());
        }

        return userRepository.save(user);
    }
}