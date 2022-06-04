package modele.Coord;

import java.io.Serializable;
import java.util.Random;

import modele.Direction.Direction2D;
import modele.Grille.Grille;

public abstract class Coord implements Cloneable, Serializable {
    protected static Random r = new Random();
    Grille g = null;

    @Override
    public abstract Coord clone();
    
    public abstract Coord getVoisin(Direction2D dir);
    
    @Override
    public abstract boolean equals(Object obj);

    @Override
    public abstract int hashCode();

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
