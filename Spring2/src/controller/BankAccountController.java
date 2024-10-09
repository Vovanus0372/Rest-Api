package controller;

import dto.AccountResponse;
import dto.CreateAccountRequest;
import service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    // Создание нового банковского счета
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        AccountResponse response = bankAccountService.createAccount(request);
        return ResponseEntity.ok(response);
    }

    // Получение всех счетов с текущими балансами
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<AccountResponse> accounts = bankAccountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }

    // Получение конкретного счета по его номеру
    @GetMapping("/{accountNumber}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable String accountNumber) {
        AccountResponse account = bankAccountService.getAccountByNumber(accountNumber);
        return ResponseEntity.ok(account);
    }
}
