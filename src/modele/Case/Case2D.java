package modele.Case;

import modele.Direction;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Case2D implements Case,Cloneable{
    protected int valeur;
    protected Coord2D coord;
    private Grille2D g;

    public Case2D(int _valeur, Coord2D _coord, Grille2D g) {
        valeur = _valeur;
        this.g = g;
        this.coord = _coord;
    }

    @Override
    public Case2D clone(){
        return new Case2D(valeur,(Coord2D) coord.clone(), g);
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
        Case2D neighbor = g.getVoisin(this, d); // Le voisin s'il existe

        if (neighbor != null) { // S'il existe un voisin
            if (neighbor.getValeur() == valeur) { // Si ce voisin a la même valeur
                Coord2D neighbor_coord = neighbor.getCoord();
                
                setValeur(2 * valeur);

                g.rmCase(neighbor_coord);

                g.rmCase(coord);
                setCoord(neighbor_coord);
                g.setCase(coord, this);
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

    @Override
    public String toString() {
        return "Case2D [ value : " + valeur + "," + coord.toString() + "]";
    }

    public Grille2D getG() {
        return g;
    }

    public void setG(Grille2D g) {
        this.g = g;
    }
}
