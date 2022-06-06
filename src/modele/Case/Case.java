package modele.Case;

import java.io.Serializable;

import modele.Joueur;
import modele.Coord.Coord;
import modele.Direction.Direction2D;
import modele.Grille.Grille;

public abstract class Case implements Cloneable, Serializable {
    protected int valeur;
    protected Coord coord;
    protected Grille g;
    protected Joueur joueur;

    public Case(int _valeur, Coord _coord, Grille g,Joueur j) {
        valeur = _valeur;
        this.g = g;
        this.coord = _coord;
        this.joueur = j;
    }

    @Override
    public abstract Case2D clone();

    public void move(Direction2D d) {
        Case neighbor = g.getVoisin(this, d); // Le voisin s'il existe

        if (neighbor != null) { // S'il existe un voisin
            if (neighbor.getValeur() == valeur) { // Si ce voisin a la même valeur
                Coord neighbor_coord = neighbor.getCoord();
                
                setValeur(2 * valeur);
                joueur.setScore(valeur);

                g.rmCase(neighbor_coord);

                g.rmCase(coord);
                setCoord(neighbor_coord);
                g.setCase(coord, this);
            }
        }
        else { // Sinon la place est libre
            
            Coord coord_dest = null;

            coord_dest = (Coord) coord.getVoisin(d);

            if (coord_dest != null) {
                g.rmCase(coord); // Supprime l'entrée correspondant à cette case
                coord = coord_dest; // On actualise les coordonnées de cette case
                g.setCase(coord, this); // On ajoute la nouvelle entrée
                this.move(d);
            }
        }  
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int _valeur) {
        valeur = _valeur;
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        if (coord != null)
            this.coord = coord;
    }

    public Grille getG() {
        return g;
    }

    public void setG(Grille g) {
        this.g = g;
        coord.setG(g);
    }
}
