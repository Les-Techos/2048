package modele.Grille;

import java.util.HashMap;
import java.util.Random;

import modele.Direction;
import modele.Case.Case;
import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;

public class Grille2D implements Grille {
    protected HashMap<Coord2D, Case2D> mp_coord_case = new HashMap<Coord2D, Case2D>();
    int size;
    public static Random r = new Random();

    public Grille2D(int _size) {
        size = _size;
    }

    @Override
    public Case2D getVoisin(Case cs, Direction dir) {

        Coord2D neighbor_coord = ((Case2D) cs).getCoord(); // Les coordonnées du voisin
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
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                Coord2D target_coord = null;
                try {
                    switch (dir) { // On récupère les coordonnées des cases à traiter
                        case gauche:
                            target_coord = Coord2D.getInstance(row, col);
                            break;
                        case droite:
                            target_coord = Coord2D.getInstance(row, size - 1 - col);
                            break;
                        case haut:
                            target_coord = Coord2D.getInstance(col, row);
                            break;
                        case bas:
                            target_coord = Coord2D.getInstance(size - 1 - row, col);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Case2D case_selected = getCase(target_coord);
                if(case_selected != null) 
                    case_selected.move(dir);
            }
        }
        insertRandomCase();
    }

    @Override
    public void setCase(Coord c, Case cs) {
        if (cs == null){
            rmCase(c);
            return;
        }
            
        mp_coord_case.put((Coord2D) c, (Case2D) cs);
    }

    @Override
    public void rmCase(Coord c) {
        mp_coord_case.remove(c);
    }

    @Override
    public Case2D getCase(Coord c) {
        return mp_coord_case.get((Coord2D) c);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void insertRandomCase() {
        Coord2D c = null;
        do{
            c = Coord2D.rand();
        }while(getCase(c) != null);
        setCase(c, new Case2D((r.nextInt()%2+1)*2, c));
    }
}
