package Model;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private char[][] map;
    private char[][] revealedMap;
    
    private Fort player;
    private List<Fort> enemies = new ArrayList<>();

    public GameManager(int numEnemies) throws Exception {
        player = setupFort('A');

        char currentEnemyLetter = 'B';
        for (int i = 0; i < numEnemies; i++) {
            Fort enemy = setupFort(currentEnemyLetter);
            if (enemy == null) 
            {
                throw new Exception("Unable to place " + numEnemies + " enemies on the board. Try again with a smaller number.");
            } 
            enemies.add(enemy);
            currentEnemyLetter += 1;
        }
    }

    private Fort setupFort(char letter) {
        Fort fort = new Fort(letter);
        map = fort.generateFort(map);
        return fort;
    }

    public void handleShot(Coordinate shootPos) {
        for (Fort enemy : enemies) {
            if (enemy.checkHit(shootPos)) {
                changeMap(shootPos, (char)(enemy.getLetter() + 32));    // char + 32 is lowercase of that letter
            }
        }
    }

    private void changeMap(Coordinate pos, char newLetter) {
        map[pos.getX()][pos.getY()] = newLetter;
    }

    public void handleAI() {
        // TODO: Make the AI take their turns
        //       (Probably just shoot randomly?)
    }

    // --------------- Getters and Setters ---------------

    public char[][] getMap(boolean isRevealed) {
        if (isRevealed) {
            return revealedMap;
        }

        return map;
    }

    public int getEnemiesRemaining() {
        return enemies.size();
    }
}
