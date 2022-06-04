package vue_controleur.vue_Listenner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

public class JCheckboxListenner implements ActionListener {

    private JCheckBox checkBox;

    public JCheckboxListenner(JCheckBox component) {
        this.checkBox = component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        System.out.println(checkBox.isSelected());
    }
    
}
