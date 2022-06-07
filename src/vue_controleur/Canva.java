package vue_controleur;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.*;
import java.lang.Math;



import modele.Jeu;
import modele.Coord.Coord2D;
import modele.Grille.Grille2D;


public class Canva extends JPanel {
    private int posX = 0;
    int posY = 0;
    private int xcase;
    int ycase;
    private int ecart;
    private int nbcolonnes;
    int nblignes;
    private Jeu jeu;
    private  HashMap<Integer,Color> coloration;
    

    public Canva(Jeu _jeu) {
        this.jeu = _jeu;
        this.setName("Canva");
        this.nbcolonnes = _jeu.getSize();
        this.nblignes = _jeu.getSize(); 
        this.coloration = Couleur.choix(0);
    }

    public void paintComponent(Graphics g) {
       
        // fond a update
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
        g.setFont(new Font("TimesRoman", Font.BOLD, 20));
        // taille du canva Setup des tailles cases et leur écarts
        // System.out.println(this.getName() + " taille " + this.getSize() );

        ecart = 14;
        xcase = (this.getWidth() - (ecart * nbcolonnes)) / (nbcolonnes);
        ycase = (this.getHeight() - (ecart * nblignes)) / (nblignes);
        Grille2D grille = jeu.getGrille();
       
        for (int i = 0; i < nblignes; i++) {
            for (int j = 0; j < nbcolonnes; j++) {
                Coord2D coordcase = Coord2D.getInstance(j, i, grille); // obtenir les coordonnées
                if(jeu.getCase(coordcase)!=null){
                int valeurcase = jeu.getCase(coordcase).getValeur();
                
                g.setColor(coloration.get(valeurcase));
                g.fillRoundRect(ecart / 2 + posX + (xcase + ecart) * i, ecart / 2 + posY + (ycase + ecart) * j, xcase,ycase,10,10);
                if(valeurcase< 8){
                g.setColor(Color.BLACK);
                }else{
                g.setColor(Color.WHITE);
                }
                g.setFont(new Font("SansSerif Bold", Font.BOLD, Math.min(xcase,ycase)/3));
                int decale_txt = g.getFontMetrics().stringWidth(valeurcase+" ");
                g.drawString(valeurcase+"",ecart / 2 +xcase/2 + posX + (xcase + ecart) * i-decale_txt/2,ecart / 2 +ycase/2 + posY + (ycase + ecart) * j +5); 
                // on pourrait utiliser la classe fontmetric pour bien centrer les numéro hors la police de base de drawstring fait des string de hauteur 5 et longeuers de 5 à l'infini
            }else{
                g.setColor(Color.LIGHT_GRAY);
                g.fillRoundRect(ecart / 2 + posX + (xcase + ecart) * i, ecart / 2 + posY + (ycase + ecart) * j, xcase,ycase,10,10);
            }
            }
        }
    }

    // Permet de bouger une seule case


    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public HashMap<Integer, Color> getColoration() {
        return coloration;
    }

    public void setColoration(HashMap<Integer, Color> coloration) {
        this.coloration = coloration;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

}
