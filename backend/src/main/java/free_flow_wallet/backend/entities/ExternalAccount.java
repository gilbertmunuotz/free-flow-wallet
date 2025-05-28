package free_flow_wallet.backend.entities;

import free_flow_wallet.backend.enums.ProviderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "external_accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExternalAccount {
    // Define ExternalAccount properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ProviderType providerName;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime linkedAt;
}
