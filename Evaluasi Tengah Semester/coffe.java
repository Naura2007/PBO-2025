
/**
 * Write a description of class coffe here.
 *
 * @author Naura Rossa Azalia 
 * @version 14-10-2025
 */
public class Coffee {
    private String name;
    private String size;
    private boolean addSugar;
    private boolean addMilk;
    private double price;

    public Coffee(String name, String size, boolean addSugar, boolean addMilk) {
        this.name = name;
        this.size = size;
        this.addSugar = addSugar;
        this.addMilk = addMilk;
        calculatePrice();
    }

    private void calculatePrice() {
        switch (size) {
            case "Small": price = 10000; break;
            case "Medium": price = 15000; break;
            case "Large": price = 20000; break;
            default: price = 10000; break;
        }

        if (addSugar) price += 1000;
        if (addMilk) price += 2000;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + " (" + size + ")" +
               (addSugar ? " + sugar" : "") +
               (addMilk ? " + milk" : "");
    }
}
