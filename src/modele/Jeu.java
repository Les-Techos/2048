package modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Jeu extends Observable {

    int size;

    private Grille2D g = new Grille2D();

    private static Random rnd = new Random(4);

    public Jeu(int size) {
        this.size = size;
        Coord2D.setJ(this);
        rnd();
    }

    public int getSize() {
        return size;
    }

    public Case2D getCase(Coord c) {
        return g.getCase(c);
    }

    public void rnd() {
        new Thread() { // permet de libérer le processus graphique ou de la console
            public void run() {
                int r;

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        r = rnd.nextInt(3);

                        Coord2D c = null;
                        try {
                            c = new Coord2D(i, j);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        
                        switch (r) {
                            case 0:
                                g.setCase(c, null);
                                break;
                            case 1:
                                g.setCase(c, new Case2D(2, c));
                                break;
                            case 2:
                                g.setCase(c, new Case2D(4, c));
                                break;
                        }
                    }
                }
            }

        }.start();

        setChanged();
        notifyObservers();

    }

    public void move(Direction d) {
        //TODO Implement move op
    }
}