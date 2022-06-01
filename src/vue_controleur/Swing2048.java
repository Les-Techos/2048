package vue_controleur;

import modele.Direction;
import modele.Jeu;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;
import util.Serializer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.atomic.AtomicReference;

public class Swing2048 extends JFrame implements Observer {
    private static final int PIXEL_PER_SQUARE = 60;
    // tableau de cases : i, j -> case graphique
    private JLabel[][] tabC;
    private Jeu jeu;

    public Swing2048(Jeu _jeu) {
        jeu = _jeu;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(jeu.getSize() * PIXEL_PER_SQUARE, jeu.getSize() * PIXEL_PER_SQUARE);
        tabC = new JLabel[jeu.getSize()][jeu.getSize()];

        JPanel contentPane = new JPanel(new GridLayout(jeu.getSize(), jeu.getSize()));

        for (int i = 0; i < jeu.getSize(); i++) {
            for (int j = 0; j < jeu.getSize(); j++) {
                Border border = BorderFactory.createLineBorder(Color.darkGray, 5);
                tabC[i][j] = new JLabel();
                tabC[i][j].setBorder(border);
                tabC[i][j].setHorizontalAlignment(SwingConstants.CENTER);

                contentPane.add(tabC[i][j]);

            }
        }
        setContentPane(contentPane);
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
                for (int i = 0; i < jeu.getSize(); i++) {
                    for (int j = 0; j < jeu.getSize(); j++) {
                        Case2D c = null;
                        try {
                            c = jeu.getCase(Coord2D.getInstance(i, j, jeu.getGrille()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (c == null) {

                            tabC[i][j].setText("");

                        } else {
                            tabC[i][j].setText(c.getValeur() + "");
                        }

                    }
                }
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
                    case KeyEvent.VK_S:
                        try {
                            Serializer.save(jeu.getGrille(), "GrilleSave.txt");
                            System.out.println("La grille est sauvegardée");
                        } catch (IOException e1) {
                            System.out.println("Erreur lors de la sauvegarde de la grille");
                            e1.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_L:
                        try {
                            jeu.setGrille((Grille2D) Serializer.load("GrilleSave.txt"));
                            System.out.println("La grille est chargée");
                        }catch (ClassNotFoundException e1) {
                            System.out.println("Erreur lors du chargement de la grille");
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            System.out.println("Erreur lors de l'ouverture du fichier");
                            e1.printStackTrace();
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void update(Observable o, Object arg) {
        rafraichir();
    }
}