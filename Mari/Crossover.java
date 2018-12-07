import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Crossover {
    private int k, m, best = 0; // variaveis auxiliares
    private int nFormigueiros;
    private int tamDNA;

    ArrayList<Formigueiro> formigueiros;

    public Crossover(int nFormigueiros, int tamDNA, ArrayList<Formigueiro> formigueiros) {
        this.nFormigueiros = nFormigueiros;
        this.formigueiros = formigueiros;
        this.tamDNA = tamDNA;
    }

    public int crossover(){
        System.out.println("-----Crossover----");
        Iterator<Formigueiro> iterator1 = formigueiros.iterator();
        Iterator<Formigueiro> iterator2 = formigueiros.iterator();
        Iterator<Formigueiro> iterator3 = formigueiros.iterator();
        Formigueiro aux;

        //Descobrir qual e o melhor formigueiro
        k = 0;
        while(iterator1.hasNext() && k < nFormigueiros){
                aux = iterator1.next();
                if(aux.getFitness() > best)
                    best = k;
                k++;
        }

        Formigueiro melhorFormigueiro = new Formigueiro(1,1);
        k = 0;
        while(iterator2.hasNext() && k < nFormigueiros){
            aux = iterator2.next();
            if(k == best)
                melhorFormigueiro = aux;
            k++;
        }

        k = 0;
        Random gerador = new Random();
        while (iterator3.hasNext() || k < nFormigueiros){
            aux = iterator3.next();
            if(k != best){
                for(m = 0; m < tamDNA; m++){
                    if(gerador.nextInt(2) == 0)
                            aux.setDNA(melhorFormigueiro.getDNA());
                }
            }
            k++;
        }

        System.out.println("DNA após crossover");

        for(Formigueiro formigueiro : formigueiros){
            for(k = 0; k < tamDNA; k++)
            System.out.println(formigueiro.getDNA()[k]);
        }

        return best;
    }
}