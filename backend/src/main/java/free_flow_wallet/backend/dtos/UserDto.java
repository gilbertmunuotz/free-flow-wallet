package free_flow_wallet.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String fullName;
    private String email;

}
