package vue_controleur;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

public class Couleur {
    

    public static HashMap<Integer,Color> choix(){
        HashMap<Integer,Color> coloration = new HashMap<Integer,Color>();
        coloration.put(2,new Color(255, 242, 230));
        coloration.put(4,new Color(255, 204, 153));
        coloration.put(8,new Color(255, 179, 102));
        coloration.put(16,new Color(255, 166, 77));
        coloration.put(32,new Color(255, 102, 102));
        coloration.put(64,new Color(255, 51, 51));
        coloration.put(128,new Color(255, 255, 153));
        coloration.put(256,new Color(255, 255, 128));
        coloration.put(512,new Color(255, 255, 77));
        coloration.put(1024,new Color(255, 255, 26));
        coloration.put(2048,new Color(0, 255, 0));
        return coloration;
    }

}
