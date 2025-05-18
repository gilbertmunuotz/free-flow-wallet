package free_flow_wallet.backend.mappers;

import free_flow_wallet.backend.dtos.RegisterRequestDto;
import free_flow_wallet.backend.dtos.UserDto;
import free_flow_wallet.backend.entities.User;

public class UserMapper {
    public static UserDto toDto(User user) { // from DB ➔ to output (DTO) ✅
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static User toEntity(RegisterRequestDto registerRequestDto) { // from input ➔ to database (Entity) ✅
        User user = new User();
        user.setFullName(registerRequestDto.getFullName());
        user.setEmail(registerRequestDto.getEmail());
        user.setPassword(registerRequestDto.getPassword());
        user.setPin(registerRequestDto.getPin());
        return user;
    }
}
