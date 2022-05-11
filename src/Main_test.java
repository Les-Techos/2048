import modele.Coord.Coord2D;
import modele.Direction;
import modele.Jeu;

public class Main_test {
    public static void main(String[] args) {
        Jeu j = new Jeu(10);
        Coord2D.setJ(j);

        Coord2D c2d = new Coord2D(8,3);
        try{
            System.out.println(c2d.getVoisin(Direction.bas));     
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}
