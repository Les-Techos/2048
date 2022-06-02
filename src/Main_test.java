import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.Cleaner;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import modele.Jeu;
import modele.Joueur;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Direction.Direction2D;
import modele.Grille.Grille;
import modele.Grille.Grille2D;
import util.Serializer;

public class Main_test {
    public static void main(String args[]) throws InterruptedException {
        run();
        // main2();
    }

    public static void main2() throws InterruptedException {
        Grille2D g = new Grille2D(4); // Initialisation de la grille
        for (int i = 0; i < 4; i++) // Insertion des cases
            g.insertRandomCase();

        System.out.println(g);
        System.out.println(getBestAction(g));
    }

    public static void run() {
        long start = System.currentTimeMillis();

        Grille2D g = new Grille2D(4); // Initialisation de la grille
        for (int i = 0; i < 4; i++) // Insertion des cases
            g.insertRandomCase();

        int step = 0;

        while (!(g.iswrecked())) { // Tant que notre noeud courant n'est pas terminé

            Direction2D max_dir = getBestAction(g);
            step(g, max_dir);

            System.out.println("Etape n° " + step++ + " / " + max_dir);
            System.out.println(g);
        }
        System.out.println((System.currentTimeMillis() - start)/1000 + " s");
    }

    public static Direction2D getBestAction(Grille2D base_g) {
        Direction2D max_dir = null;
        double max_score = -1; // On sauvegarde le maximum des moyennes des maximums
        for (Direction2D dir : base_g.getAvailableDirections()) { // Pour chaque direction
            Grille2D g = (Grille2D) base_g.clone();
            step(g, dir);

            double score = getScore(g, 100);

            // System.out.println("Direction de départ : " + dir + " / Le score atteint est
            // : " + max);

            if (score > max_score) {
                max_score = score;
                max_dir = dir;
            }
        }
        return max_dir;
    }

    public static double getScore(Grille2D base_grid, int nb_tries) {
        Random r = new Random();
        double score = 0;

        for (int i = 0; i < nb_tries; i++) {
            Grille2D g = (Grille2D) base_grid.clone();
            int nb_steps = 0;
            while (!(g.iswrecked())) {
                ArrayList<Direction2D> poss = g.getAvailableDirections();
                step(g, poss.get(Math.abs(r.nextInt()) % poss.size()));
                if(g.iswinning()){
                    nb_steps += nb_tries;
                }
                nb_steps++;
            }
            score += nb_steps;
        }

        return score/((double)nb_tries);
    }

    public static void step(Grille2D g, Direction2D d) {
        g.move(d);
    }
}
