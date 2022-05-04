package modele;

public class Coord {
    int x,y;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coord c = (Coord) obj;
        // TODO Auto-generated method stub
        return c.x == x && c.y == y;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return x * y;
    }    
}
