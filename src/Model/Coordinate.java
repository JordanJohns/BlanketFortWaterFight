package Model;

public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public Coordinate(Coordinate coordinate){
        this.x = coordinate.getX();
        this.y = coordinate.getY();
    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Coordinate otherPos) {
        return (otherPos.x == this.x && otherPos.y == this.y);
    }
}
