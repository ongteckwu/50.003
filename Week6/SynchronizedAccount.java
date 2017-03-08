package Week6;

/**
 * Created by ongteckwu on 8/3/17.
 */
public class SynchronizedAccount {
    private double balance;

    public SynchronizedAccount(double balance) {
        this.balance = balance;
    }

    public synchronized boolean withdraw(double amount) {
        if (amount < balance) {
            System.out.println("Insufficient funds");
            return false;
        }
        balance -= amount;
        System.out.printf("Amount %.2f withdrawn. Balance: %.2f\n", amount, balance);
        return true;
    }

    public synchronized boolean deposit(double amount) {
        balance += amount;
        System.out.printf("Amount %.2f withdrawn. Balance: %.2f\n", amount, balance);
        return true;
    }

    public void check() {
        System.out.printf("Current balance: %.2f\n", balance);
    }
}
