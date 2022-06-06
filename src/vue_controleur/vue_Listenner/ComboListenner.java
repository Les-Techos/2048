package vue_controleur.vue_Listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import vue_controleur.Canva;
import vue_controleur.Couleur;

public class ComboListenner implements ActionListener {
    private Canva canva;
    private JComboBox combocolor;

    public ComboListenner(JComboBox b, Canva canva) {
        this.canva = canva;
        this.combocolor = b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println(  combocolor.getSelectedIndex());
        canva.setColoration( Couleur.choix(combocolor.getSelectedIndex()));
    }

    public void setJeu(Canva c) {
        this.canva = c;
    }
    
}
