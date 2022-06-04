package vue_controleur.vue_Listenner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;


public class JLoad implements ActionListener{

    private JFileChooser fileChooser;
    

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        fileChooser = new JFileChooser();
        File dossierbase = new File(".");
        File dossiersave = new File(dossierbase.getPath(),"2048/src/sauvegarde");
        fileChooser.setCurrentDirectory(dossiersave);
        fileChooser.showOpenDialog(null);
        
    }
    
}