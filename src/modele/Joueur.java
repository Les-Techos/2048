package modele;

import java.io.Serializable;

public class Joueur implements Serializable {
    int score;String nom;
    public Joueur(int score, String nom) {
        this.score = score;
        this.nom = nom;
    }


    public int getScore() {
        return score;
    }
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        return "Joueur [nom=" + nom + ", score=" + score + "]";
    }
    
    
}
