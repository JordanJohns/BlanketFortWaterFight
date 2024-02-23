package Model;

import java.util.random.*;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Polyomino {
    final int SIZE = 5;
    private Coordinate[] positions;
    public Polyomino(char[][] map){
        Coordinate startingPosition = findStartingPosition(map);
        for(int i = 0; i < SIZE; i++){
            
        }
    }
    /*
    public void generate(char[] map, char letter){
        
    }
    */
    public Coordinate findStartingPosition(char[][] map){
        int x;
        int y;
        Coordinate position;
        int testBreakNum = 0;
        while(true){
            x = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            y = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            if(map[x][y] != ' '){
                position = new Coordinate(x, y);
                break;
            }
            testBreakNum++;
            if(testBreakNum >= 100){
                System.out.println("Infinite loop");
                position = new Coordinate(0, 0);
                break;
            }
        }
        return position;
    }

    public boolean Contains(Coordinate position) {
        for (int i = 0; i < positions.length; i++) {
            if (position.equals(positions[i])) {
                return true;
            }
        }
        return false;
    }
}
