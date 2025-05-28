package free_flow_wallet.backend.repositories;

import free_flow_wallet.backend.entities.Transaction;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTypeAndSenderId(TransactionType type, Long senderId);

    List<Transaction> findByTypeAndReceiverId(TransactionType type, Long receiverId);
}