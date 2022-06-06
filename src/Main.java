import modele.Jeu;
import modele.Joueur;
import util.IA.IAReady.Jeu_IA;
import vue_controleur.Console2048;
import vue_controleur.Swing2048solo;
import vue_controleur.menu2048;

public class Main {

    public static void main(String[] args) {
        // mainConsole();
        mainSwing();
    }

    public static void mainConsole() {
        Jeu jeu = new Jeu(4, null);
        Console2048 vue = new Console2048(jeu);
        jeu.addObserver(vue);

        vue.start();
    }

    public static void mainSwing() {
        
        menu2048 ma = new menu2048();
        ma.setVisible(true);
        
    }
}
