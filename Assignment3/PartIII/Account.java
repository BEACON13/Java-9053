
public class Account {
    private int account_count;

    private double balance;

    private final int id;

    private static int nextId = 1;

    public Account() {
        this.id = nextId;
        nextId++;
    }

    public Account(double startingBalance) {
        this.id = nextId;
        nextId++;
        this.balance = startingBalance;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public int getId() {
        return id;
    }
}
