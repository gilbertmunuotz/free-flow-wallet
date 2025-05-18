package free_flow_wallet.backend.mappers;

import free_flow_wallet.backend.dtos.ExternalAccountDto;
import free_flow_wallet.backend.entities.ExternalAccount;
import free_flow_wallet.backend.entities.User;

public class ExternalAccountMapper { // from DB ➔ to output (DTO) ✅
    public static ExternalAccountDto toDto(ExternalAccount externalAccount) {
        ExternalAccountDto externalAccount1 = new ExternalAccountDto();
        externalAccount1.setId(externalAccount.getId());
        externalAccount1.setProviderName(externalAccount.getProviderName());
        externalAccount1.setAccountNumber(externalAccount.getAccountNumber());
        externalAccount1.setUserId(externalAccount1.getUserId());
        return externalAccount1;
    }

    public static ExternalAccount toEntity(ExternalAccountDto externalAccountDto, User user) { // from input ➔ to database (Entity) ✅
        ExternalAccount externalAccount = new ExternalAccount();
        externalAccount.setProviderName(externalAccountDto.getProviderName());
        externalAccount.setAccountNumber(externalAccountDto.getAccountNumber());
        externalAccount.setUser(user);
        return externalAccount;
    }
}