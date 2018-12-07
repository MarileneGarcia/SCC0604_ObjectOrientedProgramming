import java.util.ArrayList;

public class RebootPopulation
{
    private ArrayList<ArrayList<PIXEL>> pixels = new ArrayList<ArrayList<PIXEL>>();
    private ArrayList<Formigueiro> fumi = new ArrayList<Formigueiro>();

    public RebootPopulation(){}
    
    
    void rebootPopulation(ArrayList<ArrayList<PIXEL>> matriz, ArrayList<Formigueiro> formigueiro)
    {
        int k, i, j;
        int deltaI=0, deltaJ=0;
        int num_formigas, tamanho;
        int x, y;
        int m, n;
        ArrayList<PIXEL> formiga_remover = new ArrayList<PIXEL>;

        for(k=0; k<N_FORMIGUEIROS; k++)
        {
            num_formigas = formigueiro.get(k).getFila_formigas().getTamanho();
            for(tamanho = 0; tamanho<num_formigas; tamanho++)
            {
                formiga_remover = formigueiro.get(k).getFila_formigas().desenfileirar();
                x = formiga_remover.getX();
                y = formiga_remover.getY();
                for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
                {
                    for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                        if(  pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
                            if( ( x+m >= 190 && x+m < 240 ) || ( x+m >= 600 && x+m < 650 ) || ( x+m >= 1010 && x+m < 1060 ) )
                            {
                                matriz.get(x+m).get(y+n).setType("A");
                                matriz.get(x+m.get(y+n).setVida(-1);
                            }
                            else 
                            {
                                matriz.get(x+m).get(y+n).setType("VAZIO");
                                matriz.get(x+m).get(y+n).setVida(-1);
                            }
                }
            }	
        }


        for(k=0; k<N_FORMIGUEIROS; k++)
        {
            for(i=10+deltaI; i<190+deltaI; i++)
            {
                for(j=10+deltaJ; j<310+deltaJ; j++)
                {
                    if( (rand() % 101 >= 80) &&
                    (matriz.get(i+ANT_RATIO).get(j).getType() != F) && (matriz.get(i-ANT_RATIO).get(j).getType != F) &&
                    (matriz.get(i).get(j+ANT_RATIO).getType() != F) && (matriz.get(i).get(j-ANT_RATIO).getType != F) &&
                    (matriz.get(i+ANT_RATIO).get(j).getType() != P) && (matriz.get(i-ANT_RATIO).get(j).getType != P) &&
                    (matriz.get(i).get(j+ANT_RATIO).getType() != P) && (matriz.get(i).get(j-ANT_RATIO).type != P) )
                    {
                        matriz.get(i).get(j).getVida() = MAX_VIDA;
                        for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
                        {
                            for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                            {
                                if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) )
                                {
                                    matriz.get(i+m).get(j+n).setType("F");
                                    if(!(m==0 && n==0)) matriz.get(i+m).get(j+n).setVida(-1);
                                }
                            }
							
                        }  
                        enfileirar(formigueiro.get(k).getFila_formigas(), &matriz.get(i).get(j));
                    }
                }
            }
            if(k==2) 
            {
                deltaJ += 310;
                deltaI = 0;
            }
            else deltaI += 410;
        }
    } 
