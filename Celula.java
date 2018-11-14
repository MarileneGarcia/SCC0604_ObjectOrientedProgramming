

public class Celula{
    // Constantes comuns a todas as formigas
    private static final int VAZIO = 0;
    private static final int FORMIGA = 1;
    private static final int AGUA = 2;
    private static final int PAREDE = 3;

    private static final int RAIO_FORMIGA = 1;
    private static final int RAIO_VISAO = 4;
    private static final int MAX_VIDA = 5;

    // Variáveis que definem a classe formiga
    private int tipo;
    private int x;
    private int y;
    
    //Construtor
    public Celula(int tipo, int x, int y){
        this.tipo = tipo;
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public int getTipo(){
        return this.tipo;
    }

    public class Formiga extends Celula{
        private int vida;
        private int visao_norte;
        private int visao_leste;
        private int visao_sul;
        private int visao_oeste;

        public Formiga(int x, int y){
            this.tipo = FORMIGA;
            this.x = x;
            this.y = y;
            this.vida = max_vida;
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
    }
}