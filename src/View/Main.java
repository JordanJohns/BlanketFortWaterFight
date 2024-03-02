package View;

/**
 * Starts the program.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) args = new String[] {"5"};    // Default to 5 enemies if not given any args
        UserInterface userInterface = new UserInterface();

        boolean isCheatMode = (args.length >= 2) ? (args[1] == "--cheat") : false;

        userInterface.startGame(Integer.parseInt(args[0]), isCheatMode);
    }
}