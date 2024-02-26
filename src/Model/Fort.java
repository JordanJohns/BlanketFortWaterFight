package Model;

import java.util.List;

public class Fort {
    private char letter;
    private int points;
    private List<Coordinate> damagedSections;
    private Polyomino polyomino;

    public Fort(char letter) {
        this.letter = letter;
    }

    public char getLetter() {
        return this.letter;
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
        Polyomino polyomino = new Polyomino(map, letter);
        return map;
    }
}
