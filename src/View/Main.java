public class Main {
    public static void main(String[] args) {
        if (args.length == 0) args = new String[] {"5"};    // Default to 5 enemies if not given any args
        UserInterface userInterface = new UserInterface();
        userInterface.startGame(Integer.parseInt(args[0]));
    }
}