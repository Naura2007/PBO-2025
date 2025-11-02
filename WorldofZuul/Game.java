import java.util.*;

public class Game {
    private Parser parser;
    private Player player;
    private Room startRoom;

    public Game() {
        createRooms();
        parser = new Parser();
    }

    private void createRooms() {
        Room hallway, lab, researchRoom, trapRoom, storage;

        // Create rooms
        hallway = new Room("in the campus hallway", "You see some posters on the wall.");
        lab = new Room("in the computer lab", "You see rows of computers here.");
        researchRoom = new Room("in the research room", "A quiet room filled with papers.");
        trapRoom = new Room("in the restricted lab", "An area with loud alarms!");
        storage = new Room("in the storage room", "There are boxes stacked around.");

        // Set exits
        hallway.setExit("north", lab);
        hallway.setExit("east", storage);
        lab.setExit("south", hallway);
        lab.setExit("north", researchRoom);
        researchRoom.setExit("south", lab);
        storage.setExit("west", hallway);
        lab.setExit("east", trapRoom);
        trapRoom.setExit("west", lab);

        // Add items
        hallway.addItem(new Item("notebook", "A student's notebook.", false));
        lab.addItem(new Item("usb", "A USB drive that might contain something important.", true));
        researchRoom.addItem(new Item("report", "A hidden research report.", false));

        // Mark trap room
        trapRoom.setTrap(true);

        // Starting room
        player = new Player(hallway);
        startRoom = hallway;
    }

    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            System.out.print("> ");
            Command command = parser.getCommand();
            finished = processCommand(command);
        }

        System.out.println("Thank you for playing World of Zuul!");
    }

    private void printWelcome() {
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a simple text adventure game.");
        System.out.println("Type 'help' if you need assistance.\n");
        System.out.println(player.getCurrentRoom().getLongDescription());
    }

    private boolean processCommand(Command command) {
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case GO:
                goRoom(command);
                break;
            case LOOK:
                lookAround();
                break;
            case TAKE:
                takeItem(command);
                break;
            case INVENTORY:
                player.showInventory();
                break;
            case USE:
                useItem(command);
                break;
            case QUIT:
                return true;
            default:
                System.out.println("I don't know what you mean...");
                break;
        }
        return false;
    }

    private void printHelp() {
        System.out.println("You are lost. You wander around the campus.");
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
        Room nextRoom = player.getCurrentRoom().getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door that way!");
        } else {
            player.setCurrentRoom(nextRoom);

            if (nextRoom.hasTrap()) {
                System.out.println("Warning! You stepped into a restricted lab!");
                System.out.println("Security alarms ring and you are moved back to the hallway!");
                player.setCurrentRoom(startRoom);
            } else {
                System.out.println(player.getCurrentRoom().getLongDescription());
            }
        }
    }

    private void lookAround() {
        Room current = player.getCurrentRoom();
        System.out.println(current.getLongDescription());
        if (!current.getItems().isEmpty()) {
            System.out.println("You see:");
            for (Item i : current.getItems()) {
                System.out.println(" - " + i.getName());
            }
        }
    }

    private void takeItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Take what?");
            return;
        }

        String itemName = command.getSecondWord();
        Item item = player.getCurrentRoom().takeItem(itemName);

        if (item == null) {
            System.out.println("That item isn't here.");
        } else {
            player.addItem(item);
            System.out.println("You took the " + item.getName() + ".");
        }
    }

    private void useItem(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Use what?");
            return;
        }

        String itemName = command.getSecondWord();

        if (player.hasItem(itemName)) {
            if (itemName.equalsIgnoreCase("usb")) {
                System.out.println("You plug in the USB drive and unlock the lab’s main door!");
            } else {
                System.out.println("You used the " + itemName + ", but nothing happens.");
            }
        } else {
            System.out.println("You don't have that item.");
        }
    }
}
