import modele.Coord.Coord2D;

import java.sql.Time;

import modele.Jeu;

public class Main_test {
    public static void main(String[] args) {
        Joueur j = new Joueur(42, Time.valueOf("06:30:02"), "Jean-Marque");

        System.out.println(j);   
    }
}
