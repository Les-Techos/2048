package modele;

import java.util.Observable;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Jeu extends Observable {

    int size;
    private Grille2D g;
    private static Random rnd = new Random(4);

    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    public Jeu(int size) {
        g = new Grille2D(size);
        this.size = g.getSize();
        Coord2D.setG(g);
        rnd();
    }

    public int getSize() {
        return size;
    }

    public Case2D getCase(Coord c) {
        return g.getCase(c);
    }

    public void rnd() {
        executor.submit(() -> {
            int r;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    r = rnd.nextInt(3);

                    Coord2D c = null;
                    try {
                        c = Coord2D.getInstance(i, j);
                    } catch (Exception e) {
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
            setChanged();
            notifyObservers();

        });
    }

    public void move(Direction d) {
        executor.submit(() -> {
            g.move(d);

            setChanged();
            notifyObservers();
        });
    }
}