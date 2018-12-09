import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Crossover {
    ArrayList<Formigueiro> formigueiros;

    public Crossover(ArrayList<Formigueiro> formigueiros) {
        this.formigueiros = formigueiros;
    }

    public int crossover(){
        //System.out.println("-----Crossover----");
        // variaveis auxiliares
        int k, m;
        float best_fitness = 1000;
        int i_best = -1; 

        Iterator<Formigueiro> iterator;

        //Descobrir qual e o melhor formigueiro
        Formigueiro formigueiro_aux;
        iterator = formigueiros.iterator();
        while(iterator.hasNext()){
            formigueiro_aux = iterator.next();
            if(formigueiro_aux.getFitness() < best_fitness)
            {
                i_best = formigueiro_aux.getIndice();
                best_fitness = formigueiro_aux.getFitness();
            }
        }
        System.out.println("Melhor formigueiro: " + i_best );

        Formigueiro melhor_formigueiro = null;
        iterator = formigueiros.iterator();
        while(iterator.hasNext()){
            formigueiro_aux = iterator.next();
            if( formigueiro_aux.getIndice() == i_best)
            {
                melhor_formigueiro = formigueiro_aux;
                System.out.println("Melhor fitness: " + melhor_formigueiro.getFitness());
            }
        }

        iterator = formigueiros.iterator();
        Random gerador = new Random();
        while (iterator.hasNext()){
            formigueiro_aux = iterator.next();
            if(formigueiro_aux.getIndice() != i_best){
                for(m = 0; m < Formigueiro.TAM_DNA; m++){
                    if(gerador.nextInt(100) < 80);
                        formigueiro_aux.setAminoacido(m, melhor_formigueiro.getAminoacido(m));
                }
            }
        }

        //System.out.println("DNA após crossover");

        /*for(Formigueiro formigueiro : formigueiros){
            for(k = 0; k < Formigueiro.TAM_DNA; k++)
                System.out.println(formigueiro.getDNA()[k]);
        }*/

        return i_best;
    }
}