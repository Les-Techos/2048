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
    // tableau de cases : i, j -> case graphique
    private Jeu jeu;
    private Canva dessin;
    

    public Swing2048(Jeu _jeu) {
        jeu = _jeu;
        //fenetre principale 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500); // largeur longueur
        //this.setLocationRelativeTo(null);
        this.setName("test animation");
        this.setFocusable(true);
        //this.setResizable(false);
        int taille = jeu.getSize();
        dessin =new Canva(_jeu);
        dessin.setFocusable(false);
        System.out.println(jeu.getGrille());
        //Composant
        JButton test = new JButton("test");
        test.setFocusable(false);

        //Layout et panel fils
        BorderLayout b = new BorderLayout();
        JPanel pprincipale = new JPanel(b);
        pprincipale.setName("pprincipale");
        JPanel menusud = new JPanel(new FlowLayout());
        menusud.setFocusable(false);
        menusud.setName("menu");

        menusud.add(new JLabel("ICI C LE MENU"));
        menusud.add(test);
        pprincipale.add(dessin,BorderLayout.CENTER);
        pprincipale.add(menusud,BorderLayout.SOUTH);
        pprincipale.setFocusable(false);
       
        this.setContentPane(pprincipale);
        this.setVisible(true);
        ajouterEcouteurClavier();
        rafraichir();
        System.out.println("Frame " +this.isFocusOwner());
        
        System.out.println("Panel " +pprincipale.isFocusOwner());
        System.out.println("menu " +menusud.isFocusOwner());
        System.out.println("bouton " +test.isFocusOwner());

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