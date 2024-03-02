package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * The fort for each enemy. Store location, letter, and damage the fort takes during the game.
 */
public class Fort {
    private char letter;

    private List<Coordinate> damagedSections;
    private Polyomino polyomino;

    public Fort(char letter) {
        damagedSections = new ArrayList<Coordinate>();
        this.letter = letter;
    }

    public char getLetter() {
        return this.letter;
    }

    public int getPointsPerHit() {
        if (damagedSections.size() <= 1) {
            return 20;
        }
        else if (damagedSections.size() == 2) {
            return 5;
        }
        else {
            return (5 - damagedSections.size());
        }
    }

    public boolean checkHit(Coordinate shootPos) {
        if (!polyomino.contains(shootPos)) {
            return false;
        }

        damagedSections.add(shootPos);
        return true;
    }
    
    public char[][] generateFort(char[][] map) {
        //Constructor of Polyomino generates the polyomino and fills the map given
        this.polyomino = new Polyomino(map, letter);

        for (Coordinate position : polyomino.getPositions()) {
            map[position.getX()][position.getY()] = letter;
        }

        return map;
    }

    // ----- Getters -----

    public List<Coordinate> getPositions() {
        return polyomino.getPositions();
    }

    public boolean contains(Coordinate position) {
        return polyomino.contains(position);
    }

    public boolean isDestroyed() {
        return damagedSections.size() >= 5;
    }
}
