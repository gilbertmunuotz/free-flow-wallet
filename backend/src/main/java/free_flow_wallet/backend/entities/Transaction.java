package free_flow_wallet.backend.entities;

import free_flow_wallet.backend.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    // Define transaction properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type; // P2P, ADD_FUNDS, WITHDRAW

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String category; // For analytics e.g., "Food", "Transport"

    private String description;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}