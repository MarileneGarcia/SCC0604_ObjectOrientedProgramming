import java.util.*;  
import java.lang.*;

public class Formiga{
    // Constantes de informação da formiga
    private static final int RAIO_VISAO = 2;
    private static final int MAX_VIDA = 5;

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

    public void setVisao(int[][] matriz) {
        int i, j;
        int norte = -1, sul = -1, leste = -1, oeste = -1;
        int contador_norte = 0, contador_sul = 0, contador_leste = 0, contador_oeste = 0;
        int x = this.getX();
        int y = this.getY();

        for(i = x - RAIO_VISAO; i <= x + RAIO_VISAO; i++)
        {
            for(j = y - RAIO_VISAO; j <= y + RAIO_VISAO; j++)
            {
                if( (i>=0) && (j>=0) && (i<Formigueiro.H_CENARIO) && (j<Formigueiro.L_CENARIO) )
                {
                    if ( Math.pow(i-x,2) + Math.pow(j-y,2) <= Math.pow(RAIO_VISAO,2) )
                    {
                        if( (j >= i) && (j >= -i) ) // Norte
                        {
                            switch(matriz[i][j])
                            {
                                case Formigueiro.PAREDE:
                                    norte = Formigueiro.PAREDE;
                                break;
            
                                case Formigueiro.AGUA:
                                    norte = Formigueiro.AGUA;
                                break;
            
                                case Formigueiro.FORMIGA:
                                    if (norte == -1) 
                                        contador_norte++;
                                break;
                            }
                        }

                        else if( (j < i) && (j > -i) ) // Leste
                        {
                            switch(matriz[i][j])
                            {
                                case Formigueiro.PAREDE:
                                    leste = Formigueiro.PAREDE;
                                break;
            
                                case Formigueiro.AGUA:
                                    leste = Formigueiro.AGUA;
                                break;
            
                                case Formigueiro.FORMIGA:
                                    if(leste == -1) 
                                        contador_leste++;
                                break;
                            }
                        }
            
                        else if( (j <= i) && (j <= -i) )  // Sul
                        {
                            switch(matriz[i][j])
                            {
                                case Formigueiro.PAREDE:
                                    sul = Formigueiro.PAREDE;
                                break;
            
                                case Formigueiro.AGUA:
                                    sul = Formigueiro.AGUA;
                                break;
            
                                case Formigueiro.FORMIGA:
                                    if(sul == -1)
                                        contador_sul++;
                                break;
                            }
                        }
            
                        else if( (j > i) && (j < -i) )  // Oeste
                        {
                            switch(matriz[i][j])
                            {
                                case Formigueiro.PAREDE:
                                    oeste = Formigueiro.PAREDE;
                                break;
            
                                case Formigueiro.AGUA:
                                    oeste = Formigueiro.AGUA;
                                break;
            
                                case Formigueiro.FORMIGA:
                                    if(oeste == -1)
                                        contador_oeste++;
                                break;
                            }
                        }
                    }
                }
            }
        }

        double max_contador =  (0.7 * 0.25 * Math.PI) * (Math.pow(RAIO_VISAO,2))  ; 

        if(norte == -1) 
        {
            if( contador_norte >= (int) max_contador ) norte = Formigueiro.FORMIGA;
            else norte = Formigueiro.VAZIO;
        }

        if(oeste == -1) 
        {
            if( contador_oeste >= (int) max_contador ) oeste = Formigueiro.FORMIGA;
            else oeste = Formigueiro.VAZIO;
        }

        if(sul == -1) 
        {
            if( contador_sul >= (int) max_contador ) sul = Formigueiro.FORMIGA;
            else sul = Formigueiro.VAZIO;
        }
        
        if(leste == -1) 
        {
            if( contador_leste >= (int) max_contador ) leste = Formigueiro.FORMIGA;
            else leste = Formigueiro.VAZIO;
        }

        this.visao_norte = norte;
        this.visao_oeste = oeste;
        this.visao_sul = sul;
        this.visao_leste = leste;

        System.out.println(" > Campo de visao");
        System.out.println(" 	-> Visao Norte:  " + this.visao_norte);
        System.out.println(" 	-> Visao Oeste:  " + this.visao_oeste);
        System.out.println(" 	-> Visao Sul:    " + this.visao_sul);
        System.out.println(" 	-> Visao Leste:  " + this.visao_leste);
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

        System.out.println(" > Estado: " + estado);

        while(estado > 0)
        {
            total += estado % 10 * potenc;
            estado = estado / 10;
            potenc = potenc * 2;
        }

        this.decisao = DNA[total];

        System.out.println(" > Estado(num): " + total);

        System.out.println(" > Decisão: " + this.decisao);
    }

