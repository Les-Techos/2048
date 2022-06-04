package vue_controleur;

import modele.Jeu;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Direction.Direction2D;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Console2048 extends Thread implements Observer {

    private Jeu jeu;

    public Console2048(Jeu _jeu) {
        jeu = _jeu;
    }

    @Override
    public void run() {
        while(true) {
            afficher();

            synchronized (this) {
                ecouteEvennementClavier();
                try {
                    wait(); // lorsque le processus s'endort, le verrou sur this est relâché, ce qui permet au processus de ecouteEvennementClavier()
                    // d'entrer dans la partie synchronisée, ce verrou évite que le réveil du processus de la console (update(..)) ne soit exécuté avant
                    // que le processus de la console ne soit endormi

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * Correspond à la fonctionnalité de Contrôleur : écoute les évènements, et déclenche des traitements sur le modèle
     */
    private void ecouteEvennementClavier() {

        final Object _this = this;

        new Thread() {
            public void run() {
                synchronized (_this) {
                    boolean end = false;

                    while (!end) {
                        String s = null;
                        try {
                            s = Character.toString((char)System.in.read());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        
                        if (s.equals("4") || s.equals("8") || s.equals("6") || s.equals("2") ) {
                            end = true;
                            if(s.equals("4")) jeu.move(Direction2D.gauche);
                            else if(s.equals("8")) jeu.move(Direction2D.haut);
                            else if(s.equals("6")) jeu.move(Direction2D.droite);
                            else if(s.equals("2")) jeu.move(Direction2D.bas);
                        }
                    }
                }

            }
        }.start();


    }

    /**
     * Correspond à la fonctionnalité de Vue : affiche les données du modèle
     */
    private void afficher()  {


        System.out.printf("\033[H\033[J"); // permet d'effacer la console (ne fonctionne pas toujours depuis la console de l'IDE)

        for (int i = 0; i < jeu.getSize(); i++) {
            for (int j = 0; j < jeu.getSize(); j++) {
                Case2D c = null;
                try {
                    c = jeu.getCase(Coord2D.getInstance(i, j, jeu.getGrille()));
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

    }

    private void raffraichir() {
        synchronized (this) {
            try {
                notify();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        raffraichir();
    }
}
