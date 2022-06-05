package util.IA;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class MC_IA {

    protected int nb_tries = 0;
    protected int nb_threads;
    protected int nb_tasks_per_thread;
    protected ThreadPoolExecutor tpe;

    public MC_IA(int nb_tries, int nb_threads) {
        this.nb_tries = nb_tries;
        this.nb_threads = nb_threads;
        nb_tasks_per_thread = nb_tries/nb_threads;
        
        tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nb_threads);
    }

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
        double tot_score = 0;

        ArrayList<Future<Double>> results = new ArrayList<Future<Double>>();
        for(int thread_id = 0; thread_id < nb_threads; thread_id++){
            results.add(tpe.submit(new Callable<Double>(){

                @Override
                public Double call() throws Exception {
                    double score = 0;
                    for (int i = 0; i < nb_tasks_per_thread; i++) {
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
                    return score;
                }}));
        }
        for(int thread_id = 0; thread_id < nb_threads; thread_id++)
            try {
                tot_score+=results.get(thread_id).get();
            } catch (InterruptedException | ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        return tot_score/((double)nb_tries);
    }

    public void stop(){
        tpe.shutdown();
    }
}
