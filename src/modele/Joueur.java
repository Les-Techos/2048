package modele;
import java.io.Serializable;
import java.sql.Time;

public class Joueur implements Serializable  {
    int meilleur_score;
    Time min_time;
    String nom;

    public Joueur(int meilleur_score, Time min_time, String nom) {
        this.meilleur_score = meilleur_score;
        this.min_time = min_time;
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "[nom : " + nom + "; min_time : " + min_time.toString() + "; meilleur_score : " + meilleur_score + "]";
    }    
}
