package modele.Grille;

import java.util.HashMap;

import modele.Direction;
import modele.Case.Case;
import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;

public class Grille2D implements Grille{
    protected HashMap<Coord2D, Case2D> mp_coord_case = new HashMap<Coord2D, Case2D>();

    @Override
    public Case2D getVoisin(Case cs, Direction dir) {

        Coord2D neighbor_coord = ((Case2D)cs).getCoord(); // Les coordonnées du voisin
        Case2D cs_res = null;
        try {
            cs_res = mp_coord_case.get(neighbor_coord.getVoisin(dir)); // Récupère la case voisine des coordonnées de cs
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cs_res;
    }

    @Override
    public void move(Direction dir) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setCase(Coord c, Case cs) {
        if(cs == null) mp_coord_case.remove(c);
        mp_coord_case.put((Coord2D)c, (Case2D)cs);
    }

    public Case2D getCase(Coord c) {
        return mp_coord_case.get((Coord2D)c);
    }
}
