package vue_controleur.vue_Listenner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

import modele.Jeu;
import util.Serializer;
import util.IA.IAReady.Jeu_IA;


public class JSave implements ActionListener{
    
    private JFileChooser fileChooser;
    private Jeu_IA jeu;
    
    public JSave(Jeu_IA jeu_param) {
        this.jeu = jeu_param;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        fileChooser = new JFileChooser();
        fileChooser.setApproveButtonText("SAVE");
        
        File dossierbase = new File(".");
        File dossiersave = new File(dossierbase.getPath(),"2048/src/sauvegarde");
        System.out.println(dossiersave.getPath());
        fileChooser.setCurrentDirectory(dossiersave);
        fileChooser.showOpenDialog(null);
        try {
            String fileName = fileChooser.getSelectedFile().getName()+".txt";

            Serializer.save(jeu.getGrille(),dossiersave.toPath()+"\\"+fileName);
            System.out.println("fichier sauver sous le nom " + fileName);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            System.out.println("Rentrer un nom de fichier");
        }
        
    }

    public void setJeu(Jeu_IA j) {
        this.jeu = j;
    }
    
}
