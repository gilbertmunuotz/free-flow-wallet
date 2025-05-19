package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.P2PRequestDto;
import free_flow_wallet.backend.dtos.TransactionDto;
import free_flow_wallet.backend.services.TransactionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

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
}
