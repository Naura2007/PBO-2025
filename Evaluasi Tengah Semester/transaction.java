/**
 * Write a description of class transaction here.
 *
 * @author Naura Rossa Azalia
 * @version 14-10-2025
 */
import java.time.LocalDateTime;

public class Transaction {
    private Coffee coffee;
    private double amount;
    private LocalDateTime time;

    public Transaction(Coffee coffee, double amount) {
        this.coffee = coffee;
        this.amount = amount;
        this.time = LocalDateTime.now();
    }

    public String toString() {
        return time + " - " + coffee.toString() + " : Rp" + amount;
    }
}
