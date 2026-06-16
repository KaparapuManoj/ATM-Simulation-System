import java.util.Scanner;

class Account {
    private double balance;
    private int pin;

    public Account(double balance, int pin) {
        this.balance = balance;
        this.pin = pin;
    }

    public boolean verifyPin(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Amount deposited successfully.");
    }

    public boolean withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return false;
        } else {
            balance -= amount;
            System.out.println("Withdrawal successful.");
            return true;
        }
    }
}

class ATM {
    private Account account;
    private boolean sessionActive;

    public ATM(Account account) {
        this.account = account;
        this.sessionActive = false;
    }

    public void startSession() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your PIN: ");
        int inputPin = sc.nextInt();

        if (account.verifyPin(inputPin)) {
            sessionActive = true;
            System.out.println("Login successful!\n");
            showMenu(sc);
        } else {
            System.out.println("Invalid PIN. Access denied.");
        }
    }

    private void showMenu(Scanner sc) {
        while (sessionActive) {
            System.out.println("\n--- ATM MENU ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Current Balance: " + account.getBalance());
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = sc.nextDouble();
                    account.deposit(depositAmount);
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = sc.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;

                case 4:
                    endSession();
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void endSession() {
        sessionActive = false;
        System.out.println("Session ended. Thank you!");
    }
}

public class ATMSimulationsystem {
    public static void main(String[] args) {
        Account userAccount = new Account(10000, 1234);
        ATM atm = new ATM(userAccount);

        atm.startSession();
    }
}