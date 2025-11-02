import java.util.*;

public class Room {
    private String description;
    private Map<String, Room> exits;
    private List<Item> items;
    private boolean isTrap;

    public Room(String name, String description) {
        this.description = name + ". " + description;
        this.exits = new HashMap<>();
        this.items = new ArrayList<>();
        this.isTrap = false;
    }

    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    public Room getExit(String direction) {
        return exits.get(direction);
    }

    public String getLongDescription() {
        return "You are " + description + "\nExits: " + String.join(" ", exits.keySet());
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item takeItem(String name) {
        for (Iterator<Item> it = items.iterator(); it.hasNext();) {
            Item i = it.next();
            if (i.getName().equalsIgnoreCase(name)) {
                it.remove();
                return i;
            }
        }
        return null;
    }

    public void setTrap(boolean trap) {
        this.isTrap = trap;
    }

    public boolean hasTrap() {
        return isTrap;
    }
}
