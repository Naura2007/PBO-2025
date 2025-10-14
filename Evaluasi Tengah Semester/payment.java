/**
 * Write a description of class payment here.
 *
 * @author Naura Rossa Azalia
 * @version 14-10-2025
 */
public class Payment {
    private double total;
    private double paid;

    public Payment(double total) {
        this.total = total;
    }

    public boolean pay(double amount) {
        paid = amount;
        return paid >= total;
    }

    public double getChange() {
        return paid - total;
    }
}
