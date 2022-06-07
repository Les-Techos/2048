package vue_controleur.listennermenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modele.Joueur;
import util.IA.IAReady.Jeu_IA;
import vue_controleur.Swing2048solo;

public class Singlelistenner implements ActionListener {

    private JFrame frame;
    public Singlelistenner(JFrame frame){
        this.frame = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        String s = (String)JOptionPane.showInputDialog(null, "Rentrer le nom de joueur","Joueur choix nom",JOptionPane.PLAIN_MESSAGE);
        System.out.println(s);
        Joueur joueur = new Joueur(0,s);
        Jeu_IA jeu = new Jeu_IA(4,joueur);
        Swing2048solo vue = new Swing2048solo(jeu,joueur);
        vue.setVisible(true);
        jeu.addObserver(vue);
        frame.setVisible(false);
    }

}
