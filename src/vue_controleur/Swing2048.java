package vue_controleur;

import modele.Jeu;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Direction.Direction2D;
import modele.Grille.Grille2D;
import util.Serializer;
import vue_controleur.vue_Listenner.ComboListenner;
import vue_controleur.vue_Listenner.JCheckboxListenner;
import vue_controleur.vue_Listenner.JLoad;
import vue_controleur.vue_Listenner.JSave;

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
    // tableau de cases : i, j -> case graphique
    static boolean iaplay;
    private Jeu jeu;
    private Canva dessin;
    private JProgressBar progressBar;
    private JButton sauvegarde;
    private JLoad loadlistenner;
    private JSave savelistenener;
    private JButton charger;
    private JComboBox comboBox;
    private ComboListenner comboboxlistenner;
    private JCheckBox checkBox;
    private JCheckboxListenner checkboxlistenner;
    private Boolean iajoue;
    


    String couleurs[] ={"Classique","Menthe","Eté"};

    public Swing2048(Jeu _jeu) {
        jeu = _jeu;
        // obtention des dimensions de l'écran de l'utilisateur
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) screenSize.getHeight()/2;
        int width = (int) screenSize.getWidth()/2;

        //fenetre principale 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height); 
        this.setName("2048 Fares Tim");
        this.setFocusable(true);
        
        
        
        int taille = jeu.getSize();
        
        // setup du canva
        dessin =new Canva(_jeu);
        dessin.setFocusable(false);
        System.out.println(jeu.getGrille());
        
        
        //bouton sauvegarde
        sauvegarde = new JButton("Sauvegarder");
        sauvegarde.setFocusable(false);

        //bouton charger
        charger = new JButton("Charger");
        charger.setFocusable(false);

        //list de couleurs pour le jeu
        comboBox = new JComboBox(couleurs);
        comboBox.setFocusable(false);

        // Lancer le monteCarlo
        checkBox = new JCheckBox("Lancer IA");
        checkBox.setFocusable(false);


        //Layout et panel fils
        BorderLayout b = new BorderLayout();
        JPanel pprincipale = new JPanel(b);
        JPanel menusud = new JPanel(new FlowLayout());
        menusud.setFocusable(false);
        pprincipale.setFocusable(false);

        // Ajout au  panel
        menusud.add(new JLabel("ICI C LE MENU"));
        menusud.add(sauvegarde);
        menusud.add(charger);
        menusud.add(comboBox);
        menusud.add(checkBox);
        pprincipale.add(dessin,BorderLayout.CENTER);
        pprincipale.add(menusud,BorderLayout.SOUTH);
        

        //Listenner des composants
         comboboxlistenner = new ComboListenner(comboBox,dessin);
         comboBox.addActionListener(comboboxlistenner);
         checkboxlistenner = new JCheckboxListenner(checkBox);
         checkBox.addActionListener(checkboxlistenner);
         savelistenener = new JSave(jeu);
         sauvegarde.addActionListener(savelistenener);
         loadlistenner = new JLoad(jeu);
         charger.addActionListener(loadlistenner);


       // Frame de base
        this.setContentPane(pprincipale);
        this.setVisible(true);
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
                        jeu.move(Direction2D.gauche);
                        break;
                    case KeyEvent.VK_RIGHT:
                        jeu.move(Direction2D.droite);
                        break;
                    case KeyEvent.VK_DOWN:
                        jeu.move(Direction2D.bas);
                        break;
                    case KeyEvent.VK_UP:
                        jeu.move(Direction2D.haut);
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


    // pour obtenir dans nos listenner de composant l'état actuel du 2048
    public void setCanva(Canva c){
        this.dessin = c;
        comboboxlistenner.setJeu(c);
    }

    public void setJeu(Jeu j){
        this.jeu = j;
        loadlistenner.setJeu(j);
    }

    @Override
    public void update(Observable o, Object arg) {
        rafraichir();
    }
}