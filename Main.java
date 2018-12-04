import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static final int GERACOES = 1;
    public static final int TIME_GERACAO = 50;

    public static void main(String[] params) throws IOException, InterruptedException
    {
        Formigueiro formigueiro_1 = new Formigueiro(1, 80);
        for(int i=0; i<GERACOES; i++)
        {
            System.out.println("Geração: " + i);
            for(int j=0; j<TIME_GERACAO; j++)
            {
                System.out.println("Loop: " + j);
                formigueiro_1.printMatriz();
                System.out.println();
                formigueiro_1.rodaGeracao();

                Thread.currentThread().sleep(500);

                System.out.println();
            }

            System.out.println();
            System.out.println();
        }
    }
}