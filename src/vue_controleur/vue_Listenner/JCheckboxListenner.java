package vue_controleur.vue_Listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import modele.Joueur;
import modele.Direction.Direction2D;
import util.IA.MC_IA;
import util.IA.IAReady.Grille2D_IA;
import util.IA.IAReady.Jeu_IA;

public class JCheckboxListenner implements ActionListener {

    private JCheckBox checkBox;
    private Jeu_IA jeu;
    private Thread t;
    private Joueur j;

    public JCheckboxListenner(JCheckBox component, Jeu_IA jeu2, Joueur j) {
        this.j = j;
        this.jeu = jeu2;
        this.checkBox = component;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if (checkBox.isSelected() && t == null) {
            t = new Thread(new MC_IA(50, 8, (Grille2D_IA) jeu.getGrille()));
            t.start();
        } else {
            if (t != null) {
                t.stop();
                t = null;
            }

        }

    }

    public void setJeu(Jeu_IA j) {
        this.jeu = j;
    }
}
