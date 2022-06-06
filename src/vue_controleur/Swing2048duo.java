package vue_controleur;

import modele.Jeu;
import modele.Joueur;
import modele.Case.Case2D;
import modele.Coord.Coord2D;
import modele.Direction.Direction2D;
import modele.Grille.Grille2D;
import util.Serializer;
import util.IA.IAReady.Jeu_IA;
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



public class Swing2048duo extends JFrame implements Observer {
    // tableau de cases : i, j -> case graphique
    
    private Jeu_IA jeu1;
    private Jeu_IA jeu2;
    private Canva dessin1;
    private Canva dessin2;
    private JLabel playerscore1;
    private JLabel playerscore2;
    private JButton sauvegarde;
    private JButton multi;
    private JLoad loadlistenner;
    private JSave savelistenener;
    private JButton charger;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private ComboListenner comboboxlistenner1;
    private ComboListenner comboboxlistenner2;
    private JCheckBox checkBox;
    private JCheckboxListenner checkboxlistenner;
    private Joueur joueur1;
    private Joueur joueur2;
    
    


    String couleurs1[] ={"Classique","Menthe","Eté"};
    String couleurs2[] ={"Eté","Menthe","Classique"};
    

    public Swing2048duo(Jeu_IA _jeu1,Jeu_IA _jeu2,Joueur j1,Joueur j2) {
        jeu1 = _jeu1;
        jeu2 = _jeu2;
        joueur1 = j1;
        joueur2 = j2;
        // obtention des dimensions de l'écran de l'utilisateur
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int) screenSize.getHeight()/2;
        int width = (int) screenSize.getWidth()/2;

        //fenetre principale 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setMinimumSize(new Dimension(400,400)); 
        this.setName("2048 Fares Tim");
        this.setFocusable(true);
        
        
        
        int taille = jeu1.getSize();
        
        // setup du canva
        dessin1 = new Canva(jeu1);
        dessin1.setFocusable(false);
        dessin2 =new Canva(jeu2);
        dessin2.setFocusable(false);
        System.out.println(jeu1.getGrille());
        System.out.println(jeu2.getGrille());

        //Nom  + score du joueur
        playerscore1 = new JLabel(joueur1.getNom()+":"+joueur1.getScore());
        playerscore1.setFocusable(false);
        playerscore2 = new JLabel(joueur2.getNom()+":"+joueur2.getScore());
        playerscore2.setFocusable(false);

        
        //bouton sauvegarde
        sauvegarde = new JButton("Sauvegarder");
        sauvegarde.setFocusable(false);

        //bouton charger
        charger = new JButton("Charger");
        charger.setFocusable(false);

        //list de couleurs pour le jeu
        comboBox1 = new JComboBox(couleurs1);
        comboBox1.setFocusable(false);
        comboBox2 = new JComboBox(couleurs2);
        comboBox2.setFocusable(false);
        // on le mets sur la deux pour avoir une couleur différente au début
        comboBox2.setSelectedIndex(1);

        // Lancer le monteCarlo
        checkBox = new JCheckBox("Lancer IA");
        checkBox.setFocusable(false);


        //Layout et panel fils
        BorderLayout b = new BorderLayout();
        JPanel pprincipale = new JPanel(b);
        JPanel ecrancinde = new JPanel(new GridLayout(1,2));
        JPanel menusud = new JPanel(new FlowLayout());
        menusud.setFocusable(false);
        pprincipale.setFocusable(false);
        ecrancinde.setFocusable(false);
        
        // Ajout au  panel
        ecrancinde.add(dessin1);
        ecrancinde.add(dessin2);
        menusud.add(playerscore1);
        menusud.add(sauvegarde);
        menusud.add(charger);
        menusud.add(comboBox1);
        menusud.add(checkBox);
        menusud.add(comboBox2);
        menusud.add(playerscore2);
        pprincipale.add(ecrancinde,BorderLayout.CENTER);
        pprincipale.add(menusud,BorderLayout.SOUTH);
        

        //Listenner des composants
         comboboxlistenner1 = new ComboListenner(comboBox1,dessin1);
         comboBox1.addActionListener(comboboxlistenner1);
         comboboxlistenner2 = new ComboListenner(comboBox2,dessin2);
         comboBox2.addActionListener(comboboxlistenner2);
         checkboxlistenner = new JCheckboxListenner(checkBox,jeu1,joueur1);
         checkBox.addActionListener(checkboxlistenner);
         savelistenener = new JSave(jeu1);
         sauvegarde.addActionListener(savelistenener);
         loadlistenner = new JLoad(jeu1);
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
             dessin1.repaint();
             dessin2.repaint();
             playerscore1.setText(joueur1.getNom()+":"+joueur1.getScore());
             playerscore2.setText(joueur2.getNom()+":"+joueur2.getScore());
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
                        jeu1.move(Direction2D.gauche);
                        jeu2.move(Direction2D.gauche);
                        break;
                    case KeyEvent.VK_RIGHT:
                        jeu1.move(Direction2D.droite);
                        jeu2.move(Direction2D.droite);
                        break;
                    case KeyEvent.VK_DOWN:
                        jeu1.move(Direction2D.bas);
                        jeu2.move(Direction2D.bas);
                        break;
                    case KeyEvent.VK_UP:
                        jeu1.move(Direction2D.haut);
                        jeu2.move(Direction2D.haut);
                        break;
                    case KeyEvent.VK_S:
                        try {
                            Serializer.save(jeu1.getGrille(), "GrilleSave.txt");
                            System.out.println("La grille est sauvegardée");
                        } catch (IOException e1) {
                            System.out.println("Erreur lors de la sauvegarde de la grille");
                            e1.printStackTrace();
                        }
                        break;
                    case KeyEvent.VK_L:
                        try {
                            jeu1.setGrille((Grille2D) Serializer.load("GrilleSave.txt"));
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
        this.dessin1 = c;
        comboboxlistenner1.setJeu(c);
        comboboxlistenner2.setJeu(c);
    }

    public void setJeu(Jeu_IA j){
        this.jeu1 = j;
        loadlistenner.setJeu(j);
        savelistenener.setJeu(j);
    }

    @Override
    public void update(Observable o, Object arg) {
        rafraichir();
    }
}