package free_flow_wallet.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor

public class ExternalAccountDto {

    private Long id;

    @NotBlank(message = "Provider Required")
    private String providerName;

    @NotNull(message = "Amount Required")
    private BigDecimal amount;

    private Long userId;

}