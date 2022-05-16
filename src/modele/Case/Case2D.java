package modele.Case;

import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Case2D extends Case{

    public Case2D(int _valeur, Coord2D _coord, Grille2D g) {
        super(_valeur, _coord, g);
    }

    @Override
    public Case2D clone(){
        return new Case2D(valeur, ((Coord2D)coord).clone(), (Grille2D)g);
    }

    @Override
    public String toString() {
        return "Case2D [ value : " + valeur + "," + coord.toString() + "]";
    }
}
