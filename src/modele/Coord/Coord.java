package modele.Coord;

import modele.Direction;

public interface Coord {
    @Override
    public boolean equals(Object obj);

    @Override
    public int hashCode();

    public Coord getVoisin(Direction dir);
}
