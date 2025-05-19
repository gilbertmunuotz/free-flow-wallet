package free_flow_wallet.backend.repositories;

import free_flow_wallet.backend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}