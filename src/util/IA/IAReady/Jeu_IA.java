package util.IA.IAReady;

import modele.Jeu;

public class Jeu_IA extends Jeu{

    public Jeu_IA(int size) {
        super(size);
        g = new Grille2D_IA(size);
    }
    
}
