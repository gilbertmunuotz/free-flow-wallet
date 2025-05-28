package free_flow_wallet.backend.repositories;

import free_flow_wallet.backend.entities.ExternalAccount;
import free_flow_wallet.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExternalAccountRepository extends JpaRepository<ExternalAccount, Long> {
    List<ExternalAccount> findByUser(User user);
}