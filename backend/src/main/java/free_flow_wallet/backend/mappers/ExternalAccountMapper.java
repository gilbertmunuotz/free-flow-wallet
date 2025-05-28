package free_flow_wallet.backend.mappers;

import free_flow_wallet.backend.dtos.ExternalAccountDto;
import free_flow_wallet.backend.entities.ExternalAccount;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.enums.ProviderType;

public class ExternalAccountMapper { // from DB ➔ to output (DTO) ✅
    public static ExternalAccountDto toDto(ExternalAccount externalAccount) {
        ExternalAccountDto externalAccount1 = new ExternalAccountDto();
        externalAccount1.setId(externalAccount.getId());
        externalAccount1.setProviderName(externalAccount.getProviderName().name()); // Convert enum to String
        externalAccount1.setUserId(externalAccount.getUser().getId());
        externalAccount1.setAmount(externalAccount.getAmount());
        return externalAccount1;
    }

    public static ExternalAccount toEntity(ExternalAccountDto externalAccountDto, User user) { // from input ➔ to database (Entity) ✅
        ExternalAccount externalAccount = new ExternalAccount();
        externalAccount.setProviderName(ProviderType.valueOf(externalAccountDto.getProviderName().toUpperCase())); // Convert String to enum
        externalAccount.setUser(user);
        externalAccount.setAmount(externalAccountDto.getAmount());
        return externalAccount;
    }
}