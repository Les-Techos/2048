package vue_controleur;

import modele.Direction;
import modele.Jeu;
import modele.Case.Case2D;
import modele.Coord.Coord2D;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

public class Swing2048 extends JFrame implements Observer {
    private static final int PIXEL_PER_SQUARE = 60;
    // tableau de cases : i, j -> case graphique
    private JLabel[][] tabC;
    private Jeu jeu;
    private Canva dessin;
    

    public Swing2048(Jeu _jeu) {
        jeu = _jeu;
        //fenetre principale 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500); // largeur longueur
        //this.setLocationRelativeTo(null);
        this.setName("test animation");
        //this.setResizable(false);
        int taille = jeu.getSize();
        dessin =new Canva(_jeu);
        dessin.setFocusable(false);
        System.out.println(jeu.getGrille());
        //Composant
        JButton test = new JButton("test");

        //Layout et panel fils
        BorderLayout b = new BorderLayout();
        JPanel pprincipale = new JPanel(b);
        JPanel menusud = new JPanel(new FlowLayout());
        
        menusud.add(new JLabel("ICI C LE MENU"));
        menusud.add(test);
        menusud.setFocusable(false);
        pprincipale.add(dessin,BorderLayout.CENTER);
        //pprincipale.add(menusud,BorderLayout.SOUTH);
       
        this.setContentPane(pprincipale);
        this.setVisible(true);
        
        //anime("up",100);
        //anime("right",100);
        //anime("down",100);
        //anime("left",100);
        ajouterEcouteurClavier();
        rafraichir();

    }

    /**
     * Correspond à la fonctionnalité de Vue : affiche les données du modèle
     */
    private void rafraichir() {

        SwingUtilities.invokeLater(new Runnable() { // demande au processus graphique de réaliser le traitement
            @Override
            public void run() {
             dessin.repaint();
             //System.out.println("repeinture");
            }
        });

    }

    /**
     * Correspond à la fonctionnalité de Contrôleur : écoute les évènements, et
     * déclenche des traitements sur le modèle
     */
    private void ajouterEcouteurClavier() {
        addKeyListener(new KeyAdapter() { // new KeyAdapter() { ... } est une instance de classe anonyme, il s'agit d'un
                                          // objet qui correspond au controleur dans MVC
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) { // on regarde quelle touche a été pressée
                    case KeyEvent.VK_LEFT:
                        jeu.move(Direction.gauche);
                        break;
                    case KeyEvent.VK_RIGHT:
                        jeu.move(Direction.droite);
                        break;
                    case KeyEvent.VK_DOWN:
                        jeu.move(Direction.bas);
                        break;
                    case KeyEvent.VK_UP:
                        jeu.move(Direction.haut);
                        break;
                }
                if (jeu.getGrille().isfull())
                    System.out.println("La grille est pleine ! ");
                if (jeu.getGrille().iswrecked())
                    System.out.println("La grille est fucked ! ");
                if (jeu.getGrille().iswinning())
                    System.out.println("La grille est gagnante ! ");
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        rafraichir();
    }
}