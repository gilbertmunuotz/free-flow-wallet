package free_flow_wallet.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class WalletDto {

    private Long id;
    private Double balance;
    private Long userId;

}
