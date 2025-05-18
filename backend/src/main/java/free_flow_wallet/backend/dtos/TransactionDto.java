package free_flow_wallet.backend.dtos;

import free_flow_wallet.backend.enums.TransactionType;
import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionDto {

    private Long id;
    private Double amount;
    private TransactionType type; // P2P, ADD_FUNDS, WITHDRAW(call the enum)
    private Long senderId;
    private Long receiverId;
    private String category; // Optional, for analytics
    private String description;
    private LocalDateTime timestamp;

}
