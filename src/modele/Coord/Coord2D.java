package modele.Coord;

import modele.Direction.Direction2D;
import modele.Grille.Grille2D;

public class Coord2D extends Coord {
    private int x, y;
    
    /**
     * 
     * @param x Ligne
     * @param y Colonne
     * @param g Grille
     */
    private Coord2D(int x, int y, Grille2D g){
        this.x = x;
        this.y = y;
        this.g = g;
    }
    
    /**
     * Génère une coordonnées
     * @param x Ligne
     * @param y Colonne
     * @param g Grille
     * @return
     * @throws IllegalArgumentException
     */
    public static Coord2D getInstance(int x, int y, Grille2D g) throws IllegalArgumentException{
        Coord2D res = new Coord2D(x, y, g);
        boolean test = true;
        try {
            test = g.checkCoord(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!test) throw new IllegalArgumentException(" Coordonnées incorrectes ! ");
        return res;
    }

    @Override
    public Coord2D clone(){
        return new Coord2D(this.x, this.y, (Grille2D)this.g);
    }
    
    /**
     * Génère une coordonnée aléatoire dans relatif à la grille g
     * @param g
     * @return
     */
    public static Coord2D rand(Grille2D g) {
        int row = r.nextInt()%g.getSize(), col = r.nextInt()%g.getSize();
        if(row <  0) row = -row; if(col < 0) col = -col;
        return new Coord2D(row, col, g);
    }
    
    @Override
    public Coord getVoisin(Direction2D dir){
        Coord2D c = null;
        Grille2D g = (Grille2D) this.g;
        switch (dir) {
            case droite:
                c = new Coord2D(this.x, this.y + 1, g);
                break;
            case gauche:
                c = new Coord2D(this.x, this.y - 1, g);
                break;
            case haut:
                c = new Coord2D(this.x - 1, this.y, g);
                break;
            case bas:
                c = new Coord2D(this.x + 1, this.y, g);
                break;
        }

        try {
            if(g.checkCoord(c)) return c;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
    public String toString() {
        return "Coord2D [x=" + x + ", y=" + y + "]";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) throws Exception {
        this.x = x;
        if(!g.checkCoord(this)) throw new IllegalArgumentException(" x passed not in range [0;" + g.getSize() + "["); 
    }

    public int getY() {
        return y;
    }

    public void setY(int y) throws Exception {
        this.y = y;
        if(!g.checkCoord(this)) throw new IllegalArgumentException(" y passed not in range [0;" + g.getSize() + "[");  
    }
}