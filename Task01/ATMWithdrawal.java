import java.util.Scanner;

class Account {
    private final long accountNumber;
    private double balance;
    Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber; this.balance = balance;
    }
    long getAccountNumber() { return accountNumber; }
    double getBalance() { return balance; }
    void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        if (amount > balance) throw new IllegalStateException("Insufficient balance");
        balance -= amount;
    }
}
public class ATMWithdrawal {
    private static void process(Account account, String accountText, String amountText) {
        System.out.println("\\nTransaction: account=" + accountText + ", amount=" + amountText);
        try {
            long enteredAccount = Long.parseLong(accountText);
            double amount = Double.parseDouble(amountText);
            if (enteredAccount != account.getAccountNumber())
                throw new IllegalArgumentException("Account number does not match");
            account.withdraw(amount);
            System.out.println("Withdrawal successful. Remaining balance: " + account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Input error: account number and amount must be numeric.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Transaction rejected: " + e.getMessage());
        } finally {
            System.out.println("ATM session cleanup completed.");
        }
    }
    public static void main(String[] args) {
        Account account = new Account(12345678L, 5000.0);
        process(account, "12345678", "1000");
        process(account, "ABC", "500");
        process(account, "12345678", "-200");
        process(account, "12345678", "9000");
        process(account, "87654321", "500");
        System.out.println("\\nRemaining balance: " + account.getBalance());
    }
}