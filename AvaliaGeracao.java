import java.util.ArrayList;
import java.util.Iterator;

public class AvaliaGeracao {
    private int k; // variaveis auxiliares
    private int x_esperado = Formigueiro.L_CENARIO - 2;
    private int nFormigueiros;

    ArrayList<Formigueiro> formigueiros;

    public AvaliaGeracao(int nFormigueiros, ArrayList<Formigueiro> formigueiros){ //inicializa a classe
        this.nFormigueiros = nFormigueiros;
        this.formigueiros = formigueiros;
    }

    public void avaliacao(){                    //avalia o codigo 
        System.out.println("----Relatorio da geracao----");

        Iterator<Formigueiro> iterator = formigueiros.iterator();
        Formigueiro aux;

        k = 0;
        while(iterator.hasNext() && k < nFormigueiros){
            aux = iterator.next(); 
            /*avalia cada formigueiro a partir da posição media das formigas e da posição da formiga que chegou mais longe */
            double formula = (0.8*((x_esperado - aux.getX_medio())/x_esperado))+ (0.2*((x_esperado-aux.getX_max())/x_esperado)); 
            float fitness = (float) formula;
            aux.setFitness(fitness); //seta o fitness do formigueiro como valor float
            k++;
        }
    }
}

/*int k;
	int i_esperado;

	cout<<" **************** Relatorio da geracao"<<endl;

	for(k=0, i_esperado=380; k<N_FORMIGUEIROS; k++)
	{
		formigueiro[k].fitness = 6* (  (formigueiro[k].x_medio - i_esperado + 400)  /  400 ) + 4* ( i_esperado - formigueiro[k].x_max  / (i_esperado - 10 ) );
		formigueiro[k].fitness = formigueiro[k].fitness / (float) 10;

		cout << endl << " > Formigueiro " << k+1 << ":" << endl;
		cout << "	-> X Medio: " << formigueiro[k].x_medio << endl;
		cout << "	-> Numero de Formigas: " << formigueiro[k].fila_formigas->tamanho << endl;
		cout << "	-> Fitness: " << formigueiro[k].fitness <<endl<<endl;

		if(k==2) i_esperado = 410;
		else i_esperado += 410;
	}*/
