/**
 * Write a description of class coffemachine here.
 *
 * @author Naura Rossa Azalia
 * @version 14-10-2025
 */
import java.util.ArrayList;
import java.util.Scanner;

public class CoffeeMachine {
    private int stockCoffee = 10;
    private int stockMilk = 10;
    private int stockSugar = 10;
    private int stockWater = 10;
    private ArrayList<Transaction> log = new ArrayList<>();

    private Admin admin = new Admin("admin", "1234"); // username & password

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== VENDING COFFEE MACHINE ===");
            System.out.println("1. User Mode (Buy Coffee)");
            System.out.println("2. Admin Mode (Login)");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            int menu = sc.nextInt();

            switch (menu) {
                case 1: userMode(); break;
                case 2: adminMode(); break;
                case 3:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    // ====================== MODE USER ======================
    private void userMode() {
        Scanner sc = new Scanner(System.in);

        if (stockCoffee <= 0 || stockWater <= 0) {
            System.out.println("Out of stock! Please wait for refill.");
            return;
        }

        System.out.println("\n=== MENU ===");
        System.out.println("1. Espresso\n2. Latte\n3. Cappuccino");
        System.out.print("Choose coffee: ");
        int choice = sc.nextInt();

        String name = (choice == 1) ? "Espresso" :
                      (choice == 2) ? "Latte" : "Cappuccino";

        System.out.print("Choose size (Small/Medium/Large): ");
        String size = sc.next();

        System.out.print("Add sugar? (true/false): ");
        boolean sugar = sc.nextBoolean();

        System.out.print("Add milk? (true/false): ");
        boolean milk = sc.nextBoolean();

        Coffee coffee = new Coffee(name, size, sugar, milk);
        System.out.println("Total: Rp" + coffee.getPrice());

        System.out.print("Insert payment: ");
        double pay = sc.nextDouble();

        Payment payment = new Payment(coffee.getPrice());
        if (payment.pay(pay)) {
            System.out.println("Payment successful. Making your coffee...");
            makeCoffee(sugar, milk);
            Transaction t = new Transaction(coffee, coffee.getPrice());
            log.add(t);
            System.out.println("Enjoy your " + coffee.toString() + "!");
            System.out.println("Change: Rp" + payment.getChange());
        } else {
            System.out.println("Payment failed. Not enough money.");
        }
    }

    private void makeCoffee(boolean sugar, boolean milk) {
        stockCoffee--;
        stockWater--;
        if (sugar) stockSugar--;
        if (milk) stockMilk--;
    }

    // ====================== MODE ADMIN ======================
    private void adminMode() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nEnter admin username: ");
        String user = sc.next();
        System.out.print("Enter password: ");
        String pass = sc.next();

        if (admin.login(user, pass)) {
            System.out.println("Login successful.");
            adminMenu();
        } else {
            System.out.println("Access denied. Wrong credentials.");
        }
    }

    private void adminMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== ADMIN MENU ===");
            System.out.println("1. View Stock");
            System.out.println("2. Refill Stock");
            System.out.println("3. View Transaction Log");
            System.out.println("4. Logout");
            System.out.print("Choose option: ");
            int option = sc.nextInt();

            switch (option) {
                case 1: viewStock(); break;
                case 2: refillStock(); break;
                case 3: showLog(); break;
                case 4:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private void viewStock() {
        System.out.println("\n=== CURRENT STOCK ===");
        System.out.println("Coffee: " + stockCoffee);
        System.out.println("Milk: " + stockMilk);
        System.out.println("Sugar: " + stockSugar);
        System.out.println("Water: " + stockWater);
    }

    private void refillStock() {
        stockCoffee = 10;
        stockMilk = 10;
        stockSugar = 10;
        stockWater = 10;
        System.out.println("All stock refilled to maximum.");
    }

    private void showLog() {
        System.out.println("\n=== TRANSACTION LOG ===");
        if (log.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : log) {
                System.out.println(t);
            }
        }
    }
}
