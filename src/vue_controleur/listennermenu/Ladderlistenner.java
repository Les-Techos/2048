package vue_controleur.listennermenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import sauvegarde.Highscore;

public class Ladderlistenner implements ActionListener {
    private File fichier_save = new File("./src/HIGHSCORE.TXT");
    private Highscore fichier;
    
    public Ladderlistenner(Highscore highscore){
        this.fichier = highscore;
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        fichier.update();
        JOptionPane.showMessageDialog(null, fichier.afficher(),"Meilleurs score",JOptionPane.INFORMATION_MESSAGE);
        
        
        
    }

}
