package free_flow_wallet.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Define User Properties
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private String pin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExternalAccount> externalAccounts;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<Transaction> sentTransactions;

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    private List<Transaction> receivedTransactions;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
