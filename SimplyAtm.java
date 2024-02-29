import java.util.Scanner;
// Step 4: Create a class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }
}

// Step 1: Create a class to represent the ATM machine
interface ATM {
    // Step 3: Implement methods for each option
    void withdraw(double amount);

    void deposit(double amount);

    void checkBalance();
}

// Step 2: Design the user interface for the ATM
class SimpleATM implements ATM {
    private BankAccount userAccount;

    public SimpleATM(BankAccount userAccount) {
        this.userAccount = userAccount;
    }

    // Step 6: Validate user input
    private double validateAmountInput() {
        Scanner scanner = new Scanner(System.in);
        double amount;

        do {
            System.out.print("Enter amount: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid amount.");
                System.out.print("Enter amount: ");
                scanner.next(); // consume the invalid input
            }
            amount = scanner.nextDouble();

            if (amount <= 0) {
                System.out.println("Amount must be greater than zero.");
            }
        } while (amount <= 0);

        return amount;
    }

    // Step 3: Implement methods for each option
    @Override
    public void withdraw(double amount) {
        if (userAccount.withdraw(amount)) {
            System.out.println("Withdrawal successful. Remaining balance: " + userAccount.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }

    @Override
    public void deposit(double amount) {
        userAccount.deposit(amount);
        System.out.println("Deposit successful. Updated balance: " + userAccount.getBalance());
    }

    @Override
    public void checkBalance() {
        System.out.println("Current balance: " + userAccount.getBalance());
    }

    public static void main(String[] args) {
        // Initialize the user's bank account
        BankAccount userAccount = new BankAccount(1000);

        // Step 5: Connect the ATM class with the user's bank account class
        SimpleATM atm = new SimpleATM(userAccount);

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            // Display menu
            System.out.println("\n1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Check Balance");
            System.out.println("0. Exit");

            // Get user choice
            System.out.print("Enter your choice: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid choice.");
                System.out.print("Enter your choice: ");
                scanner.next(); // consume the invalid input
            }
            choice = scanner.nextInt();

            // Perform action based on user choice
            switch (choice) {
                case 1:
                    double withdrawAmount = atm.validateAmountInput();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    double depositAmount = atm.validateAmountInput();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    atm.checkBalance();
                    break;
                case 0:
                    System.out.println("Exiting ATM. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
