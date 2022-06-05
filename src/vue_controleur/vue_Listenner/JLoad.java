package vue_controleur.vue_Listenner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import modele.Jeu;
import modele.Grille.Grille2D;
import util.Serializer;
import vue_controleur.Canva;


public class JLoad implements ActionListener{

    private JFileChooser fileChooser;
    private Jeu jeu;
    
    public JLoad(Jeu jeu_param) {
        this.jeu = jeu_param;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        fileChooser = new JFileChooser();
        File dossierbase = new File(".");
        File dossiersave = new File(dossierbase.getPath(),"2048/src/sauvegarde");
        fileChooser.setCurrentDirectory(dossiersave);
        fileChooser.showOpenDialog(null);
        String fileload =  fileChooser.getSelectedFile().getAbsolutePath();
        try {
            jeu.setGrille((Grille2D) Serializer.load(fileload));
        } catch (ClassNotFoundException | IOException e1) {
            // TODO Auto-generated catch block
            //e1.printStackTrace();
            JOptionPane.showMessageDialog(null,"Erreur lors du chargement");
        }
        
    }

    public void setJeu(Jeu j) {
        this.jeu = j;
    }
    
}