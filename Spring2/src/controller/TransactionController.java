package controller;

import dto.TransactionRequest;
import dto.TransactionResponse;
import service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // Выполнение транзакции (депозит, снятие, перевод)
    @PostMapping
    public ResponseEntity<TransactionResponse> performTransaction(@RequestBody TransactionRequest request) {
        TransactionResponse response = transactionService.performTransaction(request);
        return ResponseEntity.ok(response);
    }

    // Получение всех транзакций для конкретного счета
    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsForAccount(@PathVariable String accountNumber) {
        List<TransactionResponse> transactions = transactionService.getTransactionsForAccount(accountNumber);
        return ResponseEntity.ok(transactions);
    }
}
