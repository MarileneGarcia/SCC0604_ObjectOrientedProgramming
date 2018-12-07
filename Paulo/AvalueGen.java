public class AvalueGeneration
{    
    public AvalueGeneration(){}
    
       void avalueGeneration()
    {
        int k;
        int i_esperado;

        System.out.println("******* Relatório da Geração");

        for(k=0, i_esperado=380; k<N_FORMIGUEIROS; k++)
        {
            formigueiro.get(k).getFitness() = 6* (  (formigueiro.get(k).getX_medio() - i_esperado + 400)  /  400 ) + 4* ( i_esperado - formigueiro.get(k).getX_max()  / (i_esperado - 10 ) );
            formigueiro.get(k).getFitness() = formigueiro.get(k).fitness() / (float) 10;

            System.out.println(" > Formigueiro " + (k+1) + ":" );
            System.out.println("	-> X Medio: " + formigueiro.get(k).x_medio());
            System.out.println( "	-> Numero de Formigas: " + formigueiro.get(k).getFila_formigas().getTamanho() );
            System.out.println( "	-> Fitness: " + formigueiro.get(k).getFitness());

            if(k==2) i_esperado = 410;
            else i_esperado += 410;
        }
    } 
}
