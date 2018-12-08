import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;



public class RebootPopulation {
    private ArrayList<ArrayList<Integer>> tipos = new ArrayList<ArrayList<Integer>>();
    private ArrayList<Formigueiro> fumi = new ArrayList<Formigueiro>();

    public RebootPopulation() {
    }


    void rebootPopulation(ArrayList<ArrayList<Integer>> tipos, ArrayList<Formigueiro> formigueiro) {
        int k, i, j;
        int deltaI = 0, deltaJ = 0;
        int num_formigas, tamanho;
        int x, y;
        int m, n;
        Random r = new Random();

        for (k = 0; k < fumi.size(); k++) {
            num_formigas = formigueiro.get(k).getFormigas().size();
            for (tamanho = 0; tamanho < num_formigas; tamanho++) {
                x = formigueiro.get(k).getFormigas().get(tamanho).getX();
                y = formigueiro.get(k).getFormigas().get(tamanho).getY();
                formigueiro.get(k).getFormigas().remove(tamanho);
                if ((x >= 190 && x < 240) || (x >= 600 && x < 650) || (x>= 1010 && x< 1060)) {
                    tipos.get(x).set(y, Formigueiro.AGUA);
                } else {
                    tipos.get(x).set(y, Formigueiro.VAZIO);
                }
            }
        }



        for (k = 0; k < fumi.size(); k++) {
            for (i = 10 + deltaI; i < 190 + deltaI; i++) {
                for (j = 10 + deltaJ; j < 310 + deltaJ; j++) {
                    if ((r.nextInt(101) >= 80) &&
                            (tipos.get(i).get(j) != Formigueiro.FORMIGA) &&
                            (tipos.get(i).get(j) != Formigueiro.PAREDE)  ) {
                        fumi.get(i).getFormigas().get(j).setVida(Formiga.MAX_VIDA);
                        tipos.get(i).set(j, Formigueiro.FORMIGA);
                    }
                    formigueiro.get(k).getFormigas().add(new Formiga(i, j));
                }
            }
        }
        if (k == 2) {
            deltaJ += 310;
            deltaI = 0;
        } else deltaI += 410;
    }
}






