package modele;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Random;

public class Jeu extends Observable {

    int size;

    private HashMap<Case, Coord> mp_case_coord = new HashMap<Case, Coord>();
    private Map<Coord, Case> mp_coord_case = new HashMap<Coord, Case>();

    private static Random rnd = new Random(4);

    public Jeu(int size) {
        this.size = size;
        rnd();
    }

    public int getSize() {
        return size;
    }

    public Case getCase(int i, int j) {
        return mp_coord_case.get(new Coord(i, j));
    }

    public void rnd() {
        new Thread() { // permet de libérer le processus graphique ou de la console
            public void run() {
                int r;

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        r = rnd.nextInt(3);

                        switch (r) {
                            case 0:
                                // tabCases[i][j] = null;
                                break;
                            case 1:
                                mp_coord_case.put(new Coord(i, j), new Case(2));
                                break;
                            case 2:
                                mp_coord_case.put(new Coord(i, j), new Case(4));
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
        switch (d) {
            case gauche:

                break;
        }
    }
}
