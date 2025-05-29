package free_flow_wallet.backend.repositories;

import free_flow_wallet.backend.entities.Transaction;
import free_flow_wallet.backend.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByTypeAndSenderId(TransactionType type, Long senderId);

    List<Transaction> findByTypeAndReceiverId(TransactionType type, Long receiverId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Transaction t WHERE t.id = :id AND t.type = 'P2P'")
    void deleteP2PTransactionById(Long id);
}