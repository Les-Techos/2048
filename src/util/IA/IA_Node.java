package util.IA;

import java.util.ArrayList;

public interface IA_Node extends Cloneable{
    public ArrayList<IA_Action> getAvailableActions(); //Retourne les actions disponibles

    /**
     * Initialise la première node
     */
    public void build();

    public Object clone();

    /**
     * Applique un changement sur la node
     * @param act
     */
    public void step(IA_Action act); 

    /**
     * La node est une feuile ?
     * @return
     */
    public boolean isFinished();

    /**
     * La node est Gagnante ?
     * @return
     */
    public boolean isWinning();
}
