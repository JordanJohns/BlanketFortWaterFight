import java.util.*;

import Model.*;

public class UserInterface {
    private Menu mainMenu;
    private GameManager gameManager;


    public void startGame(int numEnemies) {
        try {
            gameManager = new GameManager(numEnemies);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        Menu.MenuOption[] mainMenuOptions = new Menu.MenuOption[]{
            new Menu.MenuOption("Option 1", null),
            new Menu.MenuOption("Option 2", null),
        };
        mainMenu = new Menu(mainMenuOptions);

        System.out.println("Starting game with " + numEnemies + " enemies...");
        GameLoop();
    }

    private void GameLoop() {
        displayMap();
        System.out.println("Opponent's points: 0"); // TODO

        while (gameManager.getEnemiesRemaining() > 0) {
            gameManager.handleShot(getShootPos());
            gameManager.handleAI();
        }
    }

    private Coordinate getShootPos() {
        Scanner scanner = new Scanner(System.in);

        Coordinate shootPos = null;
        do {
            System.out.print("Enter the square you want to shoot: ");
            String input = scanner.nextLine();

            if (input.isEmpty() || input.length() < 2) {
                continue;
            }

            input = input.toLowerCase();

            int x = (int)(input.charAt(0) - 'a') + 1;
            int y;
            if (input.length() >= 3 && input.charAt(2) == '0') {
                // Special case because 10 is two digits
                y = 10;
            } else {
                y = (int)(input.charAt(1) - '0');
            }

            if (x < 1 || x > 10 || y < 1 || y > 10) {
                System.out.println("Invalid Coordinate, please try again. (" + input + ", " + x + ", " + y + ")" );
                continue;
            }

            shootPos = new Coordinate(x, y);
        } while (shootPos == null);

        return shootPos;
    }

    private void displayMap() {
        // TODO: This is a placeholder
        System.out.println("Game Board:");
        System.out.println("    1  2  3  4  5  6  7  8  9 10");
        System.out.println("  A ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  B ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  C ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  D ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  E ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  F ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  G ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  H ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  I ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
        System.out.println("  J ~  ~  ~  ~  ~  ~  ~  ~  ~  ~");
    }
}
