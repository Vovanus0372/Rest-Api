package dto;

import java.time.LocalDateTime;

public class TransactionResponse {
    private String accountNumber;
    private double amount;
    private String type;
    private LocalDateTime date;

    // Конструкторы
    public TransactionResponse() {}

    public TransactionResponse(String accountNumber, double amount, String type, LocalDateTime date) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.date = date;
    }

    // Геттеры и сеттеры
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
