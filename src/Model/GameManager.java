package Model;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    private final int MAP_SIZE = 10;

    private char[][] map;
    private char[][] revealedMap;
    
    private List<Fort> enemies = new ArrayList<>();
    private int enemyPoints = 0;

    public GameManager(int numEnemies) throws Exception {
        map = generateMap('.');
        revealedMap = generateMap('~');

        char currentEnemyLetter = 'A';
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

    private char[][] generateMap(char letterToFill) {
        char[][] map = new char[MAP_SIZE][MAP_SIZE];
        for (int x = 0; x < MAP_SIZE; x++) {
            for (int y = 0; y < MAP_SIZE; y++) {
                map[x][y] = letterToFill;
            }
        }
        return map;
    }

    private Fort setupFort(char letter) {
        Fort fort = new Fort(letter);
        map = fort.generateFort(map);
        return fort;
    }

    public void handlePlayerShot(Coordinate shootPos) {
        for (Fort enemy : enemies) {
            if (enemy.checkHit(shootPos)) {
                changeMap(shootPos, (char)(enemy.getLetter() + 32));    // char + 32 is lowercase of that letter
            }
        }

        revealPosition(shootPos);
    }

    private void changeMap(Coordinate pos, char newLetter) {
        map[pos.getX()][pos.getY()] = newLetter;
    }

    private void revealPosition(Coordinate position) {
        char letterToReveal = map[position.getX()][position.getY()];
        if (letterToReveal == '.') {
            letterToReveal = ' ';
        } 
        else {
            letterToReveal = 'X';
        }

        revealedMap[position.getX()][position.getY()] = letterToReveal;
    }

    public void handleAI() {
        // TODO: Make the AI take their turns
        //       (Probably just shoot randomly?)
    }

    public boolean isValidPlayerShootPos(Coordinate shootPos) {
        return true;
    }

    // --------------- Getters and Setters ---------------

    public char[][] getMap(boolean isCheatMode) {
        if (isCheatMode) {
            return map;
        }

        return revealedMap;
    }

    public int getEnemiesRemaining() {
        return enemies.size();
    }

    public int getMapSize() {
        return this.MAP_SIZE;
    }

    public boolean enemiesHaveWon() {
        return enemyPoints >= 2500;
    }

    public int getEnemyPoints() {
        return enemyPoints;
    }
}
