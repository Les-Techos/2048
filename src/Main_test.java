import modele.Direction;
import modele.Jeu;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;

public class Main_test {
    public static void main(String[] args) {
        
        Jeu j = new Jeu(4);
        j.rnd();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Grille2D j_clone = (Grille2D) j.getGrille().clone();

        if(j_clone.equals(j.getGrille())) System.out.println("Il sont pareil !");
        
        Case2D c = (Case2D) j_clone.getCase(Coord2D.getInstance(2, 3, j_clone));
        c.move(Direction.bas);
        c.move(Direction.haut);

        if(j_clone.equals(j.getGrille())) System.out.println("Et là aussi !");

        System.out.println(j_clone.toString());
        System.out.println(j.getGrille().toString());
        
    }
}