    /*public void setMov(int[][] matriz) {
        //cout<<"Vida no inicio da move: " << formiga_mover->vida <<endl;
        int aux;
        int move;
        int count_aux = 0;
        boolean flag_norte = false, flag_leste = false, flag_sul = false, flag_oeste = false;
        Random gerador = new Random();

        switch(decisao)
        {
            case SEGUE_AGUA:
                if( visao_norte == AGUA ) {
                    count_aux++;
                    flag_norte = true;
                }
                if( visao_leste == AGUA ) {
                    count_aux++;
                    flag_leste = true;
                }
                if( visao_oeste == AGUA ) {
                    count_aux++;
                    flag_oeste = true;
                }
                if( visao_sul == AGUA ) {
                    count_aux++;
                    flag_sul = true;
                }

                if(count_aux == 0) move = gerador.nextInt(5);
                else if(count_aux == 1) {
                    if( flag_norte == true ) move = NORTE;
                    else if( flag_leste == true ) move = LESTE;
                    else if( flag_oeste == true ) move = SUL;
                    else if( flag_sul == true ) move = OESTE;
                }
                else {
                    
                }

            break;

            case SEGUE_PAREDE:
                if( visao_formiga->S == P ) move = SUL;
                else if( visao_formiga->L == P ) move = LESTE;
                else if( visao_formiga->N == P ) move = NORTE;
                else if( visao_formiga->O == A ) move = OESTE; 
                else move = 
            break;

            case SEGUE_MUITAS_FORMIGAS:
                if( visao_formiga->N == MAX_F ) move = NORTE;
                else if( visao_formiga->L == MAX_F ) move = LESTE;
                else if( visao_formiga->O == MAX_F ) move = OESTE;
                else if( visao_formiga->S == MAX_F ) move = SUL;
                else move = 1+ rand() % 5;
            break;

            case SEGUE_POUCAS_FORMIGAS:
                if( visao_formiga->S == MIN_F ) move = SUL;
                else if( visao_formiga->L == MIN_F ) move = LESTE;
                else if( visao_formiga->O == MIN_F ) move = OESTE;
                else if( visao_formiga->N == MIN_F ) move = NORTE; 
                else move = 1+ rand() % 5;
            break;

            case FICA_PARADO:
                move = PARADO;
            break;

            case PROCURA_ALEATORIAMENTE:
                move = rand() % 5;
            break;
        }

        int x = this.x;
        int y = this.y;
        int vida = this.vida;
        int m, n;

        switch(move)
        {
            case NORTE:
                if( (matriz[x][y-1-ANT_RATIO].type == P) ) move = PARADO;
            break;

            case LESTE:
                if( (matriz[x+1+ANT_RATIO][y].type == P) ) move = PARADO;
            break; 

            case SUL:
                if( (matriz[x][y+1+ANT_RATIO].type == P) ) move = PARADO; 
            break;

            case OESTE:
                if( (matriz[x-1-ANT_RATIO][y].type == P) ) move = PARADO;
            break;
        }

        for(m=-ANT_RATIO; ( move != PARADO ) && ( m<=ANT_RATIO ); m++)
        {
            for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                if(  pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
                    if( ( x+m >= 190 && x+m < 240 ) || ( x+m >= 600 && x+m < 650 ) || ( x+m >= 1010 && x+m < 1060 ) )
                    {
                        matriz[x+m][y+n].type = A;
                        matriz[x+m][y+n].vida = -1;
                    }
                    else 
                    {
                        matriz[x+m][y+n].type = VAZIO;
                        matriz[x+m][y+n].vida = -1;
                    }
        }


        switch(move)
        {
            case PARADO:
            break;

            case NORTE:
                *formiga_mover = matriz[x][y-1];
                for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
                {
                    for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                        if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
                            matriz[x+m][y-1+n].type = F;
                }
            break;

            case LESTE:	
                *formiga_mover = matriz[x+1][y];
                for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
                {
                    for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                        if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
                            matriz[x+1+m][y+n].type = F;
                }	
            break;

            case SUL:
                *formiga_mover = matriz[x][y+1];
                for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
                {
                    for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                        if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
                            matriz[x+m][y+1+n].type = F;
                }
                break;
            break;

            case OESTE:
                *formiga_mover = matriz[x-1][y];
                for(m=-ANT_RATIO; m<=ANT_RATIO; m++)
                {
                    for(n=-ANT_RATIO; n<=ANT_RATIO; n++)
                        if( pow(m,2) + pow(n,2) <= pow(ANT_RATIO,2) ) 
                            matriz[x-1+m][y+n].type = F;
                }
            break;
        }

        formiga_mover->type = F;
        formiga_mover->vida = vida;

        //cout<<"Vida na move: " << formiga_mover->vida <<endl;

        //cout<<" Novas coordenadas: "<< formiga_mover->x << "   "<< formiga_mover->y <<endl;
    }*/

}