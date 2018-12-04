import java.io.IOException;
import java.util.Scanner;

public class Main
{
    public static final int GERACOES = 50;

    public static void main(String[] params) throws IOException, InterruptedException
    {
        Formigueiro formigueiro_1 = new Formigueiro(1, 80);
        for(int i=0; i<GERACOES; i++)
        {
            System.out.println("Geração: " + i);
            formigueiro_1.printMatriz();
            System.out.println();
            formigueiro_1.rodaGeracao();

            Thread.currentThread().sleep(500);

            System.out.println();
        }
    }
}