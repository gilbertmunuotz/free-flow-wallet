package free_flow_wallet.backend.mappers;

import free_flow_wallet.backend.dtos.WalletDto;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.entities.Wallet;

public class WalletMapper {
    public static WalletDto toDto(Wallet wallet) { // from DB ➔ to output (DTO) ✅
        WalletDto walletDto = new WalletDto();
        walletDto.setId(wallet.getId());
        walletDto.setBalance(wallet.getBalance());
        walletDto.setUserId(wallet.getUser().getId());
        return walletDto;
    }

    public static Wallet toEntity(WalletDto walletDto, User user) { // from input ➔ to database (Entity) ✅
        Wallet wallet = new Wallet();
        wallet.setBalance(walletDto.getBalance());
        wallet.setUser(user);
        return wallet;
    }
}
