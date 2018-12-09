import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Mutacao {
    private int k, m, best; // variaveis auxiliares
    private int nFormigueiros;
    private int tamDNA, taxaMutacao;

    ArrayList<Formigueiro> formigueiros;

    public Mutacao(int nFormigueiros, int tamDNA, int taxaMutacao, ArrayList<Formigueiro> formigueiros, int best) {
        this.nFormigueiros = nFormigueiros;
        this.tamDNA = tamDNA;
        this.taxaMutacao = taxaMutacao;
        this.formigueiros = formigueiros;
        this.best = best;
    }

    public void mutacao(){
        System.out.println("----Mutacao----");

        Iterator<Formigueiro> iterator = formigueiros.iterator();
        Formigueiro aux;

        k = 0;
        Random gerador = new Random();
        while (iterator.hasNext() || k < nFormigueiros){
            aux = iterator.next();
            if(k != best){
                for(m = 0; m < tamDNA; m++){
                    if(gerador.nextInt(101) >= taxaMutacao);
                        aux.setAminoacidos();
                }
            }
            k++;
        }
        System.out.println("DNA após crossover");

        for(Formigueiro formigueiro : formigueiros){
            for(k = 0; k < tamDNA; k++)
                System.out.print(formigueiro.getDNA()[k]);
            System.out.print("\n");
        }
    }
 }