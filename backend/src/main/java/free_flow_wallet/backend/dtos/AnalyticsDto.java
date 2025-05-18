package free_flow_wallet.backend.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class AnalyticsDto {

    private String category;
    private Double totalSpent;
    private Double percentage;

}
