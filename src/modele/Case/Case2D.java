package modele.Case;

import modele.Coord.Coord2D;

public class Case2D implements Case{
    protected int valeur;
    protected Coord2D coord;

    public Case2D(int _valeur, Coord2D _coord) {
        valeur = _valeur;
        this.coord = _coord;
    }

    public int getValeur() {
        return valeur;
    }

    public Coord2D getCoord() {
        return coord;
    }

    public void setCoord(Coord2D coord) {
        this.coord = coord;
    }

}
