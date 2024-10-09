package service;

import dto.TransactionRequest;
import dto.TransactionResponse;
import exception.AccountNotFoundException;
import exception.InsufficientFundsException;
import exception.InvalidPinCodeException;
import model.BankAccount;
import model.Transactions;
import repository.BankAccountRepository;
import repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    // Выполнение транзакции
    public TransactionResponse performTransaction(TransactionRequest request) {
        BankAccount account = bankAccountRepository.findByAccountNumber(request.getAccountNumber())
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        // Проверка PIN-кода
        if (!account.getPinCode().equals(request.getPinCode())) {
            throw new InvalidPinCodeException("Invalid PIN code");
        }

        // Обработка типа транзакции
        if (request.getAmount() < 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        double newBalance;
        if (request.getAmount() > 0) {
            // Депозит или перевод
            newBalance = account.getBalance() + request.getAmount();
        } else {
            // Снятие средств
            if (account.getBalance() < -request.getAmount()) {
                throw new InsufficientFundsException("Insufficient funds");
            }
            newBalance = account.getBalance() + request.getAmount(); // Уменьшение баланса
        }

        account.setBalance(newBalance);
        bankAccountRepository.save(account);

        // Сохранение транзакции
        Transactions transaction = new Transactions();
        transaction.setAccountNumber(account.getAccountNumber());
        transaction.setAmount(request.getAmount());
        transaction.setDate(LocalDateTime.now());
        transactionRepository.save(transaction);

        return new TransactionResponse(account.getAccountNumber(), request.getAmount(), "transaction", LocalDateTime.now());
    }

    // Получение всех транзакций для конкретного счета
    public List<TransactionResponse> getTransactionsForAccount(String accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        List<Transactions> transactions = transactionRepository.findByAccountNumber(account.getAccountNumber());
        return transactions.stream()
                .map(tx -> new TransactionResponse(tx.getAccountNumber(), tx.getAmount(), "transaction", tx.getDate()))
                .collect(Collectors.toList());
    }
}
