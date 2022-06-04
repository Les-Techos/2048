package util.IA;

import java.util.ArrayList;

public interface IA_Agent extends Cloneable{
    public ArrayList<IA_Action> getAvailableActions();

    public void build();

    public Object clone();

    public void step(IA_Action act);

    public boolean isFinished();

    public boolean isWinning();
}
