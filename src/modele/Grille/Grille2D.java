package modele.Grille;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;

import modele.Direction;
import modele.Case.Case;
import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;

public class Grille2D implements Grille, Cloneable {
    protected HashMap<Coord2D, Case2D> mp_coord_case = new HashMap<Coord2D, Case2D>();
    public static Random r = new Random();

    static int size = -1;

    public Grille2D() {
        checkSize();
    }

    public static boolean isCoordCorrect(int x, int y) throws Exception {
        checkSize();
        return x >= 0 && x < size && y >= 0 && y < size;
    }

    public static void checkSize(){
        if(size == -1){
            try{
                throw new Exception(" Static attributes Size has not been initialized : " + size);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public static void setSize(int size){
        Grille2D.size = size;
    }

    public static int getSize(){
        return size;
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
                if (case_selected != null)
                    case_selected.move(dir);
            }
        }
        if (!isfull()) {
            insertRandomCase();
        }
    }

    @Override
    public void setCase(Coord c, Case cs) {
        if (cs == null) {
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
    public void insertRandomCase() {
        Coord2D c = null;
        do {
            c = Coord2D.rand();
        } while (getCase(c) != null); // TODO Check if the grid is not already full
        Case2D cs = new Case2D((Math.abs(r.nextInt()) % 2 + 1) * 2, c, this);
        setCase(c, cs);
    }

    @Override
    public boolean isfull() {
        // TODO Auto-generated method stub
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (getCase(Coord2D.getInstance(row, col)) == null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean iswinning() {
        // TODO Auto-generated method stub
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Case2D c = getCase(Coord2D.getInstance(row, col));
                if (c != null) {
                    if (c.getValeur() >= 2048) {
                        System.out.println("Bien joué vous avez gagné");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean iswrecked() {
        // TODO Auto-generated method stub
        if (isfull()) {
            Grille2D clone = (Grille2D) this.clone();
            for (Direction dir : Direction.values()) {
                this.move(dir);
                if (!this.equals(clone)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public synchronized Grille2D clone() {

        Grille2D clone = new Grille2D();

        Iterator<Entry<Coord2D,Case2D>> it = mp_coord_case.entrySet().iterator();
        Entry<Coord2D,Case2D> ent;

        while(it.hasNext()){
            ent = (Entry<Coord2D,Case2D>) it.next();

            Coord2D coord_clone = ent.getKey().clone();
            Case2D case_clone = ent.getValue().clone();

            case_clone.setCoord(coord_clone);
            case_clone.setG(clone);

            clone.setCase(coord_clone, case_clone);
        }            

        return clone;
    }

    @Override
    public boolean equals(Object obj) {
            for (int row = 0; row < size; row++) {
                for (int col = 0; col < size; col++) {
                    Case2D ccompare = getCase(Coord2D.getInstance(row, col));
                    Case2D cobj = ((Grille2D) obj).getCase(Coord2D.getInstance(row, col));
                    if (ccompare != null && cobj != null) {
                        if (ccompare.getValeur() != cobj.getValeur()) {
                            return false;
                        }
                    }else if(!(ccompare == null && cobj == null)) return false;
                }
            }
            return true;
    }

    @Override
    public String toString() {
        String res = "";

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Case2D c = null;
                try {
                    c = getCase(Coord2D.getInstance(i, j));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (c != null) {
                    System.out.format("%5.5s", c.getValeur());
                } else {
                    System.out.format("%5.5s", "");
                }
            }
            System.out.println();
        }

        return res;
    }
}
