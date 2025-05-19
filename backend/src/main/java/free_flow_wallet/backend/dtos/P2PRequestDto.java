package free_flow_wallet.backend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class P2PRequestDto {

    @NotBlank(message = "Receiver Email is required")
    @Email(message = "Receiver Email should be valid")
    private String receiverEmail;

    @NotNull(message = "Amount is required")
    private BigDecimal amount;

    @NotBlank(message = "PIN is required")
    private String pin;
}