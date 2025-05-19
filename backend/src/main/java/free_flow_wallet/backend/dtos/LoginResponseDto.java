package free_flow_wallet.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class LoginResponseDto {
    private Long id;
    private String email;
    private String token;
}
