package free_flow_wallet.backend.services.impl;


import free_flow_wallet.backend.dtos.WalletDto;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.repositories.UserRepository;
import free_flow_wallet.backend.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WalletServiceImpl implements WalletService {

    @Autowired
    private UserRepository userRepository; // Interface to talk to DB and invoke all methods


    @Override
    public WalletDto getBalance(Authentication authentication) {
        String userEmail = authentication.getName(); // Gets email from JWT token
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return WalletDto.builder()
                .id(user.getWallet().getId())
                .balance(user.getWallet().getBalance())
                .userId(user.getId())
                .build();
    }
}
