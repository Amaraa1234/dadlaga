package java2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 1. BankAccount класс
class BankAccount {
    private int balance = 1000;

    // Мөнгө нэмэх
    public synchronized void deposit(int amount) {
        balance += amount;
        System.out.println(Thread.currentThread().getName() + " Deposited " + amount + ", Balance: " + balance);
    }

    // Мөнгө авах
    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " Withdrawn " + amount + ", Balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " Insufficient funds for " + amount
                    + ". Current balance: " + balance);
        }
    }

    public int getBalance() {
        return balance;
    }
}

// 2. Customer класс (Runnable-ийг хэрэгжүүлсэн)
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

// 3. Программыг ажиллуулах үндсэн класс
public class Main {
    public static void main(String[] args) {
        BankAccount sharedAccount = new BankAccount();

        // ExecutorService ашиглан 3 thread-тэй pool үүсгэх
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Хэрэглэгчдийн хүсэлтийг дараалалд оруулах
        executor.execute(new Customer(sharedAccount, "withdraw", 700)); // Thread 1
        executor.execute(new Customer(sharedAccount, "withdraw", 500)); // Thread 2
        executor.execute(new Customer(sharedAccount, "deposit", 200)); // Thread 3

        // Ажил дууссаны дараа executor-ийг хаах
        executor.shutdown();
    }
}