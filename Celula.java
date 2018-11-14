

public class Celula{
    // Constantes para o tipo
    private static final int VAZIO = 0; // branco
    private static final int FORMIGA = 1; // vermelho
    private static final int AGUA = 2; // azul
    private static final int PAREDE = 3; // preto

    // Variáveis que definem a classe celula
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

    public void setTipo(int tipo){
        this.tipo = tipo;
    }

    public class Formiga extends Celula{
        // Constantes de informação da formiga
        private static final int RAIO_VISAO = 4;
        private static final int MAX_VIDA = 5;

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