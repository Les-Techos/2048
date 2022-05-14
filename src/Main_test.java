import modele.Coord.Coord2D;
import modele.Grille.Grille2D;
import modele.Direction;
import modele.Jeu;
import modele.Case.Case2D;

public class Main_test {
    public static void main(String[] args) {
        Jeu j = new Jeu(4);
        j.rnd();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Grille2D j_clone = j.getGrille().clone();

        
        Case2D c = j_clone.getCase(Coord2D.getInstance(0, 0));
        c.setValeur(200);
        c.move(Direction.bas);
        

        System.out.println(j_clone.toString());
        System.out.println(j.getGrille().toString());
    }
}
