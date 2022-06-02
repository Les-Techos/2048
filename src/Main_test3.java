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

public class Main_test3 {
    public static void main(String args[]) throws InterruptedException {
        long start = System.currentTimeMillis();

        MC_IA ia = new MC_IA();

        Grille2D_IA g = new Grille2D_IA(4); // Initialisation de la grille
        for (int i = 0; i < 4; i++) // Insertion des cases
            g.insertRandomCase();

        int step = 0;

        while (!g.isFinished()) { // Tant que notre noeud courant n'est pas terminé

            IA_Action act = (IA_Action)ia.getBestAction(g);
            g.step(act);

            System.out.println("Etape n° " + step++ + " / " + act.getAction());
            System.out.println(g);
        }
        System.out.println((System.currentTimeMillis() - start)/1000 + " s");
    }

  
}
