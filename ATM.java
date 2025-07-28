import java.util.Scanner;

class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

public class ATM {
    private BankAccount bankAccount;
    private Scanner scanner;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    public void checkBalance() {
        System.out.printf("\nCurrent balance: $%.2f%n", bankAccount.getBalance());
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("\nError: Deposit amount must be positive");
            return;
        }
        if (bankAccount.deposit(amount)) {
            System.out.printf("\nSuccessfully deposited $%.2f%n", amount);
            checkBalance();
        } else {
            System.out.println("\nError: Unable to process deposit");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("\nError: Withdrawal amount must be positive");
            return;
        }
        if (bankAccount.withdraw(amount)) {
            System.out.printf("\nSuccessfully withdrew $%.2f%n", amount);
            checkBalance();
        } else {
            System.out.println("\nError: Insufficient funds or invalid amount");
        }
    }

    public void run() {
        while (true) {
            displayMenu();
            System.out.print("\nEnter your choice (1-4): ");
            
            try {
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: $");
                        double depositAmount = scanner.nextDouble();
                        deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: $");
                        double withdrawAmount = scanner.nextDouble();
                        withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("\nThank you for using the ATM. Goodbye!");
                        scanner.close();
                        return;
                    default:
                        System.out.println("\nError: Invalid choice. Please select 1-4");
                }
            } catch (Exception e) {
                System.out.println("\nError: Invalid input. Please enter a valid number");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount("Akash Singh Gaira", 100000.00);
        ATM atm = new ATM(account);
        atm.run();
    }
}