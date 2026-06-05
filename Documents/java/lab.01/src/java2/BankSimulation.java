package java2;

class BankAccount {
    private int balance = 1000;

    // 1. deposit() функцэд synchronized нэмсэн
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " Deposited " + amount + ", Balance: " + balance);
    }

    // 1. withdraw() функцэд synchronized нэмсэн
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " Withdrawn " + amount + ", Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " Insufficient funds for " + amount);
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

        // Thread-үүдийг үүсгэх
        customers[0] = new Thread(new Customer(account, "deposit", 500));
        customers[1] = new Thread(new Customer(account, "withdraw", 700));
        customers[2] = new Thread(new Customer(account, "withdraw", 600));

        // Thread-үүдийг эхлүүлэх
        for (Thread t : customers) {
            t.start();
        }

        // 2. Бүх урсгалыг дуусахыг хүлээхийн тулд join() ашиглах
        for (Thread t : customers) {
            t.join();
        }

        // Эцсийн балансыг хэвлэх
        System.out.println("Final Balance: " + account.getBalance());
    }

}
