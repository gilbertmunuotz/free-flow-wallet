package free_flow_wallet.backend.services.impl;

import free_flow_wallet.backend.dtos.P2PRequestDto;
import free_flow_wallet.backend.dtos.TransactionDto;
import free_flow_wallet.backend.entities.Transaction;
import free_flow_wallet.backend.entities.User;
import free_flow_wallet.backend.enums.TransactionType;
import free_flow_wallet.backend.exceptions.*;
import free_flow_wallet.backend.mappers.TransactionMapper;
import free_flow_wallet.backend.repositories.TransactionRepository;
import free_flow_wallet.backend.repositories.UserRepository;
import free_flow_wallet.backend.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public TransactionDto sendP2PTransaction(P2PRequestDto p2PRequestDto, String senderEmail) {
        // Step 1: Get senderEmail in token from Controller
        User sender = userRepository.findByEmail(senderEmail)
                .orElseThrow(() -> new UserNotFoundException("Sender not found"));

        // Step 2: Validate PIN (not hashed currently)
        if (!p2PRequestDto.getPin().equals(sender.getPin())) {
            throw new InvalidPinException("Incorrect transaction PIN");
        }

        // Step 3: Get receiver
        User receiver = userRepository.findByEmail(p2PRequestDto.getReceiverEmail())
                .orElseThrow(() -> new UserNotFoundException("Receiver not found"));

        // Step 4: Check balance
        if (sender.getWallet().getBalance().compareTo(p2PRequestDto.getAmount()) < 0) {
            throw new InsufficientBalanceException("Not enough balance");
        }

        // Step 5: Deduct from sender, credit to receiver
        sender.getWallet().setBalance(sender.getWallet().getBalance().subtract(p2PRequestDto.getAmount()));
        receiver.getWallet().setBalance(receiver.getWallet().getBalance().add(p2PRequestDto.getAmount()));

        // Step 6: Save updated wallets
        userRepository.save(sender);
        userRepository.save(receiver);

        // Step 7: Create and save transaction
        Transaction transaction = new Transaction();
        transaction.setSender(sender);
        transaction.setReceiver(receiver);
        transaction.setAmount(p2PRequestDto.getAmount());
        transaction.setCreatedAt(LocalDateTime.now());
        transaction.setType(TransactionType.P2P);
        transaction.setDescription("P2P Transfer");

        Transaction saved = transactionRepository.save(transaction);

        // Step 8: Return DTO
        return TransactionMapper.toDto(saved);
    }

    @Override
    public List<TransactionDto> getP2PTransaction(Authentication authentication) {
        String email = authentication.getName(); // from JWT sub
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Long userId = user.getId();

        // Get sent and received transactions separately
        List<Transaction> sent = transactionRepository.findByTypeAndSenderId(TransactionType.P2P, userId);
        List<Transaction> received = transactionRepository.findByTypeAndReceiverId(TransactionType.P2P, userId);

        // Combine both lists
        List<Transaction> all = new ArrayList<>();
        all.addAll(sent);
        all.addAll(received);

        // Map to DTOs
        return all.stream()
                .map(TransactionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteP2PTransaction(Long transactionId, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        // Ensure the user is sender or receiver of this P2P transaction
        if (transaction.getType() != TransactionType.P2P ||
                (!transaction.getSender().equals(user) && !transaction.getReceiver().equals(user))) {
            throw new AccessDeniedException("You are not allowed to delete this transaction");
        }

        transactionRepository.deleteP2PTransactionById(transactionId);
    }
}