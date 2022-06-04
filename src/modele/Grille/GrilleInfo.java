package modele.Grille;

import modele.Direction.Direction2D;

public class GrilleInfo {
    Direction2D indice;
    EtatGrille etat;

    public GrilleInfo(EtatGrille etat) {
        this.etat = etat;
    }
    public GrilleInfo(EtatGrille etat, Direction2D indice) {
        this.indice = indice;
        this.etat = etat;
    }
    public Direction2D getIndice() {
        return indice;
    }
    public void setIndice(Direction2D indice) {
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
