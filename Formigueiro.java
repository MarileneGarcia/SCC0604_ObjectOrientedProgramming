import java.util.*;  

public class Formigueiro{

    /* Constantes para a matriz do formigueiro */
    public static final int VAZIO = 0; // branco
    public static final int FORMIGA = 1; // vermelho
    public static final int AGUA = 2; // azul
    public static final int PAREDE = 3; // preto
    
    public static final int L_CENARIO = 60;
    public static final int H_CENARIO = 30;

    private static final int TAXA_MUT = 40;
    private static final int TAM_DNA = 16;

    private int indice;
    private ArrayList<Formiga> formigas;
    private int num_formigas;
    private int DNA[];
    private float x_medio;
    private int x_max;
    private float fitness;
    private int matriz[][]; 

    public Formigueiro(int indice, int preenchimento) {
        this.indice = indice;
        formigas = new ArrayList<Formiga>();
        DNA = new int[TAM_DNA];
        num_formigas = 0;
        x_medio = -1;
        x_max = -1;
        fitness = 0;

        Random gerador = new Random();
        matriz = new int[H_CENARIO][L_CENARIO];
        for (int i = 0; i < H_CENARIO; i++) {
            for (int j = 0; j < L_CENARIO; j++) {
                if (i == 0 || i == (H_CENARIO - 1) || j == 0 || j == (L_CENARIO - 1))
                    matriz[i][j] = PAREDE;
                else if (j > 0.5 * L_CENARIO && j < 0.7 * L_CENARIO)
                    matriz[i][j] = AGUA;
                else if (j <= 0.5 * L_CENARIO){
                    if (gerador.nextInt(100)<preenchimento){
                        matriz[i][j] = FORMIGA;
                        formigas.add(new Formiga(i,j));
                        num_formigas++;
                    }
                }
                else 
                    matriz[i][j] = VAZIO;
            }
        }
        //System.out.println("Numero de formigas " + formigas.size());

        //System.out.print("DNA do formigueiro:  ");
        for(int k=0; k<TAM_DNA; k++){
            DNA[k] = gerador.nextInt(6);
            //System.out.print(DNA[k] + " ");
        }
        //System.out.println();
    }

    public int getIndice(){
        return this.indice;
    }

    public int getCelula(int x, int y) {
        return matriz[x][y];
    }

    /*public void setDNA(int[] DNA) {
        this.DNA = DNA;
    }*/

    /*public int[] getDNA() {
        return DNA;
    }*/

    public void printMatriz() {
        for (int i = 0; i < H_CENARIO; i++) {
            for (int j = 0; j < L_CENARIO; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void setX_medio(float x_medio) {
        this.x_medio = x_medio;
    }

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }

    public void setX_max(int x_max) {
        this.x_max = x_max;
    }

    public float getX_medio() {
        return x_medio;
    }

    public int getX_max() {
        return x_max;
    }

    public float getFitness() {
        return fitness;
    }

    public void rodaGeracao() {
        System.out.println("Numero de formigas antes: " + formigas.size());
        Iterator<Formiga> iter_formiga = formigas.iterator();
        while ( iter_formiga.hasNext() ) {  
            Formiga formiga_analisar = iter_formiga.next();
            //System.out.println(" > Posicao: (" + formiga_analisar.getX() + " " + formiga_analisar.getY() + ")");
            //System.out.println(" > Vida: " + formiga_analisar.getVida());
            formiga_analisar.setVisao(matriz);
            formiga_analisar.getDecisao(DNA);
            formiga_analisar.setMov(matriz);
            if(formiga_analisar.getVida() <= 0)
                iter_formiga.remove();
            //System.out.println();
        }
        System.out.println("Numero de formigas depois: " + formigas.size());
    }
}
