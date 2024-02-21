import java.util.*;

import Model.*;

public class UserInterface {
    private Menu mainMenu;
    private GameManager gameManager;


    public void startGame() {
        int numEnemies = getNumEnemies();

        Menu.MenuOption[] mainMenuOptions = new Menu.MenuOption[]{
            new Menu.MenuOption("Option 1", null),
            new Menu.MenuOption("Option 2", null),
        };
        mainMenu = new Menu(mainMenuOptions);

        gameManager.setupGame(numEnemies);

        GameLoop();
    }

    private void GameLoop() {
        while (gameManager.getEnemiesRemaining() > 0) {
            getShootPos();
        }
    }

    private Vector<Integer> getShootPos() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the square you want to shoot: ");
        String input = scanner.nextLine();
        int x = input.charAt(0);
        int y = input.charAt(1);

        return new Vector<Integer>(x, y);
    }

    private int getNumEnemies() {
        return 5;
    }

    private void displayMap() {

    }
}
