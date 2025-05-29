package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.P2PRequestDto;
import free_flow_wallet.backend.dtos.TransactionDto;
import free_flow_wallet.backend.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionsController {

    @Autowired
    private final TransactionService transactionService;

    @PostMapping("/p2p")
    public ResponseEntity<TransactionDto> sendP2PTransaction(@Valid @RequestBody P2PRequestDto p2PRequestDto, Principal principal) {
        String senderEmail = principal.getName(); // Comes from the JWT sub
        TransactionDto result = transactionService.sendP2PTransaction(p2PRequestDto, senderEmail);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/p2p/history")
    public ResponseEntity<List<TransactionDto>> getP2PTransaction(Authentication authentication) {
        return ResponseEntity.ok(transactionService.getP2PTransaction(authentication));
    }

    @DeleteMapping("/p2p/{id}")
    public ResponseEntity<?> deleteP2PTransaction(@PathVariable Long id, Authentication authentication) {
        String email = authentication.getName(); // JWT sub
        transactionService.deleteP2PTransaction(id, email);
        return ResponseEntity.ok().build();
    }
}
