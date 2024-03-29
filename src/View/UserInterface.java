package View;

import java.util.*;

import Model.*;


/**
 * Runs the game. Gets player input and passes it to the GameManager.
 */
public class UserInterface {
    private GameManager gameManager;

    public void startGame(int numEnemies, boolean isCheatMode) {
        try {
            gameManager = new GameManager(numEnemies);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        System.out.println("Starting game with " + numEnemies + " enemies...\n");
        GameLoop(isCheatMode);
    }

    private void GameLoop(boolean isCheatMode) {
        while (gameManager.getEnemiesRemaining() > 0 && !gameManager.enemiesHaveWon()) {
            displayMap(isCheatMode);
            System.out.println("Opponent's points: " + gameManager.getEnemyPoints() + "/2500");

            gameManager.handlePlayerShot(getShootPos());
            gameManager.handleAI();
        }

        if (gameManager.getEnemiesRemaining() > 0) {
            System.out.println("Your fort is all wet! You lost!");
        } else {
            System.out.println("You have soaked all of your enemies! you win!");
        }
        displayMap(true);
    }

    private Coordinate getShootPos() {
        Scanner scanner = new Scanner(System.in);

        Coordinate shootPos = null;
        boolean isValidPlayerShootPos = false;
        do {
            System.out.print("Enter the square you want to shoot: ");
            String input = scanner.nextLine();

            if (input.isEmpty() || input.length() < 2) {
                continue;
            }

            shootPos = parseInput(input);

            isValidPlayerShootPos = gameManager.isValidPlayerShootPos(shootPos);
            if (!isValidPlayerShootPos) {
                System.out.println("Invalid shoot position!");
            }
        } while (shootPos == null || !isValidPlayerShootPos);

        return shootPos;
    }

    private Coordinate parseInput(String input) {
        int y = input.charAt(0) - 'a' + 1;
        int x = Character.isDigit(input.charAt(1)) ? input.charAt(1) - '0' : 10;

        if (x < 1 || x > 10 || y < 1 || y > 10) {
            return null;
        }

        return new Coordinate(x - 1, y - 1);
    }

    private void displayMap(boolean isCheatMode) {
        System.out.println("Game Board:");
        System.out.print("     1  2  3  4  5  6  7  8  9 10");

        int mapSize = gameManager.getMapSize();
        char[][] map = gameManager.getMap(isCheatMode);

        char currentRow = 'A'; 
        for (int y = 0; y < mapSize; y++) {
            System.out.print("\n  " + currentRow);
            for (int x = 0; x < mapSize; x++) {
                System.out.print("  " + map[x][y]);
            }
            currentRow = (char)(currentRow + 1);
        }

        System.out.println();
    }
}
