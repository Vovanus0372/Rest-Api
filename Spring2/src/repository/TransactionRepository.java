package repository;

import model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    // Поиск всех транзакций для конкретного номера счета
    List<Transactions> findByAccountNumber(String accountNumber);
}
