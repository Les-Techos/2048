package util.IA;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * IA Montécarlo, reprenant le célèbre algorithme de résolution
 */
public class MC_IA implements Runnable {

    protected int nb_tries = 0; // Nombre d'essais
    protected int nb_threads; // Nombre de threads
    protected int nb_tasks_per_thread; // Nombre de tâches par thread
    protected IA_Node node;
    protected ThreadPoolExecutor tpe; // pool d'exécution
    protected static Semaphore s = new Semaphore(1);

    /**
     * 
     * @param nb_tries   Nombre d'essais par branche
     * @param nb_threads Nombre de thread alloués
     */
    public MC_IA(int nb_tries, int nb_threads, IA_Node node) {
        this.nb_tries = nb_tries;
        this.nb_threads = nb_threads;
        nb_tasks_per_thread = nb_tries / nb_threads;
        this.node = node;

        tpe = (ThreadPoolExecutor) Executors.newFixedThreadPool(nb_threads);
    }

    /**
     * Retourne la meilleure action pour le noeud passé en paramètre
     * 
     * @param base_agent
     * @return
     */
    public IA_Action getBestAction(IA_Node base_agent) {
        IA_Action best_action = null;
        double max_score = -1; // On sauvegarde le maximum des moyennes des maximums
        for (IA_Action action : base_agent.getAvailableActions()) { // Pour chaque direction
            IA_Node g = (IA_Node) base_agent.clone();
            g.step(action);

            double score = getScore(g);

            if (score > max_score) {
                max_score = score;
                best_action = action;
            }
        }
        return best_action;
    }

    public IA_Action getBestAction() {
        return getBestAction(node);
    }

    /**
     * Calcul un score pour le noeud base_agent
     * 
     * @param base_agent
     * @return
     */
    public double getScore(IA_Node base_agent) {
        Random r = new Random();
        double tot_score = 0;

        ArrayList<Future<Double>> results = new ArrayList<Future<Double>>();
        for (int thread_id = 0; thread_id < nb_threads; thread_id++) {
            results.add(tpe.submit(new Callable<Double>() {

                @Override
                public Double call() throws Exception {
                    double score = 0;
                    for (int i = 0; i < nb_tasks_per_thread; i++) {
                        IA_Node g = (IA_Node) base_agent.clone();
                        int nb_steps = 0;
                        while (!(g.isFinished())) {
                            ArrayList<IA_Action> poss = g.getAvailableActions();
                            g.step(poss.get(Math.abs(r.nextInt()) % poss.size()));
                            nb_steps++;
                        }
                        if (g.isWinning()) {
                            nb_steps += nb_tries;
                        }
                        score += nb_steps;
                    }
                    return score;
                }
            }));
        }
        for (int thread_id = 0; thread_id < nb_threads; thread_id++)
            try {
                tot_score += results.get(thread_id).get();
            } catch (InterruptedException | ExecutionException e) {
            }
        return tot_score / ((double) nb_tries);
    }

    public double getScore() {
        return getScore(node);
    }

    /**
     * éteint la pool d'exécution
     */
    public void stop() {
        tpe.shutdown();
    }

    @Override
    public void run() {
        int step = 0;

        while (!node.isFinished()) { // Tant que notre noeud courant n'est pas terminé

            IA_Action act = getBestAction(); // On récupère la meilleur action à
                                                                             // effectuer
            node.applyAction(act); // On applique la modif
            
            System.out.println("Etape n° " + step++ + " / " + act.getAction().toString()); // affiche l'action effectuée
            System.out.println(node); // affiche la grille
        }
        stop();
        System.out.println("l'ia a fini elle a trouvé en " + step + "coups");
    }

}
