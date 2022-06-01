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
import modele.Grille.Grille2D;
import util.Serializer;

public class Main_test {
    public static void main(String args[]) throws InterruptedException {

        Grille2D g = new Grille2D(5), g_clone; // Initialisation de la grille
        for (int i = 0; i < 4; i++) // Insertion des cases
            g.insertRandomCase();

        g_clone = (Grille2D) g.clone(); // Garde une copie

        Random r = new Random();

        int step = 0;

        while (!(g_clone.iswinning() || g_clone.iswrecked())) { // Tant que notre noeud courant n'est pas terminé
            double max_max = 0; // On sauvegarde le maximum des moyennes des maximums
            System.out.println("Etape n° " + step++);
            System.out.println(g_clone);
            Direction2D max_dir = Direction2D.gauche;

            for (Direction2D dir : g_clone.getAvailableDirections()) { // Pour chaque direction
                int max = 0;
                g.move(dir); // On bouge
                g.insertRandomCase();
                int nb_essais = 100;

                for (int i = 0; i < nb_essais; i++) {
                    g = (Grille2D) g_clone.clone();
                    while (!(g.iswinning() || g.iswrecked())) {

                        ArrayList<Direction2D> poss = g.getAvailableDirections();
                        g.move(poss.get(Math.abs(r.nextInt()) % poss.size()));
                        g.insertRandomCase();
                    }
                    if(max < g.getMax_case().getValeur()){
                        max = g.getMax_case().getValeur();
                        System.out.println("Max = " + max);
                    }
                }

                System.out.println("Direction de départ : " + dir + " / Le maximum atteint est : " + max);

                if (max > max_max) {
                    max_max = max;
                    max_dir = dir;
                }
            }

            System.out.println("Direction choisie : " + max_dir + " \n \n");
            g_clone.move(max_dir); g_clone.insertRandomCase();
        }
        System.out.println(g);
    }

}
