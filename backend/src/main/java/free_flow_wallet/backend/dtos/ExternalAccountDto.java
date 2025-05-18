package free_flow_wallet.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class ExternalAccountDto {

    private Long id;
    private String providerName;
    private String accountNumber;
    private Long userId;

}
