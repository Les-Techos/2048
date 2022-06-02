package util.IA;

import java.util.ArrayList;
import java.util.Random;

public class MC_IA {

    public int nb_tries = 70;

    public IA_Action getBestAction(IA_Agent base_agent){
        IA_Action best_action = null;
        double max_score = -1; // On sauvegarde le maximum des moyennes des maximums
        for (IA_Action action : base_agent.getAvailableActions()) { // Pour chaque direction
            IA_Agent g = (IA_Agent)base_agent.clone();
            g.step(action);

            double score = getScore(g);

            if (score > max_score) {
                max_score = score;
                best_action = action;
            }
        }
        return best_action;
    }

    public double getScore(IA_Agent base_agent){
        Random r = new Random();
        double score = 0;

        for (int i = 0; i < nb_tries; i++) {
            IA_Agent g = (IA_Agent)base_agent.clone();
            int nb_steps = 0;
            while (!(g.isFinished())) {
                ArrayList<IA_Action> poss = g.getAvailableActions();
                g.step(poss.get(Math.abs(r.nextInt()) % poss.size()));
                nb_steps++;
            }
            if(g.isWinning()){
                nb_steps += nb_tries;
            }
            score += nb_steps;
        }

        return score/((double)nb_tries);
    }
}
