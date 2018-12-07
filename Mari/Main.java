import java.util.*;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static final int GERACOES = 1;
    public static final int TIME_GERACAO = 2;
    public static final int NUM_FORMIGUEIROS = 4;

    public static void main(String[] params) throws IOException, InterruptedException
    {
        ArrayList<Formigueiro> formigueiros = new ArrayList<Formigueiro>();

        for(int aux=0; aux<NUM_FORMIGUEIROS; aux++) {
            formigueiros.add(new Formigueiro(aux, 80));
        }        

        for(int i=0; i<GERACOES; i++)
        {
            System.out.println("Geração: " + i);
            for(int j=0; j<TIME_GERACAO; j++) 
            {
                System.out.println("Loop: " + j);
                for (Formigueiro formigueiro_analisar : formigueiros) 
                {
                    System.out.println("Formigueiro: " + formigueiro_analisar.getIndice());
                    formigueiro_analisar.printMatriz();
                    System.out.println();
                    formigueiro_analisar.rodaGeracao();

                    Thread.currentThread().sleep(500);

                    System.out.println();
                }
            }

            //avaliaGeracao();
            Crossover crossover = new Crossover(NUM_FORMIGUEIROS,Formigueiro.TAM_DNA,formigueiros);
            Mutacao mutacao = new Mutacao(NUM_FORMIGUEIROS,Formigueiro.TAM_DNA,Formigueiro.TAXA_MUT,formigueiros,crossover.crossover());
            mutacao.mutacao();
            //reboot();

            System.out.println();
            System.out.println();
        }
    }
}