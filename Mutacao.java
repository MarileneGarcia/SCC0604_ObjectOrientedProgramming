import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Mutacao {
    ArrayList<Formigueiro> formigueiros;
    int i_best;

    public Mutacao(ArrayList<Formigueiro> formigueiros, int i_best) {
        this.formigueiros = formigueiros;
        this.i_best = i_best;
    }

    public void mutacao(){
        //System.out.println("----Mutacao----");
        int m;

        Iterator<Formigueiro> iterator = formigueiros.iterator();
        Formigueiro formigueiro_aux;

        Random gerador = new Random();
        while (iterator.hasNext()){
            formigueiro_aux = iterator.next();
            if( formigueiro_aux.getIndice() != i_best ){
                for(m = 0; m < Formigueiro.TAM_DNA; m++){
                    if(gerador.nextInt(100) < Formigueiro.TAXA_MUT);
                        formigueiro_aux.setAminoacido(m, gerador.nextInt(6));
                }
            }
        }
        //System.out.println("DNA após crossover");

        /*for(Formigueiro formigueiro : formigueiros){
            for(k = 0; k < tamDNA; k++)
                System.out.println(formigueiro.getDNA()[k]);
        }*/
    }
 }
