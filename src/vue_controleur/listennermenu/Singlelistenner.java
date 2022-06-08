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

    public Singlelistenner(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        frame.setVisible(false);
        String s1 = JOptionPane.showInputDialog(null, "Rentrer le nom de joueur");

        if(s1 != null){
            if(!s1.isEmpty()){
                Joueur joueur = new Joueur(0, s1);
                Jeu_IA jeu = new Jeu_IA(4, joueur);
                Swing2048solo vue = new Swing2048solo(jeu, joueur,frame);
                vue.setVisible(true);
                jeu.addObserver(vue);
                
        } else {
            frame.setVisible(true);
        }}
    else{
        frame.setVisible(true);
    }

}}
