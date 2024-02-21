package Model;

import java.util.Vector;

public class GameManager {
    private char[][] map;
    private char[][] revealedMap;
    
    private int enemiesRemaining;

    public void setupGame(int numEnemies) {
        
    }

    public void handleShot(Vector<Integer> position) {

    }

    private void changeMap(Vector<Integer> position) {

    }

    private void handleAI() {

    }

    // --------------- Getters and Setters ---------------

    public char[][] getMap(boolean isRevealed) {
        if (isRevealed) {
            return revealedMap;
        }

        return map;
    }

    public int getEnemiesRemaining() {
        return enemiesRemaining;
    }
}
