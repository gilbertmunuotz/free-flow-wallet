package free_flow_wallet.backend.mappers;

import free_flow_wallet.backend.dtos.TransactionDto;
import free_flow_wallet.backend.entities.Transaction;
import free_flow_wallet.backend.entities.User;

public class TransactionMapper {
    public static TransactionDto toDto(Transaction tx) { // from DB ➔ to output (DTO) ✅
        TransactionDto dto = new TransactionDto();
        dto.setId(tx.getId());
        dto.setAmount(tx.getAmount());
        dto.setType(tx.getType());
        dto.setSenderId(tx.getSender().getId());
        dto.setReceiverId(tx.getReceiver().getId());
        dto.setDescription(tx.getDescription());
        dto.setTimestamp(tx.getCreatedAt());
        return dto;
    }

    public static Transaction toEntity(TransactionDto dto, User sender, User receiver) { // from input ➔ to database (Entity) ✅
        Transaction tx = new Transaction();
        tx.setAmount(dto.getAmount());
        tx.setType(dto.getType());
        tx.setSender(sender);
        tx.setReceiver(receiver);
        tx.setDescription(dto.getDescription());
        tx.setCreatedAt(dto.getTimestamp());
        return tx;
    }
}
