import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.Cleaner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import modele.Direction;
import modele.Jeu;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Main_test {
    public static void main(String args[]) {
        System.out.println("TutorialsPoint");
        Cleaner cleaner = Cleaner.create();
        if (true) {
            Jeu myObject = new Jeu(4);
        }
        for (int i = 1; i <= 10000; i++) {
            String[] largeObject = new String[1000];
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
