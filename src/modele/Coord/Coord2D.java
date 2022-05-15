package modele.Coord;

import java.util.Random;

import modele.Direction;
import modele.Grille.Grille2D;

public class Coord2D implements Coord,Cloneable {
    private int x, y;
    private static Random r = new Random();

    private Coord2D(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Coord2D clone(){
        return new Coord2D(this.x, this.y);
    }
    
    public static Coord2D getInstance(int x, int y) throws IllegalArgumentException{
        Coord2D res = new Coord2D(x, y);
        boolean test = true;
        try {
            test = Grille2D.isCoordCorrect(res.getX(), res.getY());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!test) throw new IllegalArgumentException(" Coordonnées incorrectes ! ");
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        Coord2D c = (Coord2D) obj;
        return c.x == x && c.y == y;
    }

    @Override
    public int hashCode() {
        return x * y;
    }



    @Override
    public Coord getVoisin(Direction dir){
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

        try {
            if(Grille2D.isCoordCorrect(c.getX(),c.getY())) return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Coord2D [x=" + x + ", y=" + y + "]";
    }

    public static Coord2D rand() {
        int row = r.nextInt()%Grille2D.getSize(), col = r.nextInt()%Grille2D.getSize();
        if(row <  0) row = -row; if(col < 0) col = -col;
        return new Coord2D(row, col);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws Exception {
        this.x = x;
        if(!Grille2D.isCoordCorrect(getX(),getY())) throw new IllegalArgumentException(" x passed not in range [0;" + Grille2D.getSize() + "["); 
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws Exception {
        this.y = y;
        if(!Grille2D.isCoordCorrect(getX(),getY())) throw new IllegalArgumentException(" y passed not in range [0;" + Grille2D.getSize() + "[");  
    }
}