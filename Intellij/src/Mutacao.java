import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

//Classe para a mutacao de alguns formigueiros
public class Mutacao {
    ArrayList<Formigueiro> formigueiros;
    int i_best;

    public Mutacao(ArrayList<Formigueiro> formigueiros, int i_best) {
        this.formigueiros = formigueiros;
        this.i_best = i_best;
    }

    public void mutacao(){
        //System.out.println("----Mutacao----");

        // variavel auxiliar
        int m;

        Random gerador = new Random(); //Inicializacao de um objeto para gerar valores aleatórios

        Iterator<Formigueiro> iterator = formigueiros.iterator();
        Formigueiro formigueiro_aux;

        /*Se não for o melhor formigueiro (garantir que o melhor vai ser preservado) e se um número
         gerado entre 0 e 100 for menor que a taxa de mutação (garantir a aleatoriedade) então o
         DNA desse formigueiro recebe valores aleatórios (mutação) */
        while (iterator.hasNext()){
            formigueiro_aux = iterator.next();
            if( formigueiro_aux.getIndice() != i_best ){
                for(m = 0; m < Formigueiro.TAM_DNA; m++){
                    if(gerador.nextInt(101) < Formigueiro.TAXA_MUT);
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
