package vue_controleur.vue_Listenner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import modele.Joueur;
import modele.Direction.Direction2D;
import util.IA.MC_IA;
import util.IA.IAReady.Grille2D_IA;
import util.IA.IAReady.Jeu_IA;

public class JCheckboxListenner implements ActionListener {

    private JCheckBox checkBox;
    private Jeu_IA jeu;
    private Thread t;
    private Joueur j;

    public JCheckboxListenner(JCheckBox component,Jeu_IA jeu2, Joueur j) {
        this.j = j;
        this.jeu = jeu2;
        this.checkBox = component;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        
        
        if(checkBox.isSelected() && t == null){

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                

                Grille2D_IA g = (Grille2D_IA) jeu.getGrille();
                MC_IA ia = new MC_IA(50, 6, g); // Tu peux laisser ces paramètres ils sont optimaux
                int step = 0;
        
                while (!g.isFinished()) { // Tant que notre noeud courant n'est pas terminé
                
                Direction2D act = (Direction2D) ia.getBestAction(g).getAction(); // On récupère la meilleur action à effectuer
                jeu.move(act); // On applique la modif
        
                System.out.println("Etape n° " + step++ + " / " + act); // affiche l'action effectuée
                System.out.println(g); // affiche la grille
            }
            ia.stop();
            System.out.println("l'ia a fini elle a trouvé en " + step + "coups");
            }	});
            t.start();
        }else{
            if(t != null){
                t.stop();
                t = null;
            }
            
        }

    }
    
    public void setJeu(Jeu_IA j) {
        this.jeu = j;
    }
}
