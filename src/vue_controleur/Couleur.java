package vue_controleur;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

public class Couleur {

    public static HashMap<Integer, Color> choix(int a) {

        HashMap<Integer, Color> coloration = new HashMap<Integer, Color>();
        coloration.clear();
        switch (a) {
            case 0:
                coloration.put(2, new Color(255, 242, 230));
                coloration.put(4, new Color(255, 204, 153));
                coloration.put(8, new Color(255, 179, 102));
                coloration.put(16, new Color(255, 166, 77));
                coloration.put(32, new Color(255, 102, 102));
                coloration.put(64, new Color(255, 51, 51));
                coloration.put(128, new Color(255, 255, 153));
                coloration.put(256, new Color(255, 255, 128));
                coloration.put(512, new Color(255, 255, 77));
                coloration.put(1024, new Color(255, 255, 26));
                coloration.put(2048, new Color(0, 255, 0));
                break;
            case 1:
                coloration.put(2, new Color(128, 255, 223));
                coloration.put(4, new Color(102, 255, 204));
                coloration.put(8, new Color(77, 255, 77));
                coloration.put(16, new Color(102, 217, 255));
                coloration.put(32, new Color(102, 179, 255));
                coloration.put(64, new Color(102, 140, 255));
                coloration.put(128, new Color(102, 102, 255));
                coloration.put(256, new Color(140, 102, 255));
                coloration.put(512, new Color(179, 102, 255));
                coloration.put(1024, new Color(217, 102, 255));
                coloration.put(2048, new Color(255, 102, 102));
                break;
            case 2:
                coloration.put(2, new Color(179, 255, 204));
                coloration.put(4, new Color(102, 255, 179));
                coloration.put(8, new Color(0, 230, 172));
                coloration.put(16, new Color(255, 128, 255));
                coloration.put(32, new Color(255, 77, 210));
                coloration.put(64, new Color(255, 77, 166));
                coloration.put(128, new Color(255, 77, 77));
                coloration.put(256, new Color(179, 0, 45));
                coloration.put(512, new Color(115, 0, 153));
                coloration.put(1024, new Color(102, 0, 51));
                coloration.put(2048, new Color(255, 229, 229));
                break;
        }
        return coloration;

    }

}
