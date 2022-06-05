package util.IA.IAReady;

import modele.Direction.Direction2D;
import util.IA.IA_Action;

public class Direction2D_IA implements IA_Action{

    Direction2D dir = null;

    Direction2D_IA(Direction2D d){
        dir = d;
    }

    @Override
    public Object getAction() {
        return dir;
    }
    
}
