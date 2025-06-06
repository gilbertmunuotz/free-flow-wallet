package free_flow_wallet.backend.controllers;

import free_flow_wallet.backend.dtos.ExternalAccountDto;
import free_flow_wallet.backend.services.ExternalAccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/external-accounts")
public class ExternalAccountController {

    private final ExternalAccountService externalAccountService;

    @PostMapping("/funds")
    public ResponseEntity<ExternalAccountDto> addFunds(@Valid @RequestBody ExternalAccountDto dto, Authentication authentication) {
        ExternalAccountDto savedDto = externalAccountService.addFunds(dto, authentication);
        return ResponseEntity.ok(savedDto);
    }

    @GetMapping("/history")
    public ResponseEntity<List<ExternalAccountDto>> getFundingHistory(Authentication authentication) {
        List<ExternalAccountDto> history = externalAccountService.getFundingHistory(authentication);
        return ResponseEntity.ok(history);
    }

}
