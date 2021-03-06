package vue_controleur.listennermenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.Joueur;
import util.IA.IAReady.Jeu_IA;
import vue_controleur.Swing2048duo;
import vue_controleur.Swing2048solo;

public class Multilistenner implements ActionListener {

    private JFrame frame;

    public Multilistenner(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        frame.setVisible(false);
        String s1 = (String) JOptionPane.showInputDialog(null, "Rentrer le nom du joueur 1", "nom",
                JOptionPane.PLAIN_MESSAGE);
        if (s1 != null) {
            if (!s1.isEmpty()) {
                String s2 = (String) JOptionPane.showInputDialog(null, "Rentrer le nom du joueur 2", "nom",
                        JOptionPane.PLAIN_MESSAGE);
                if (s2 != null) {
                    if (!s2.isEmpty()) {
                        Joueur joueur1 = new Joueur(0, s1);
                        Jeu_IA jeu1 = new Jeu_IA(5, joueur1);
                        Joueur joueur2 = new Joueur(0, s2);
                        Jeu_IA jeu2 = new Jeu_IA(5, joueur2);
                        Swing2048duo vue = new Swing2048duo(jeu1, jeu2, joueur1, joueur2,frame);
                        vue.setVisible(true);
                        jeu1.addObserver(vue);
                        jeu2.addObserver(vue);
                    } else {
                        frame.setVisible(true);
                    }
                } else {
                    frame.setVisible(true);
                }
            } else {
                frame.setVisible(true);
            }

        } else {
            frame.setVisible(true);
        } 
    }

}