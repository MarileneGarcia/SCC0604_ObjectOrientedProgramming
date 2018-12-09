import java.util.*;  
import java.lang.*;

public class Formiga{
    // Constantes de informação da formiga
    public static final int MAX_VIDA = 5;

    /* Possiveis decisoes: caracteristicas do DNA */ 
    public static final int SEGUE_AGUA = 0;
    public static final int SEGUE_PAREDE = 1;
    public static final int SEGUE_FORMIGAS = 2;
    public static final int SEGUE_VAZIO = 3;
    public static final int FICA_PARADO = 4;
    public static final int PROCURA_ALEATORIAMENTE = 5;

    /* Possiveis decisoes (logicas) */ 
    public static final int PARADO = 0;
    public static final int NORTE = 1;
    public static final int LESTE = 2;
    public static final int SUL = 3;
    public static final int OESTE = 4;

    // Variáveis que definem a classe formiga
    private int x;
    private int y;
    private int vida;
    private int visao_norte;
    private int visao_leste;
    private int visao_sul;
    private int visao_oeste;
    private int decisao;

    //Construtor
    public Formiga(int x, int y){
        this.x = x;
        this.y = y;
        this.vida = MAX_VIDA;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getVida(){
        return this.vida;
    }

    public int getNorte(){
        return this.visao_norte;
    }

    public int getLeste(){
        return this.visao_leste;
    }

    public int getSul(){
        return this.visao_sul;
    }

    public int getOeste(){
        return this.visao_oeste;
    }

    public void setVida(int vida) { this.vida = vida; }

    public void setVisao(int[][] matriz) {
        int norte = -1, sul = -1, leste = -1, oeste = -1;
        int x = this.getX();
        int y = this.getY();


        switch(matriz[x][y-1]) // Norte
        {
            case Formigueiro.PAREDE:
                norte = Formigueiro.PAREDE;
            break;

            case Formigueiro.AGUA:
                norte = Formigueiro.AGUA;
            break;

            case Formigueiro.FORMIGA:
                norte = Formigueiro.FORMIGA;
            break;

            case Formigueiro.VAZIO:
                norte = Formigueiro.VAZIO;
            break;
        }

        switch(matriz[x+1][y]) // Leste
        {
            case Formigueiro.PAREDE:
                leste = Formigueiro.PAREDE;
            break;

            case Formigueiro.AGUA:
                leste = Formigueiro.AGUA;
            break;

            case Formigueiro.FORMIGA:
                leste = Formigueiro.FORMIGA;
            break;

            case Formigueiro.VAZIO:
                leste = Formigueiro.VAZIO;
            break;
        }

        switch(matriz[x][y+1]) // Sul
        {
            case Formigueiro.PAREDE:
                sul = Formigueiro.PAREDE;
            break;

            case Formigueiro.AGUA:
                sul = Formigueiro.AGUA;
            break;

            case Formigueiro.FORMIGA:
                sul = Formigueiro.FORMIGA;
            break;

            case Formigueiro.VAZIO:
                sul = Formigueiro.VAZIO;
            break;
        }

        switch(matriz[x-1][y]) // Oeste
        {
            case Formigueiro.PAREDE:
                oeste = Formigueiro.PAREDE;
            break;

            case Formigueiro.AGUA:
                oeste = Formigueiro.AGUA;
            break;

            case Formigueiro.FORMIGA:
                oeste = Formigueiro.FORMIGA;
            break;

            case Formigueiro.VAZIO:
                oeste = Formigueiro.VAZIO;
            break;
        }

        this.visao_norte = norte;
        this.visao_oeste = oeste;
        this.visao_sul = sul;
        this.visao_leste = leste;

        //System.out.println(" > Campo de visao");
        //System.out.println(" 	-> Visao Norte:  " + this.visao_norte);
        //System.out.println(" 	-> Visao Oeste:  " + this.visao_oeste);
        //System.out.println(" 	-> Visao Sul:    " + this.visao_sul);
        //System.out.println(" 	-> Visao Leste:  " + this.visao_leste);
    }


    public void getDecisao(int[] DNA) {
        int estado;
        int agua, formigas, vazio, parede;
        int total  = 0;
        int potenc = 1;

        if( (visao_norte == Formigueiro.AGUA) || ( visao_oeste == Formigueiro.AGUA) || (visao_sul == Formigueiro.AGUA) || (visao_leste == Formigueiro.AGUA) )
            agua = 1;
        else agua = 0;

        if( (visao_norte == Formigueiro.FORMIGA) || ( visao_oeste == Formigueiro.FORMIGA) || (visao_sul == Formigueiro.FORMIGA) || (visao_leste == Formigueiro.FORMIGA) )
            formigas = 1;
        else formigas = 0;

        if( (visao_norte == Formigueiro.VAZIO) || ( visao_oeste == Formigueiro.VAZIO) || (visao_sul == Formigueiro.VAZIO) || (visao_leste == Formigueiro.VAZIO) )
            vazio = 1;
        else vazio = 0;

        if( (visao_norte == Formigueiro.PAREDE) || ( visao_oeste == Formigueiro.PAREDE) || (visao_sul == Formigueiro.PAREDE) || (visao_leste == Formigueiro.PAREDE) )
            parede = 1;
        else parede = 0;

        estado = agua*1000 + formigas*100 + vazio*10 + parede*1;

        //System.out.println(" > Estado: " + estado);

        while(estado > 0)
        {
            total += estado % 10 * potenc;
            estado = estado / 10;
            potenc = potenc * 2;
        }

        this.decisao = DNA[total];

        //System.out.println(" > Estado(num): " + total);

        //System.out.println(" > Decisão: " + this.decisao);
    }

    public void setMov(int[][] matriz) {
        //System.out.println(" > Vida antes do move: " + this.vida);
        int aux;
        int move = PARADO;
        int count_aux = 0;
        boolean flag[] = {false,false,false,false};
        Random gerador = new Random();

        switch(decisao)
        {
            case SEGUE_AGUA:
                if( visao_norte == Formigueiro.AGUA ) {
                    count_aux++;
                    flag[0] = true;
                }
                if( visao_leste == Formigueiro.AGUA ) {
                    count_aux++;
                    flag[1] = true;
                }
                if( visao_oeste == Formigueiro.AGUA ) {
                    count_aux++;
                    flag[2] = true;
                }
                if( visao_sul == Formigueiro.AGUA ) {
                    count_aux++;
                    flag[3] = true;
                }

                if(count_aux == 0) move = gerador.nextInt(5);
                else if(count_aux == 1) {
                    if( flag[0] == true ) move = NORTE;
                    else if( flag[1] == true ) move = LESTE;
                    else if( flag[2] == true ) move = SUL;
                    else if( flag[3] == true ) move = OESTE;
                }
                else {
                    int i_aux;
                    do{
                        i_aux = gerador.nextInt(4);
                    } while(flag[i_aux] == false);

                    if( i_aux == 0 ) move = NORTE;
                    else if( i_aux == 1 ) move = LESTE;
                    else if( i_aux == 2 ) move = SUL;
                    else if( i_aux == 3 ) move = OESTE;
                }

            break;

            case SEGUE_PAREDE:
                if( visao_norte == Formigueiro.PAREDE ) {
                    count_aux++;
                    flag[0] = true;
                }
                if( visao_leste == Formigueiro.PAREDE ) {
                    count_aux++;
                    flag[1] = true;
                }
                if( visao_oeste == Formigueiro.PAREDE ) {
                    count_aux++;
                    flag[2] = true;
                }
                if( visao_sul == Formigueiro.PAREDE ) {
                    count_aux++;
                    flag[3] = true;
                }

                if(count_aux == 0) move = gerador.nextInt(5);
                else if(count_aux == 1) {
                    if( flag[0] == true ) move = NORTE;
                    else if( flag[1] == true ) move = LESTE;
                    else if( flag[2] == true ) move = SUL;
                    else if( flag[3] == true ) move = OESTE;
                }
                else {
                    int i_aux;
                    do{
                        i_aux = gerador.nextInt(4);
                    } while(flag[i_aux] == false);

                    if( i_aux == 0 ) move = NORTE;
                    else if( i_aux == 1 ) move = LESTE;
                    else if( i_aux == 2 ) move = SUL;
                    else if( i_aux == 3 ) move = OESTE;
                }
            break;

            case SEGUE_FORMIGAS:
                if( visao_norte == Formigueiro.FORMIGA ) {
                    count_aux++;
                    flag[0] = true;
                }
                if( visao_leste == Formigueiro.FORMIGA ) {
                    count_aux++;
                    flag[1] = true;
                }
                if( visao_oeste == Formigueiro.FORMIGA ) {
                    count_aux++;
                    flag[2] = true;
                }
                if( visao_sul == Formigueiro.FORMIGA ) {
                    count_aux++;
                    flag[3] = true;
                }

                if(count_aux == 0) move = gerador.nextInt(5);
                else if(count_aux == 1) {
                    if( flag[0] == true ) move = NORTE;
                    else if( flag[1] == true ) move = LESTE;
                    else if( flag[2] == true ) move = SUL;
                    else if( flag[3] == true ) move = OESTE;
                }
                else {
                    int i_aux;
                    do{
                        i_aux = gerador.nextInt(4);
                    } while(flag[i_aux] == false);

                    if( i_aux == 0 ) move = NORTE;
                    else if( i_aux == 1 ) move = LESTE;
                    else if( i_aux == 2 ) move = SUL;
                    else if( i_aux == 3 ) move = OESTE;
                }
            break;

            case SEGUE_VAZIO:
                if( visao_norte == Formigueiro.VAZIO ) {
                    count_aux++;
                    flag[0] = true;
                }
                if( visao_leste == Formigueiro.VAZIO ) {
                    count_aux++;
                    flag[1] = true;
                }
                if( visao_oeste == Formigueiro.VAZIO ) {
                    count_aux++;
                    flag[2] = true;
                }
                if( visao_sul == Formigueiro.VAZIO ) {
                    count_aux++;
                    flag[3] = true;
                }

                if(count_aux == 0) move = gerador.nextInt(5);
                else if(count_aux == 1) {
                    if( flag[0] == true ) move = NORTE;
                    else if( flag[1] == true ) move = LESTE;
                    else if( flag[2] == true ) move = SUL;
                    else if( flag[3] == true ) move = OESTE;
                }
                else {
                    int i_aux;
                    do{
                        i_aux = gerador.nextInt(4);
                    } while(flag[i_aux] == false);

                    if( i_aux == 0 ) move = NORTE;
                    else if( i_aux == 1 ) move = LESTE;
                    else if( i_aux == 2 ) move = SUL;
                    else if( i_aux == 3 ) move = OESTE;
                }
            break;

            case FICA_PARADO:
                move = PARADO;
            break;

            case PROCURA_ALEATORIAMENTE:
                move = gerador.nextInt(5);
            break;
        }

        int x = this.x;
        int y = this.y;
        int vida = this.vida;
        int m, n;

        switch(move)
        {
            case NORTE:
                if( (matriz[x][y-1] == Formigueiro.PAREDE) || (matriz[x][y-1] == Formigueiro.FORMIGA) ) move = PARADO;
            break;

            case LESTE:
                if( (matriz[x+1][y] == Formigueiro.PAREDE) || (matriz[x+1][y] == Formigueiro.FORMIGA) ) move = PARADO;
            break; 

            case SUL:
                if( (matriz[x][y+1] == Formigueiro.PAREDE) || (matriz[x][y+1] == Formigueiro.FORMIGA) ) move = PARADO; 
            break;

            case OESTE:
                if( (matriz[x-1][y] == Formigueiro.PAREDE) || (matriz[x-1][y] == Formigueiro.FORMIGA) ) move = PARADO;
            break;
        }

        switch(move)
        {
            case PARADO:
            break;

            case NORTE:
                if( (this.x > 0.5 * Formigueiro.L_CENARIO) && (this.x < 0.7 * Formigueiro.L_CENARIO) )
                    matriz[x][y] = Formigueiro.AGUA;
                else
                    matriz[x][y] = Formigueiro.VAZIO;
                this.x = x;
                this.y = y-1;
                matriz[x][y-1] = Formigueiro.FORMIGA;
            break;

            case LESTE:	
                if( (this.x > 0.5 * Formigueiro.L_CENARIO) && (this.x < 0.7 * Formigueiro.L_CENARIO) )
                    matriz[x][y] = Formigueiro.AGUA;
                else
                    matriz[x][y] = Formigueiro.VAZIO;
                this.x = x+1;
                this.y = y;
                matriz[x+1][y] = Formigueiro.FORMIGA;
            break;

            case SUL:
                if( (this.x > 0.5 * Formigueiro.L_CENARIO) && (this.x < 0.7 * Formigueiro.L_CENARIO) )
                    matriz[x][y] = Formigueiro.AGUA;
                else
                    matriz[x][y] = Formigueiro.VAZIO;
                this.x = x;
                this.y = y + 1;
                matriz[x][y+1] = Formigueiro.FORMIGA;
            break;

            case OESTE:
                if( (this.x > 0.5 * Formigueiro.L_CENARIO) && (this.x < 0.7 * Formigueiro.L_CENARIO) )
                    matriz[x][y] = Formigueiro.AGUA;
                else
                    matriz[x][y] = Formigueiro.VAZIO;
                this.x = x - 1;
                this.y = y;
                matriz[x-1][y] = Formigueiro.FORMIGA;
            break;
        }

        int count_neighbor = 0;
        if( (this.x > 0.5 * Formigueiro.L_CENARIO) && (this.x < 0.7 * Formigueiro.L_CENARIO) ) {
            if(this.visao_norte == Formigueiro.AGUA) count_neighbor++;
            if(this.visao_leste == Formigueiro.AGUA) count_neighbor++;
            if(this.visao_sul == Formigueiro.AGUA) count_neighbor++;
            if(this.visao_oeste == Formigueiro.AGUA) count_neighbor++;

            if(count_neighbor > 3) this.vida = this.vida - 1; 
        }
            
        //System.out.println(" > Nova posicao: (" + this.x + " " + this.y + ")");
        //System.out.println(" > Nova vida: " + this.vida);
    }
}