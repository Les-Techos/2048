import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.ref.Cleaner;
import java.sql.Time;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import modele.Direction;
import modele.Jeu;
import modele.Joueur;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;
import util.Serializer;

public class Main_test {
    public static void main(String args[]) {

        try {
            Joueur jean = (Joueur) Serializer.load("JeanSave.txt");
            System.out.println(jean);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("La sauvegarde s'est mal déroulée");
            e.printStackTrace();
        }
    }
}
