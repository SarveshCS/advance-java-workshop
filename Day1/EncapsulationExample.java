public class EncapsulationExample {
    public static void main(String[] args) {
        BankAccount account = new BankAccount("SB1001", "Sarvesh", 5000.00);

        account.deposit(1500.00);
        boolean withdrawalStatus = account.withdraw(2200.00);

        System.out.println("Account Holder: " + account.getAccountHolderName());
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Withdrawal Successful: " + withdrawalStatus);
        System.out.println("Current Balance: Rs. " + account.getBalance());
    }
}

class BankAccount {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    BankAccount(String accountNumber, String accountHolderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = openingBalance;
    }

    String getAccountNumber() {
        return accountNumber;
    }

    String getAccountHolderName() {
        return accountHolderName;
    }

    double getBalance() {
        return balance;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    boolean withdraw(double amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        return true;
    }
}