package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.WalletDto;
import free_flow_wallet.backend.services.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/wallet")
public class WalletController {

    @Autowired
    private final WalletService walletService;

    @PostMapping("/balance")
    public ResponseEntity<WalletDto> getBalance(Authentication authentication) {
        WalletDto balance = walletService.getBalance(authentication);
        return ResponseEntity.ok(balance);
    }
}
