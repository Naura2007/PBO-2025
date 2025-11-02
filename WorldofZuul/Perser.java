import java.util.Scanner;

public class Parser {
    private Scanner reader;

    public Parser() {
        reader = new Scanner(System.in);
    }

    public Command getCommand() {
        String inputLine;
        String word1 = null;
        String word2 = null;

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }

        CommandWord commandWord;
        try {
            commandWord = CommandWord.valueOf(word1.toUpperCase());
        } catch (Exception e) {
            commandWord = CommandWord.UNKNOWN;
        }

        return new Command(commandWord, word2);
    }

    public void showCommands() {
        System.out.println("go look take inventory use help quit");
    }
}
