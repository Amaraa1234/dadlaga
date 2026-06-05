import java.util.*;

class BankAccount {
    private int balance = 1000;

    // synchronized: Olon thread zereg handahad balance-iig aldaagui hadgalna
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out
                .println(Thread.currentThread().getName() + " Deposited: " + amount + ", Current Balance: " + balance);
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(
                    Thread.currentThread().getName() + " Withdrawn: " + amount + ", Current Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " Insufficient funds for " + amount + " (Balance: "
                    + balance + ")");
        }
    }

    public int getBalance() {
        return balance;
    }
}

class Customer implements Runnable {
    private BankAccount account;
    private String action;
    private int amount;

    public Customer(BankAccount account, String action, int amount) {
        this.account = account;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void run() {
        if (action.equalsIgnoreCase("deposit")) {
            account.deposit(amount);
        } else if (action.equalsIgnoreCase("withdraw")) {
            account.withdraw(amount);
        }
    }
}

public class BankSimulation {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();
        Thread[] customers = new Thread[3];

        // Thread-uudiig uushgeh (Ner ogov)
        customers[0] = new Thread(new Customer(account, "deposit", 500), "Customer-1");
        customers[1] = new Thread(new Customer(account, "withdraw", 700), "Customer-2");
        customers[2] = new Thread(new Customer(account, "withdraw", 600), "Customer-3");

        // Thread-uudiig ehluuleh
        for (Thread t : customers) {
            t.start();
        }

        // join(): Buh threaduudiig duusahiig huleene
        for (Thread t : customers) {
            t.join();
        }

        System.out.println("---------------------------");
        System.out.println("Final Bank Balance: " + account.getBalance());
    }
}
