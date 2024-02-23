package Model;

import java.util.List;

public class Fort {
    private char letter;
    private int points;
    private List<Coordinate> damagedSections;
    private Polyomino polyomino;

    public char getLetter() {
        return this.letter;
    }

    public boolean checkHit(Coordinate shootPos) {
        if (!polyomino.Contains(shootPos)) {
            return false;
        }

        damagedSections.add(shootPos);
        return true;
    }

    public Fort(char letter) {
        this.letter = letter;
    }

    public char[][] generateFort(char[][] map) {
        return null;    // TODO: will return map + new polyomino
    }
}
