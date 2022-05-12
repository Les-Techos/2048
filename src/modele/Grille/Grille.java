package modele.Grille;
import modele.*;
import modele.Case.Case;
import modele.Coord.*;

public interface Grille {

    public int getSize();

    // TODO Check if the grid is full

    // TODO Check if the grid is winning
    
    // TODO Check if the grid is wrecked

    public void setCase(Coord c, Case cs);

    public void insertRandomCase();

    public void rmCase(Coord c);

    public Case getCase(Coord c);

    public Case getVoisin(Case cs, Direction dir);

    public void move(Direction dir);
}
