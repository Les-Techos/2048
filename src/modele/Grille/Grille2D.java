package modele.Grille;

import java.util.concurrent.atomic.AtomicReference;

import modele.Case.Case2D;
import modele.Coord.Coord;
import modele.Coord.Coord2D;
import modele.Direction.Direction2D;

public class Grille2D extends Grille {

    public Grille2D(int _size) {
        super(_size);

    }

    @Override
    protected void simple_move(Direction2D dir) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {

                Coord2D target_coord = null;
                try {
                    switch (dir) { // On récupère les coordonnées des cases à traiter
                        case gauche:
                            target_coord = Coord2D.getInstance(row, col, this);
                            break;
                        case droite:
                            target_coord = Coord2D.getInstance(row, size - 1 - col, this);
                            break;
                        case haut:
                            target_coord = Coord2D.getInstance(col, row, this);
                            break;
                        case bas:
                            target_coord = Coord2D.getInstance(size - 1 - row, col, this);
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Case2D case_selected = (Case2D) getCase(target_coord);
                if (case_selected != null) {
                    case_selected.move(dir);
                    if (max_case.getValeur() < case_selected.getValeur())
                        max_case = case_selected;
                }

            }
        }
    }

    @Override
    public void insertRandomCase() {
        if (isfull())
            return;

        Coord2D c = null;
        do {
            c = Coord2D.rand(this);
        } while (getCase(c) != null);
        int rd = Math.abs(r.nextInt()%10);

        Case2D cs = null;

        if(rd == 9)
            cs = new Case2D( 4, c, this);
        else
            cs = new Case2D( 2, c, this);
        setCase(c, cs);
    }

    @Override
    public String toString() {
        String res = "";

        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                Case2D c = null;
                try {
                    c = (Case2D) getCase(Coord2D.getInstance(i, j, this));
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

    @Override
    public boolean checkCoord(Coord c) {
        Coord2D c_2D = (Coord2D) c;
        return c_2D.getX() >= 0 && c_2D.getX() < size && c_2D.getY() >= 0 && c_2D.getY() < size;
    }

    @Override
    public int calculateNbSlots() {
        return size * size;
    }
}
