package vue_controleur;

import java.awt.Dimension;
import java.awt.*;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sauvegarde.Highscore;
import vue_controleur.listennermenu.Ladderlistenner;
import vue_controleur.listennermenu.Multilistenner;
import vue_controleur.listennermenu.Singlelistenner;

public class menu2048 extends JFrame {

    private JButton single;
    private JButton multi;
    private JButton ladder;
    private Singlelistenner jeusolo; 
    private Highscore h;


    public menu2048() {

        // obtention des dimensions de l'écran de l'utilisateur
        
        h = new Highscore();
        // fenetre principale
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300, 300);
        this.setResizable(false);
        this.setTitle("2048");
        this.setFocusable(true);

        JPanel fenetreprincipal = new JPanel();
        single = new JButton("solo");
        multi = new JButton("multi");
        ladder = new JButton("highscore");

        Singlelistenner singlelistenner = new Singlelistenner(this);
        single.addActionListener(singlelistenner);
        Multilistenner multilistenner = new Multilistenner(this);
        multi.addActionListener(multilistenner); 
        Ladderlistenner  ladderlistenner = new Ladderlistenner(h);
        ladder.addActionListener(ladderlistenner);

        fenetreprincipal.setLayout(new GridLayout(3,1));
        fenetreprincipal.add(single);
        fenetreprincipal.add(multi);
        fenetreprincipal.add(ladder);
        this.add(fenetreprincipal);

    }
}