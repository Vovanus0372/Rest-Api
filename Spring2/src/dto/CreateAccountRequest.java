package dto;

public class CreateAccountRequest {
    private String ownerName;
    private String pinCode;

    public CreateAccountRequest() {}

    public CreateAccountRequest(String ownerName, String pinCode) {
        this.ownerName = ownerName;
        this.pinCode = pinCode;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "ownerName='" + ownerName + '\'' +
                ", pinCode='" + pinCode + '\'' +
                '}';
    }
}
