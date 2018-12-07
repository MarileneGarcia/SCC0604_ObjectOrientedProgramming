import java.util.ArrayList;

public class AvalueGeneration
{    
    public AvalueGeneration(){}
    
       void avalueGeneration(int n_formigueiros, ArrayList<Formigueiro> formigueiro)
    {
        int k;
        int i_esperado;

        System.out.println("******* Relatório da Geração");

        for(k=0, i_esperado=380; k<n_formigueiros; k++)
        {
            formigueiro.get(k).setFitness(6* (  (formigueiro.get(k).getX_medio() - i_esperado + 400)  /  400 ) + 4* ( i_esperado - formigueiro.get(k).getX_max()  / (i_esperado - 10 )));
            formigueiro.get(k).setFitness(formigueiro.get(k).getFitness() / (float) 10);

            System.out.println(" > Formigueiro " + (k+1) + ":" );
            System.out.println("	-> X Medio: " + formigueiro.get(k).getX_medio());
            System.out.println( "	-> Numero de Formigas: " + formigueiro.get(k).getFormigas().size() );
            System.out.println( "	-> Fitness: " + formigueiro.get(k).getFitness());

            if(k==2) i_esperado = 410;
            else i_esperado += 410;
        }
    } 
}
