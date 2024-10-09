package model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;

    private double amount;
    private String AccountNumber;

    private String type; // Например, DEPOSIT, WITHDRAWAL, TRANSFER

    private LocalDateTime date;

    // Конструкторы
    public Transactions() {}

    public Transactions(BankAccount bankAccount, double amount, String type) {
        this.bankAccount = bankAccount;
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
    }

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
