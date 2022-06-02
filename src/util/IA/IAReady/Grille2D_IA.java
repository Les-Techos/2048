package util.IA.IAReady;

import java.util.ArrayList;

import modele.Direction.Direction2D;
import modele.Grille.Grille;
import modele.Grille.Grille2D;
import util.IA.IA_Action;
import util.IA.IA_Agent;

public class Grille2D_IA extends Grille2D implements IA_Agent {

    public Grille2D_IA(int _size) {
        super(_size);
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

}
