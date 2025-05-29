package free_flow_wallet.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {
    private String fullName;
    private String email;
    private String password;
    private String pin;
}