package free_flow_wallet.backend.services;

import free_flow_wallet.backend.dtos.P2PRequestDto;
import free_flow_wallet.backend.dtos.TransactionDto;

public interface TransactionService {

    TransactionDto sendP2PTransaction(P2PRequestDto p2PRequestDto, String senderEmail);
}
