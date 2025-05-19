package free_flow_wallet.backend.repositories;

import free_flow_wallet.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalAccountRepository extends JpaRepository<User, Long> {

}