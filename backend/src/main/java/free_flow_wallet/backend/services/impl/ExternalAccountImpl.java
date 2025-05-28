package free_flow_wallet.backend.services.impl;

import free_flow_wallet.backend.dtos.ExternalAccountDto;
import free_flow_wallet.backend.entities.ExternalAccount;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.entities.Wallet;
import free_flow_wallet.backend.exceptions.UserNotFoundException;
import free_flow_wallet.backend.mappers.ExternalAccountMapper;
import free_flow_wallet.backend.repositories.ExternalAccountRepository;
import free_flow_wallet.backend.repositories.UserRepository;
import free_flow_wallet.backend.repositories.WalletRepository;
import free_flow_wallet.backend.services.ExternalAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ExternalAccountImpl implements ExternalAccountService {

    @Autowired
    private UserRepository userRepository;
    private ExternalAccountRepository externalAccountRepository;
    private WalletRepository walletRepository;

    @Override
    public ExternalAccountDto addFunds(ExternalAccountDto dto, Authentication authentication) {
        // Step 1: Get email from Authentication
        String email = authentication.getName();

        // Step 2: Find the User
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        // Step 3: Find the User's Wallet
        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        // Step 4: Add funds to wallet
        BigDecimal amount = dto.getAmount() != null ? dto.getAmount() : BigDecimal.ZERO;
        wallet.setBalance(wallet.getBalance().add(amount));
        walletRepository.save(wallet); // Save the updated wallet

        // Step 5: Map DTO to Entity
        ExternalAccount externalAccount = ExternalAccountMapper.toEntity(dto, user);
        ExternalAccount savedAccount = externalAccountRepository.save(externalAccount);

        // Step 6: Return DTO
        return ExternalAccountMapper.toDto(savedAccount);
    }

}
