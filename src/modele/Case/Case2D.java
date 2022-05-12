package modele.Case;

import modele.Direction;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Case2D implements Case {
    protected int valeur;
    protected Coord2D coord;

    public Case2D(int _valeur, Coord2D _coord) {
        valeur = _valeur;
        this.coord = _coord;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int _valeur) {
        valeur = _valeur;
    }

    public Coord2D getCoord() {
        return coord;
    }

    public void setCoord(Coord2D coord) {
        if (coord != null)
            this.coord = coord;
    }

    @Override
    public void move(Direction d) {
        Grille2D g = Coord2D.getG(); // La grille
        Case2D neighbor = g.getVoisin(this, d); // Le voisin s'il existe

        if (neighbor != null) { // S'il existe un voisin
            if (neighbor.getValeur() == valeur) { // Si ce voisin a la même valeur
                neighbor.setValeur(2 * valeur);
                g.rmCase(this.coord);
            }
        }
        else { // Sinon la place est libre
            
            Coord2D coord_dest = null;

            coord_dest = (Coord2D) coord.getVoisin(d);

            if (coord_dest != null) {
                g.rmCase(coord); // Supprime l'entrée correspondant à cette case
                coord = coord_dest; // On actualise les coordonnées de cette case
                g.setCase(coord, this); // On ajoute la nouvelle entrée
                this.move(d);
            }
        }
    }

}
