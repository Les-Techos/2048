package modele.Grille;
import modele.*;
import modele.Case.Case;
import modele.Case.Case2D;
import modele.Coord.*;

import java.util.HashMap;

public interface Grille {

    public Case getVoisin(Case cs, Direction dir);

    public void setCase(Coord c, Case cs);

    public void move(Direction dir);
}
