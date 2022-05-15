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

        if(j_clone.equals(j.getGrille())) System.out.println("Il sont pareil !");
        
        Case2D c = j_clone.getCase(Coord2D.getInstance(2, 3));
        c.move(Direction.bas);
        c.move(Direction.haut);

        if(j_clone.equals(j.getGrille())) System.out.println("Et là aussi !");

        System.out.println(j_clone.toString());
        System.out.println(j.getGrille().toString());
    }
}
