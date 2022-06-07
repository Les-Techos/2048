package sauvegarde;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

import modele.Joueur;

public class Highscore {

    private Joueur[] players;
    private Scanner obj;
    private File fichier_save;

    public Highscore() {
        this.players = new Joueur[10];
        this.fichier_save = new File("2048/src/HIGHSCORE.TXT");

        try {
            this.obj = new Scanner(fichier_save);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int j = 0; j < players.length; j++) {
            // System.out.println(obj.nextLine());
            if(obj.hasNextLine()) { 
            String[] ligne = obj.nextLine().split("-");

            players[j] = new Joueur(Integer.parseInt(ligne[2]), ligne[1]);
            
        }else{}
    }
    System.out.println("taille="+players.length);
    }

    //affichage console
    public String afficher(){
        String res ="<html>";
        for(int i=0; i<players.length; i++){
            res=res+i+" nom "+players[i].getNom()+" score: "+players[i].getScore()+"<br/>";
        }
        res =res + "</html>";
        return res;
    }


    // Update le fichier
    public void update(){
        try {
            PrintWriter printWriter = new PrintWriter(fichier_save);
            for (int j = 0; j < players.length; j++) {
                // System.out.println(obj.nextLine());
                printWriter.print(j+"-"+players[j].getNom()+"-"+players[j].getScore()+"\n");   
            }
            printWriter.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //Sert a ajouter un highscore au tableau

    public void add(Joueur j) {
        int step = 0;
        if(j.getScore() > players[9].getScore()){
        for(int i=0; i< players.length; i++) {   
            if(players[i].getScore() > j.getScore()){
                step++;
            }else{break;}
        }  
        for(int i=players.length-2; i>=step; i--){
           players[i+1] = players[i];
        }
        players[step] = j;}

    }

}
