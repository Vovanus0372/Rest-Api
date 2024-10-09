import model.BankAccount;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import repository.BankAccountRepository;

@Component
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(BankAccountRepository repository) {
        return (args) -> {
            // Создаем тестовые счета
            repository.save(new BankAccount("123456", "John Doe", 1000L));
            repository.save(new BankAccount("654321", "Jane Smith", 2000L));
        };
    }
}
