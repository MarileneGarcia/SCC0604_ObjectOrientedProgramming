import java.util.*;
import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static final int GERACOES = 50;
    public static final int TIME_GERACAO = 300;
    public static final int NUM_FORMIGUEIROS = 4;

    public static void main(String[] params) throws IOException, InterruptedException
    {
        GuiNova janela = new GuiNova();

        ArrayList<Formigueiro> formigueiros = new ArrayList<Formigueiro>();

        int[][] matriz_0 = null;
        int[][] matriz_1 = null;
        int[][] matriz_2 = null;
        int[][] matriz_3 = null;

        for(int aux=0; aux<NUM_FORMIGUEIROS; aux++) {
            formigueiros.add(new Formigueiro(aux, 60));
        }       

        System.out.println("Esperando inicio...");
        while(janela.iniciar == false){
            System.out.print("");
        }
        System.out.println("Iniciando... " +  janela.iniciar);

        for(int i=0; (i<GERACOES) && (janela.pausar == false); i++) 
        {
            for(int j=0; (j<TIME_GERACAO) && (janela.pausar == false); j++) 
            {
                System.out.println("Loop: " + j + " (Geração: " + i + ")");      

                int index = 0;
                for (Formigueiro formigueiro_analisar : formigueiros) 
                {
                    if(index == 0) matriz_0 = formigueiro_analisar.getMatriz();
                    else if(index == 1) matriz_1 = formigueiro_analisar.getMatriz();
                    else if(index == 2) matriz_2 = formigueiro_analisar.getMatriz();
                    else if(index == 3) matriz_3 = formigueiro_analisar.getMatriz();
                    index++;
                }

                /*for (int a = 0; a < Formigueiro.H_CENARIO; a++) {
                    for (int b = 0; b < Formigueiro.L_CENARIO; b++) {
                        System.out.print(matriz_1[a][b] + " ");
                    }
                    System.out.println();
                }*/

                System.out.println("Pausar: " + janela.pausar);
                if(janela.pausar == false){
                    System.out.println("Iniciar: " + janela.iniciar);
                    if(janela.iniciar == true){
                        System.out.print("");
                        janela.editAll(matriz_0, matriz_1, matriz_2, matriz_3, i, j);

                        for (Formigueiro formigueiro_analisar : formigueiros) 
                        {
                            //System.out.println("Formigueiro: " + formigueiro_analisar.getIndice());
                            //formigueiro_analisar.printMatriz();
                            //System.out.println();
                            formigueiro_analisar.rodaGeracao();

                            //System.out.println();
                        }
                    }
                }else{
                    System.out.print("");
                    while(janela.pausar == true){
                        System.out.print("");
                    }
                }         
                //Thread.currentThread().sleep(100);
            }

            for (Formigueiro formigueiro_analisar : formigueiros) 
                formigueiro_analisar.avaliaGeracao();

            Crossover crossover = new Crossover(formigueiros);
            int melhor_formigueiro = crossover.crossover();

            Mutacao mutacao = new Mutacao(formigueiros, melhor_formigueiro);
            mutacao.mutacao();

            for (Formigueiro formigueiro_analisar : formigueiros) 
                formigueiro_analisar.rebootGeracao(60);

            //Scanner scanner = new Scanner(System.in);
            //String line = scanner.nextLine();

            //System.out.println();
            System.out.println();
        }

        System.exit(0);
    }
}