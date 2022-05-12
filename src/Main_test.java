import modele.Coord.Coord2D;
import modele.Jeu;

public class Main_test {
    public static void main(String[] args) {
        Jeu j = new Jeu(10);

        for(int i = 0; i < 100 ; i++) System.out.println(Coord2D.rand());     
    }
}
