import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.Cleaner;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import modele.Direction.Direction2D;
import modele.Grille.Grille;
import modele.Grille.Grille2D;
import util.Serializer;
import util.IA.IA_Action;
import util.IA.MC_IA;
import util.IA.IAReady.Grille2D_IA;

public class Main_test {
    public static void main(String args[]) throws InterruptedException {
        
        long start = System.currentTimeMillis();
        Joueur j = new Joueur("Ratio");

        MC_IA ia = new MC_IA(50, 2); // Tu peux laisser ces paramètres ils sont optimaux

        Grille2D_IA g = new Grille2D_IA(4,j); // Initialisation de la grille
        for (int i = 0; i < 4; i++) // Insertion des cases de départ
            g.insertRandomCase();

        int step = 0;

        while (!g.isFinished()) { // Tant que notre noeud courant n'est pas terminé

            Direction2D act = (Direction2D) ia.getBestAction(g).getAction(); // On récupère la meilleur action à effectuer
            g.move(act); // On applique la modif

            System.out.println("Etape n° " + step++ + " / " + act); // affiche l'action effectuée
            System.out.println(g); // affiche la grille
        }
        ia.stop(); // éteint la pool de threads
    }

}
