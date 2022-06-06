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
import util.IA.IAReady.Jeu_IA;
import vue_controleur.Canva;


public class JLoad implements ActionListener{

    private JFileChooser fileChooser;
    private Jeu_IA jeu;
    
    public JLoad(Jeu_IA jeu_param) {
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

    public void setJeu(Jeu_IA j) {
        this.jeu = j;
    }
    
}