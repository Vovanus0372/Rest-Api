package tests;

import dto.AccountResponse;
import dto.CreateAccountRequest;
import exception.AccountNotFoundException;
import model.BankAccount;
import repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import service.BankAccountService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BankAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;

    @InjectMocks
    private BankAccountService bankAccountService;

//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this); // Инициализация моков
//    }

    @Test
    void createAccount_ShouldReturnAccountResponse() {
        CreateAccountRequest request = new CreateAccountRequest("John Doe", "1234");
        BankAccount savedAccount = new BankAccount();
        savedAccount.setAccountNumber("1234567890");
        savedAccount.setOwnerName("John Doe");
        savedAccount.setBalance(0);

        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(savedAccount);

        AccountResponse response = bankAccountService.createAccount(request);

        assertNotNull(response);
        assertEquals("1234567890", response.getAccountNumber());
        assertEquals("John Doe", response.getOwnerName());
    }

    @Test
    void getAccountByNumber_ShouldReturnAccountResponse() {
        BankAccount account = new BankAccount();
        account.setAccountNumber("1234567890");
        account.setOwnerName("John Doe");
        account.setBalance(100);

        when(bankAccountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.of(account));

        AccountResponse response = bankAccountService.getAccountByNumber("1234567890");

        assertNotNull(response);
        assertEquals("1234567890", response.getAccountNumber());
        assertEquals(100, response.getBalance());
    }

    @Test
    void getAccountByNumber_ShouldThrowAccountNotFoundException() {
        when(bankAccountRepository.findByAccountNumber("1234567890")).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> bankAccountService.getAccountByNumber("1234567890"));
    }
}
