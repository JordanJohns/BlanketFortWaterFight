package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Polyomino {
    private final int SIZE = 5;
    private final int BREAK_POINT = 100;
    private List<Coordinate> positions = new ArrayList<>();
    private char[][] map;
    private char letter;

    public Polyomino(char[][] map, char letter) {
        this.map = map;
        this.letter = letter;
        generate();
    }

    private void generate() {
        boolean validPositionIsFound = false;
        int numTimesInLoop = 0;
        while(!validPositionIsFound){
            Coordinate startingPosition = findStartingPosition();
            positions.add(new Coordinate(startingPosition));
            int x = startingPosition.getX();
            int y = startingPosition.getY();
            map[x][y] = letter;
            for (int i = 1; i < SIZE; i++) {
                Coordinate newPosition = findNewPosition();
                if (newPosition != null) {
                    positions.add(newPosition);
                    x = newPosition.getX();
                    y = newPosition.getY();
                    map[x][y] = letter;
                    validPositionIsFound = true;
                } else {
                    validPositionIsFound = false;
                    removeFromMap();
                    break;
                }
            }
            if(numTimesInLoop >= BREAK_POINT){
                throw new RuntimeException("ERROR: No space in map for another Polyomino");
            }
            numTimesInLoop++;
        }
    }

    private Coordinate findStartingPosition() {
        int x;
        int y;
        Coordinate position;
        int numTimesInLoop = 0;
        while (true) {
            x = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            y = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            if (map[x][y] == '.') {
                position = new Coordinate(x, y);
                break;
            }
            numTimesInLoop++;
            if (numTimesInLoop >= BREAK_POINT) {
                throw new RuntimeException("ERROR: No space in map for another Polyomino");
            }
        }
        return position;
    }

    private Coordinate findNewPosition() {
        Coordinate selectedPosition;
        int numTimesInLoop = 0;
        while (true) {
            if (positions.size() > 1) {
                int randNum = ThreadLocalRandom.current().nextInt(0, positions.size() - 1);
                selectedPosition = positions.get(randNum);
            } else {
                selectedPosition = positions.get(0);
            }
            int x = selectedPosition.getX();
            int y = selectedPosition.getY();
            if (validPosition(x - 1, y)) {
                selectedPosition = new Coordinate(x - 1, y);
                break;
            } else if (validPosition(x, y - 1)) {
                selectedPosition = new Coordinate(x, y - 1);
                break;
            } else if (validPosition(x + 1, y)) {
                selectedPosition = new Coordinate(x + 1, y);
                break;
            } else if (validPosition(x, y + 1)) {
                selectedPosition = new Coordinate(x, y + 1);
                break;
            }

            if (numTimesInLoop >= BREAK_POINT) {
                selectedPosition = null;
                break;
            }
            numTimesInLoop++;
        }
        return selectedPosition;
    }

    private boolean validPosition(int x, int y) {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            return false;
        }
        if (map[x][y] != '.') {
            return false;
        }
        return true;
    }

    private void removeFromMap(){
        int x;
        int y;
        positions.stream().forEach(s -> map[s.getX()][s.getY()] = '.');
        positions.clear();
    }

    public boolean contains(Coordinate position) {
        int numOfThatPosition = (int)positions.stream().filter(s -> s.equals(position)).count();
        if(numOfThatPosition >= 1){
            return true;
        }
        return false;
    }

    public List<Coordinate> getPositions() {
        return this.positions;
    }
}
