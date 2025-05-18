package free_flow_wallet.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    private String providerName; // e.g., "Chase", "PayPal"

    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime linkedAt;
}
