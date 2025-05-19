package free_flow_wallet.backend.services;

import free_flow_wallet.backend.dtos.AuthRequestDto;
import free_flow_wallet.backend.dtos.LoginResponseDto;
import free_flow_wallet.backend.dtos.RegisterRequestDto;
import free_flow_wallet.backend.dtos.UserDto;

public interface UserService {

    UserDto registerUser(RegisterRequestDto registerRequestDto); // Create/Register User

    LoginResponseDto loginUser(AuthRequestDto authRequestDto); // Login User
}
