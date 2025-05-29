package free_flow_wallet.backend.services;

import free_flow_wallet.backend.dtos.ExternalAccountDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ExternalAccountService {
    ExternalAccountDto addFunds(ExternalAccountDto dto, Authentication authentication);

    List<ExternalAccountDto> getFundingHistory(Authentication authentication);
}
