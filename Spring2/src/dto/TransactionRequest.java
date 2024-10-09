package dto;

public class TransactionRequest {
    private String accountNumber;
    private double amount;
    private String pinCode;

    // Конструкторы
    public TransactionRequest() {}

    public TransactionRequest(String accountNumber, double amount, String pinCode) {
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.pinCode = pinCode;
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

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }
}
