package free_flow_wallet.backend.services;

import free_flow_wallet.backend.dtos.*;
import free_flow_wallet.backend.entities.User;


public interface UserService {

    UserDto registerUser(RegisterRequestDto registerRequestDto); // Create/Register User

    LoginResponseDto loginUser(AuthRequestDto authRequestDto); // Login User

    User updateUserInfo(String email, UserUpdateRequestDto updateDto); // Update User Info

    User getUserByEmail(String email);
}
