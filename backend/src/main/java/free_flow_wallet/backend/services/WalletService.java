package free_flow_wallet.backend.services;

import free_flow_wallet.backend.dtos.WalletDto;
import org.springframework.security.core.Authentication;

public interface WalletService {

    WalletDto getBalance(Authentication authentication); // Get Wallet Balance
}
