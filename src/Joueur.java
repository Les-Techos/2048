import java.io.Serializable;
import java.sql.Time;

public class Joueur implements Serializable{
    int score;
    String nom;

    public Joueur(String nom) {
        this.score = 0;
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

      
}
