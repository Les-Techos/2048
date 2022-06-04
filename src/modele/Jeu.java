package modele;

import java.lang.ref.Cleaner;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Jeu extends Observable implements AutoCloseable, Observer {

    int size;
    private Grille2D g;
    private static Random rnd = new Random();

    Semaphore s = new Semaphore(1);
    public ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

    public final static Cleaner cleaner = Cleaner.create();

    private static class CleaningAction implements Runnable {
        ArrayList<ThreadPoolExecutor> l = new ArrayList<ThreadPoolExecutor>();

        CleaningAction(ThreadPoolExecutor ls) {
            l.add(ls);
        }

        @Override
        public void run() {
            for (ThreadPoolExecutor t : l)
                t.shutdown();
        }

    }

    public Jeu(int size) {
        cleaner.register(this, new CleaningAction(executor));
        g = new Grille2D(size);
        g.addObserver(this);
        this.size = size;
        rnd();
    }

    public int getSize() {
        return size;
    }

    public Case2D getCase(Coord c) {
        return (Case2D) g.getCase(c);
    }

    public void rnd() {
        executor.execute(() -> {
                int r;

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        r = rnd.nextInt(3);

                        Coord2D c = null;
                        try {
                            c = Coord2D.getInstance(i, j, g);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        switch (r) {
                            case 0:
                                g.setCase(c, null);
                                break;
                            case 1:
                                g.setCase(c, new Case2D(2, c, g));
                                break;
                            case 2:
                                g.setCase(c, new Case2D(4, c, g));
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
            try {
                s.acquire();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                if (!(g.iswrecked() || g.iswinning())) {
                    g.move(d);
                    setChanged();
                    notifyObservers();
                }
            s.release();
        });

    }

    public Grille2D getGrille() {
        return g;
    }

    public void setGrille(Grille2D g) {
        try {
            s.acquire();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.g = g;
        setChanged();
        notifyObservers();
        s.release();
    }

    @Override
    public void close() throws Exception {
        executor.shutdown();
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        // TODO Auto-generated method stub
        System.out.println(arg1);
    }

}