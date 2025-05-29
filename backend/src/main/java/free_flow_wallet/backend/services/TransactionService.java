package free_flow_wallet.backend.services;

import free_flow_wallet.backend.dtos.P2PRequestDto;
import free_flow_wallet.backend.dtos.TransactionDto;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TransactionService {

    TransactionDto sendP2PTransaction(P2PRequestDto p2PRequestDto, String senderEmail); // Make P2P Transaction

    List<TransactionDto> getP2PTransaction(Authentication authentication); // Get P2P Transaction

    @Transactional
    void deleteP2PTransaction(Long id, String email);
}
