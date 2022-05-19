package modele.Grille;

import modele.Direction;

public class GrilleInfo {
    Direction indice;
    EtatGrille etat;

    public GrilleInfo(EtatGrille etat) {
        this.etat = etat;
    }
    public GrilleInfo(EtatGrille etat, Direction indice) {
        this.indice = indice;
        this.etat = etat;
    }
    public Direction getIndice() {
        return indice;
    }
    public void setIndice(Direction indice) {
        this.indice = indice;
    }
    public EtatGrille getEtat() {
        return etat;
    }
    public void setEtat(EtatGrille etat) {
        this.etat = etat;
    }
    @Override
    public String toString() {
        return "[ indice : " + indice + ";" + " etat : " + etat + "]";
    }
}
