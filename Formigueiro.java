import java.util.*;  

public class Formigueiro{

    /* Constantes para a matriz do formigueiro */
    public static final int VAZIO = 0; // branco
    public static final int FORMIGA = 1; // vermelho
    public static final int AGUA = 2; // azul
    public static final int PAREDE = 3; // preto
    
    public static final int L_CENARIO = 530;
    public static final int H_CENARIO = 280;

    public static final int TAXA_MUT = 15;
    public static final int TAM_DNA = 16;

    private int indice;
    private ArrayList<Formiga> formigas;
    private int DNA[];
    private float x_medio;
    private int x_max;
    private float num_best;
    private float fitness;
    private int matriz[][]; 

    public Formigueiro(int indice, int preenchimento) {
        this.indice = indice;
        formigas = new ArrayList<Formiga>();
        DNA = new int[TAM_DNA];
        x_medio = -1;
        x_max = -1;
        num_best = 0;
        fitness = 0;

        Random gerador = new Random();
        matriz = new int[L_CENARIO][H_CENARIO];
        for (int j = 0; j < H_CENARIO; j++) {
            for (int i = 0; i < L_CENARIO; i++) {
                if (j == 0 || j == (H_CENARIO - 1) || i == 0 || i == (L_CENARIO - 1))
                    matriz[i][j] = PAREDE;
                else if (i > 0.5 * L_CENARIO && i < 0.7 * L_CENARIO)
                    matriz[i][j] = AGUA;
                else if (i <= 0.5 * L_CENARIO){
                    if (gerador.nextInt(100)<preenchimento){
                        matriz[i][j] = FORMIGA;
                        formigas.add(new Formiga(i,j));
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

    public int[][] getMatriz() {
        return matriz;
    }

    public void setDNA(int[] DNA) {
        this.DNA = DNA;
    }

    public int[] getDNA() {
        return DNA;
    }

    public void setAminoacido(int indice, int valor) {
        this.DNA[indice] = valor;
    }

    public int getAminoacido(int indice) {
        return this.DNA[indice];
    }

    public void printMatriz() {
        for (int j = 0; j < H_CENARIO; j++) {
            for (int i = 0; i < L_CENARIO; i++) {
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

    public ArrayList<Formiga> getFormigas() { return formigas; }

    public float getX_medio() {
        return x_medio;
    }

    public void removeFormiga(int i){ formigas.remove(i);}

    public int getX_max() {
        return x_max;
    }

    public float getFitness() {
        return fitness;
    }

    public void rodaGeracao() {
        //System.out.println("Numero de formigas antes: " + formigas.size());
        Iterator<Formiga> iter_formiga = formigas.iterator();
        while ( iter_formiga.hasNext() ) {  
            Formiga formiga_analisar = iter_formiga.next();
            //System.out.println(" > Posicao: (" + formiga_analisar.getX() + " " + formiga_analisar.getY() + ")");
            //System.out.println(" > Vida: " + formiga_analisar.getVida());
            formiga_analisar.setVisao(matriz);
            formiga_analisar.getDecisao(DNA);
            formiga_analisar.setMov(matriz);
            if(formiga_analisar.getVida() <= 0)
            {
                iter_formiga.remove();

            }
            //System.out.println();
        }
        //System.out.println("Numero de formigas depois: " + formigas.size());
    }

    public void avaliaGeracao() {
        Iterator<Formiga> iter_formiga = formigas.iterator();
        float num = 0;
        float den = 0;
        int x_max_aux = 0;
        while ( iter_formiga.hasNext() ) {  
            Formiga formiga_analisar = iter_formiga.next();
            num += formiga_analisar.getX();
            den++;
            if(formiga_analisar.getX() > x_max_aux) x_max_aux = formiga_analisar.getX();
            if(formiga_analisar.getX() > 0.5 * L_CENARIO) num_best++;
        }

        this.x_medio = num / den;
        this.x_max = x_max_aux;

        int x_esperado = L_CENARIO - 2;

        //this.fitness = (float) 0.1 * ( (x_esperado - this.x_medio) / x_esperado );
        this.fitness = (float) ( (formigas.size() - this.num_best) / formigas.size() );
        //this.fitness += (float) 0.6 * ( (x_esperado - this.x_max) / x_esperado );

        System.out.println(" > Formigueiro " + indice + ":" );
        System.out.println( "	-> Numero de Formigas: " + this.formigas.size() );
        System.out.println("	-> X Medio: " + this.x_medio);
        System.out.println("	-> X Maximo: " + this.x_max);
        System.out.println("	-> Formigas legais: " + this.num_best);
        System.out.println( "	-> Fitness: " + this.fitness);
    }

    public void rebootGeracao(int preenchimento) {
        formigas.clear();
        this.x_medio = -1;
        this.x_max = -1;
        this.num_best = 0;
        this.fitness = 0;

        Random gerador = new Random();
        matriz = new int[L_CENARIO][H_CENARIO];
        for (int j = 0; j < H_CENARIO; j++) {
            for (int i = 0; i < L_CENARIO; i++) {
                if (j == 0 || j == (H_CENARIO - 1) || i == 0 || i == (L_CENARIO - 1))
                    matriz[i][j] = PAREDE;
                else if (i > 0.5 * L_CENARIO && i < 0.7 * L_CENARIO)
                    matriz[i][j] = AGUA;
                else if (i <= 0.5 * L_CENARIO){
                    if (gerador.nextInt(100)<preenchimento){
                        matriz[i][j] = FORMIGA;
                        formigas.add(new Formiga(i,j));
                    }
                }
                else 
                    matriz[i][j] = VAZIO;
            }
        }
    }
}
