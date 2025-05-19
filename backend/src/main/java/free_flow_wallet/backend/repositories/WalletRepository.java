package free_flow_wallet.backend.repositories;

import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findByUser(User user);
}
