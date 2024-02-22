package Model;

import javax.vecmath.Vector2d;
import java.util.random.*;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class Polyomino {
    final int SIZE = 5;
    private Vector2d[] positions;
    public Polyomino(char[][] map, char letter){
        Vector2d startingPosition = findStartingPosition(map);
        for(int i = 0; i < SIZE; i++){
            
        }
    }
    /*
    public void generate(char[] map, char letter){
        
    }
    */
    public Vector2d findStartingPosition(char[][] map){
        int x;
        int y;
        Vector2d position;
        int testBreakNum = 0;
        while(true){
            x = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            y = ThreadLocalRandom.current().nextInt(0, map.length - 1);
            if(map[x][y] != ' '){
                position = new Vector2d(x, y);
                break;
            }
            testBreakNum++;
            if(testBreakNum >= 100){
                System.out.println("Infinite loop");
                position = new Vector2d(0, 0);
                break;
            }
        }
        return position;
    }
}
