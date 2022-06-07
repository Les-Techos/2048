package modele.Grille;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Iterator;
import java.util.Observable;

import modele.*;
import modele.Case.Case;
import modele.Coord.*;
import modele.Direction.Direction2D;

public abstract class Grille extends Observable implements Cloneable, Serializable {

    protected HashMap<Coord, Case> mp_coord_case = new HashMap<Coord, Case>();
    public static Random r = new Random();
    protected Case max_case = null;
    protected Joueur joueur;

    int size = -1;
    int nbSlots = 0;

    /**
     * 
     * @param _size taille de grille
     */
    public Grille(int _size, Joueur joueur) {

        this.size = _size;
        nbSlots = calculateNbSlots();
        checkSize();
        this.joueur = joueur;
    }

    public Grille clone() {

        Grille clone = null;
        try {
            clone = this.getClass().getDeclaredConstructor(int.class, Joueur.class).newInstance(size, null);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }

        //clone.deleteObservers();

        Iterator<Entry<Coord, Case>> it = mp_coord_case.entrySet().iterator();
        Entry<Coord, Case> ent;

        while (it.hasNext()) {
            ent = it.next();

            Case case_clone = ent.getValue().clone();
            Coord coord_clone = case_clone.getCoord();

            case_clone.setG(clone);

            clone.setCase(coord_clone, case_clone);
        }

        if(max_case != null) clone.max_case = max_case.clone(); 

        return clone;
    }

    /**
     * La grille est pleine ?
     * @return
     */
    public synchronized boolean isfull() {
        return mp_coord_case.size() >= nbSlots;
    }

    /**
     * La grille est gagnante ?
     * @return
     */
    public synchronized boolean iswinning() {
        return getMax_case().getValeur() >= 2048;
    }

    /**
     * La grille est bloquée ?
     * @param soluce : La solution le cas contraire
     * @return
     */
    public synchronized boolean iswrecked(AtomicReference<Direction2D> soluce) {
        if (isfull()) {
            Grille clone = null;

            for (Direction2D dir : Direction2D.values()) {
                clone = (Grille) this.clone();
                clone.simple_move(dir);
                if (!this.equals(clone)) {
                    soluce.set(dir);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Renvoie les mouvements disponibles
     * @return
     */
    public synchronized ArrayList<Direction2D> getAvailableDirections() {
        ArrayList<Direction2D> res = new ArrayList<Direction2D>();
        if (isfull()) {
            Grille clone = null;
            for (Direction2D dir : Direction2D.values()) {
                clone = (Grille) this.clone();
                clone.simple_move(dir);
                if (!this.equals(clone))
                    res.add(dir);
            }
        } else
            for (Direction2D dir : Direction2D.values())
                res.add(dir);
        return res;
    }

    /**
     * La grille est bloqué ?
     * @return
     */
    public synchronized boolean iswrecked() {
        AtomicReference<Direction2D> d = new AtomicReference<Direction2D>(Direction2D.bas);
        return iswrecked(d);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != this.getClass())
            return false; // If they are from the same class

        Grille cmp_Grille = (Grille) obj;
        if (cmp_Grille.mp_coord_case.size() != mp_coord_case.size())
            return false;

        Iterator<Entry<Coord, Case>> it = mp_coord_case.entrySet().iterator();

        while (it.hasNext()) {
            Entry next = it.next();
            if (((Case) next.getValue()).getValeur() != ((Case) cmp_Grille.getCase((Coord) next.getKey())).getValeur())
                return false;
        }
        return true;
    }

    /**
     * Vérifie la coordonnée
     * @param c
     * @return
     */
    public abstract boolean checkCoord(Coord c);

    /**
     * Vérifie la taille de la grille
     */
    public void checkSize() {
        if (size <= 0) {
            try {
                throw new Exception(" Static attributes Size has not been initialized : " + size);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int getNbSlots() {
        return nbSlots;
    }

    /**
     * Calcul le nombre d'emplacement
     * @return
     */
    public abstract int calculateNbSlots();

    /**
     * Insère une case au coordonnées spécifiée
     * @param c
     * @param cs
     */
    public synchronized void setCase(Coord c, Case cs) {
        if (cs == null) {
            rmCase(c);
            return;
        }
        
        if (checkCoord(c)){
            if(getMax_case().getValeur() < cs.getValeur()) max_case = cs;
            mp_coord_case.put(c, cs);
        }
           
    }

    /**
     * Insère une case aléatoirement dans la grille
     */
    public abstract void insertRandomCase();

    /**
     * Supprime la case à la coordonnée c
     * @param c
     */
    public void rmCase(Coord c) {
        mp_coord_case.remove(c);
    }

    /**
     * Retourne la case à la coordonnée c
     * @param c
     * @return
     */
    public Case getCase(Coord c) {
        return mp_coord_case.get((Coord2D) c);
    }

    /**
     * Retourne la case voisine à la coordonné c et dans la direction dir 
     * @param cs
     * @param dir
     * @return
     */
    public Case getVoisin(Case cs, Direction2D dir) {

        Coord neighbor_coord = cs.getCoord(); // Les coordonnées du voisin
        Case cs_res = null;
        try {
            cs_res = mp_coord_case.get(neighbor_coord.getVoisin(dir)); // Récupère la case voisine des coordonnées de cs
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cs_res;
    }

    /**
     * Retourne la case maximale
     * @return
     */
    public Case getMax_case() {
        return max_case;

    }

    /**
     * Retourn la somme des case de la grille
     * @return
     */
    public int getSum(){
        int res = 0;
        Iterator iter = mp_coord_case.entrySet().iterator();
        while(iter.hasNext()) res += ((Entry<Coord, Case>) iter.next()).getValue().getValeur();
        return res;
    }

    /**
     * Déplace toutes les cases de la grille vers dir ( les fusionne au besoin ) 
     * @param dir
     */
    protected abstract void simple_move(Direction2D dir);

    /**
     * Déplace les cases vers dir, les fusionne et notifie le jeu
     * @param dir
     */    
    public synchronized void move(Direction2D dir) {
        simple_move(dir);

        AtomicReference<Direction2D> d = new AtomicReference<Direction2D>(Direction2D.bas);
        boolean wrecked = iswrecked(d);

        if (isfull() || wrecked || iswinning())
            setChanged();

        if (isfull() && !wrecked)
            notifyObservers(new GrilleInfo(EtatGrille.full, d.get()));
        else if (wrecked)
            notifyObservers(new GrilleInfo(EtatGrille.wrecked));
        else if (iswinning())
            notifyObservers(new GrilleInfo(EtatGrille.winning));

        if (!isfull())
            insertRandomCase();

        setChanged();
        notifyObservers();
    }
}
