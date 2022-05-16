package modele.Grille;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;

import java.util.Iterator;

import modele.*;
import modele.Case.Case;
import modele.Coord.*;

public abstract class Grille implements Cloneable{

    protected HashMap<Coord, Case> mp_coord_case = new HashMap<Coord, Case>();
    public static Random r = new Random();
    protected Case max_case = null;

    int size = -1;
    int nbSlots = 0;

    public Grille(int _size){
        this.size  = _size;
        nbSlots = calculateNbSlots();
        checkSize();
    }

    public Grille clone() {

        Grille clone = null;
        try {
            Class this_class = this.getClass();
            clone = this.getClass().getDeclaredConstructor(int.class).newInstance(size);
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Iterator<Entry<Coord,Case>> it = mp_coord_case.entrySet().iterator();
        Entry<Coord,Case> ent;

        while(it.hasNext()){
            ent = it.next();

            Coord coord_clone = ent.getKey().clone();
            Case case_clone = ent.getValue().clone();

            coord_clone.setG(clone);

            case_clone.setCoord(coord_clone);
            case_clone.setG(clone);

            clone.setCase(coord_clone, case_clone);
        }            

        return clone;
    }

    public boolean isfull(){
        return mp_coord_case.size() >= nbSlots;
    }

    public boolean iswinning(){
        return max_case.getValeur() >= 2048;
    }
    
    public boolean iswrecked() {
        // TODO Auto-generated method stub
        if (isfull()) {
            Grille clone = null;
            clone = (Grille) this.clone();
            for (Direction dir : Direction.values()) {
                clone.move(dir);
                if (!this.equals(clone)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public abstract boolean equals(Object obj);
    // TODO Implements this here : Iterate over hashmap and look for sibling in obj

    public abstract boolean checkCoord(Coord c);

    public void checkSize(){
        if(size <= 0){
            try{
                throw new Exception(" Static attributes Size has not been initialized : " + size);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    public int getSize(){
        return size;
    }

    public int getNbSlots() {
        return nbSlots;
    }

    public abstract int calculateNbSlots();

    public void setCase(Coord c, Case cs) {
        if (cs == null) {
            rmCase(c);
            return;
        }

        if(checkCoord(c)) mp_coord_case.put(c, cs);
    }

    public abstract void insertRandomCase();

    public void rmCase(Coord c) {
        mp_coord_case.remove(c);
    }
    
    public Case getCase(Coord c) {
        return mp_coord_case.get((Coord2D) c);
    }

    public Case getVoisin(Case cs, Direction dir) {

        Coord neighbor_coord = cs.getCoord(); // Les coordonnées du voisin
        Case cs_res = null;
        try {
            cs_res = mp_coord_case.get(neighbor_coord.getVoisin(dir)); // Récupère la case voisine des coordonnées de cs
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cs_res;
    }

    public Case getMax_case() {
        return max_case;
    }

    public abstract void move(Direction dir);
}
