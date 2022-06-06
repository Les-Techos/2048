package util.IA.IAReady;

import modele.Jeu;
import modele.Joueur;

public class Jeu_IA extends Jeu{

    public Jeu_IA(int size,Joueur j) {
        super(size, j);
        g = new Grille2D_IA(size, j);
    }

    
}
