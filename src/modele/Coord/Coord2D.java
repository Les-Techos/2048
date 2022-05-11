package modele.Coord;

import modele.Direction;
import modele.Jeu;

public class Coord2D implements Coord {
    private int x, y;
    private static Jeu j;

    public Coord2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Coord2D c = (Coord2D) obj;
        // TODO Auto-generated method stub
        return c.x == x && c.y == y;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return x * y;
    }

    @Override
    public Coord getVoisin(Direction dir) throws Exception {

        if (j == null)
            throw new Exception("J pointer is null");

        Coord2D c = null;
        switch (dir) {
            case droite:
                c = new Coord2D(this.x, this.y + 1);
                break;
            case gauche:
                c = new Coord2D(this.x, this.y - 1);
                break;
            case haut:
                c = new Coord2D(this.x - 1, this.y);
                break;
            case bas:
                c = new Coord2D(this.x + 1, this.y);
                break;
        }

        if(c.x >= 0 && c.x < j.getSize() && c.y >= 0 && c.y < j.getSize()) return c;
        else return null;
        
    }

    public static Jeu getJ() {
        return j;
    }

    public static void setJ(Jeu j) {
        Coord2D.j = j;
    }

    @Override
    public String toString() {
        return "Coord2D [x=" + x + ", y=" + y + "]";
    }
}
