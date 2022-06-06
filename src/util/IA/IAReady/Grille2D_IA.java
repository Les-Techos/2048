package util.IA.IAReady;

import java.util.ArrayList;

import modele.Joueur;
import modele.Direction.Direction2D;
import modele.Grille.Grille;
import modele.Grille.Grille2D;
import util.IA.IA_Action;
import util.IA.IA_Node;

public class Grille2D_IA extends Grille2D implements IA_Node {

    public Grille2D_IA(int _size,Joueur j) {
        super(_size, j);
    }

    @Override
    public ArrayList<IA_Action> getAvailableActions() {
        ArrayList<IA_Action> actions = new ArrayList<IA_Action>();
        for (Direction2D dIA : getAvailableDirections()) {
            actions.add(new Direction2D_IA(dIA));
        }
        return actions;
    }

    @Override
    public Grille2D clone() {
        return (Grille2D) super.clone();
    }

    @Override
    public void step(IA_Action act) {
        move(((Direction2D) act.getAction()));
    }

    @Override
    public boolean isFinished() {
        return iswrecked();
    }

    @Override
    public boolean isWinning() {
        return iswinning();
    }

    @Override
    public void build() {
        for (int i = 0; i < 4; i++) // Insertion des cases
        insertRandomCase();
    }

}
