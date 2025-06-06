package free_flow_wallet.backend.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class WalletDto {

    private Long id;
    private BigDecimal balance;
    private Long userId;

}
