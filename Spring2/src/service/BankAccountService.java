package service;

import dto.AccountResponse;
import dto.CreateAccountRequest;
import exception.AccountNotFoundException;
import model.BankAccount;
import repository.BankAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    // Создание нового банковского счета
    public AccountResponse createAccount(CreateAccountRequest request) {
        BankAccount newAccount = new BankAccount();
        newAccount.setOwnerName(request.getOwnerName());
        newAccount.setPinCode(request.getPinCode());
        newAccount.setBalance(0);  // Начальный баланс

        BankAccount savedAccount = bankAccountRepository.save(newAccount);
        return new AccountResponse(savedAccount.getAccountNumber(), savedAccount.getOwnerName(), savedAccount.getBalance());
    }

    // Получение всех счетов с их балансами
    public List<AccountResponse> getAllAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return accounts.stream()
                .map(account -> new AccountResponse(account.getAccountNumber(), account.getOwnerName(), account.getBalance()))
                .collect(Collectors.toList());
    }

    // Получение конкретного счета по его номеру
    public AccountResponse getAccountByNumber(String accountNumber) {
        BankAccount account = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new AccountNotFoundException("Account with number " + accountNumber + " not found"));
        return new AccountResponse(account.getAccountNumber(), account.getOwnerName(), account.getBalance());
    }
}
