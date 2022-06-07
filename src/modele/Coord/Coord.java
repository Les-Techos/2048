package modele.Coord;

import java.io.Serializable;
import java.util.Random;

import modele.Direction.Direction2D;
import modele.Grille.Grille;

public abstract class Coord implements Cloneable, Serializable {
    protected static Random r = new Random();
    Grille g = null; // Grille dans laquelle repère la coordonnée

    @Override
    public abstract Coord clone();
    
    /**
     * Obtient le voisin de la coordonnée courant dans la direction dir
     * @param dir
     * @return
     */
    public abstract Coord getVoisin(Direction2D dir);
    
    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

    /**
     * Vérifie si la coordonnée est correcte vis-à-vis de la grille g
     * @return
     */
    public boolean isCorrect(){
        return g.checkCoord(this);
    }

    @Override
    public abstract String toString();

    public Grille getG() {
        return g;
    }

    public void setG(Grille g) {
        this.g = g;
    }
}
