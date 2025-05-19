package free_flow_wallet.backend.dtos;

import free_flow_wallet.backend.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {

    private Long id;
    private BigDecimal amount;
    private TransactionType type; // P2P, ADD_FUNDS, WITHDRAW(call the enum)
    private Long senderId;
    private Long receiverId;
    private String description;
    private LocalDateTime timestamp;

}
