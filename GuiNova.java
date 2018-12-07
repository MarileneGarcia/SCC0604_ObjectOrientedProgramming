import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.Random;

public class GuiNova {

    public boolean iniciar = false, pausar = false;
    public static void main(String args[]) {
        GuiNova inter = new GuiNova();
        int matriz1[][] = new int[530][280];
        int matriz2[][] = new int[530][280];
        int matriz3[][] = new int[530][280];
        int matriz4[][] = new int[530][280];
        Random r = new Random();
        while(true){
            System.out.print("");
            if(inter.pausar == false){
                System.out.print("");
                if(inter.iniciar == true){
                    
                    for(int i = 0; i< 530; i++){
                        for(int j = 0; j < 280; j++){
                            matriz1[i][j] = r.nextInt(3);
                            matriz2[i][j] = r.nextInt(3);
                            matriz3[i][j] = r.nextInt(3);
                            matriz4[i][j] = r.nextInt(3);
                        }
                    }
                    inter.editAll(matriz1, matriz2, matriz3, matriz4);
                }   
            }
        }
    }

    

    // Campos
    private JFrame quadro;
    private JLabel rotuloGera;
    private JLabel rotuloMut;
    private JButton boton1;
    private JButton boton2;
    private JButton boton3;
    private JPanel formigueiro1;
    private JPanel container;
    private JPanel container1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JPanel formigueiro2;
    private JPanel formigueiro3;
    private JPanel formigueiro4;
    private JPanel controle;
    private JPanel container2;
    private BufferedImage img1;
    private BufferedImage img2;
    private BufferedImage img3;
    private BufferedImage img4;
    private JLabel gerac;
    private JLabel mut;

    // Construtor
    public GuiNova() {
        this.criarQuadro();
    }

    ////////////// QUADRO /////////////////////////
    // Criar quadro
    private void criarQuadro() {
        quadro = new JFrame("Formigueiros");

        gerac = new JLabel( "                                 Geração: " );
        mut = new JLabel( "                                   Mutação: " );
        JFrame.setDefaultLookAndFeelDecorated(true);
        boton1 = new JButton("Iniciar");
        boton2 = new JButton("Pausar");
        boton3 = new JButton("Parar");
        boton1.addActionListener(new ReconhecedorActionListenerIniciar());
        boton2.addActionListener(new ReconhecedorActionListenerPausar());
        boton3.addActionListener(new ReconhecedorActionListenerParar());

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setSize(1200, 700);
        container1 = new JPanel();
        container1.setLayout(new BoxLayout(container1, BoxLayout.X_AXIS));
        container1.setSize(600, 640);

        container2 = new JPanel();
        container2.setLayout(new BoxLayout(container2, BoxLayout.X_AXIS));
        container2.setSize(600, 640);

        controle = new JPanel();

        controle.setSize(1200, 60);

        formigueiro1 = new JPanel();
        formigueiro2 = new JPanel();
        formigueiro3 = new JPanel();
        formigueiro4 = new JPanel();

        int width = 530; // 545
        int height = 280; // 300
        img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img4 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);


        setInic();

        //controle.setMaximumSize(new Dimension(1200, 60));
        formigueiro1.setMaximumSize(new Dimension(557, 320));
        formigueiro2.setMaximumSize(new Dimension(557, 320));
        formigueiro3.setMaximumSize(new Dimension(557, 320));
        formigueiro4.setMaximumSize(new Dimension(557, 320));

        controle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formigueiro1.setAlignmentX(Component.CENTER_ALIGNMENT);
        formigueiro2.setAlignmentX(Component.CENTER_ALIGNMENT);
        formigueiro3.setAlignmentX(Component.CENTER_ALIGNMENT);
        formigueiro4.setAlignmentX(Component.CENTER_ALIGNMENT);
        controle.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Controle"));
        formigueiro1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formigueiro1"));
        formigueiro2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formigueiro2"));
        formigueiro3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formigueiro3"));
        formigueiro4.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Formigueiro4"));

        label1 = new JLabel(new ImageIcon(img1));
        label2 = new JLabel(new ImageIcon(img2));
        label3 = new JLabel(new ImageIcon(img3));
        label4 = new JLabel(new ImageIcon(img4));
        formigueiro1.add(label1);
        formigueiro2.add(label2);
        formigueiro3.add(label3);
        formigueiro4.add(label4);

        controle.setLayout(new FlowLayout());
        controle.add(boton1);
        controle.add(boton2);
        controle.add(boton3);
        controle.add(gerac);
        controle.add(mut);

        container.add(controle);

        container1.add(formigueiro1);
        container1.add(formigueiro2);
        container2.add(formigueiro3);
        container2.add(formigueiro4);
        container.add(container1);
        container.add(container2);

        quadro.add(container);
        quadro.setSize(1200, 800);
        quadro.setVisible(true);

    }

    class ReconhecedorActionListenerIniciar implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            pausar = false;
            iniciar = true;
        }
    }

    class ReconhecedorActionListenerPausar implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            pausar = true;
        }
    }



    class ReconhecedorActionListenerParar implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            pausar = true;
            quadro.setVisible(false);
        }
    }

    public void editarf1(int matriz[][]) {
        //label1 = new JLabel(new ImageIcon(img));
        int p, width = 530, height = 280;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch(matriz[x][y]){
                    case 0:
                        p = (250 << 24) | (255 << 16) | (255 << 8) | 255;
                        img1.setRGB(x, y, p);
                        break;
                    case 1:
                        p = (250 << 24) | (255 << 16) | (0 << 8) | 0;
                        img1.setRGB(x, y, p);
                        break;
                    case 2:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 255;
                        img1.setRGB(x, y, p);
                        break;
                    case 3:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                        img1.setRGB(x, y, p);
                        break;
                }
            }
        }
        //quadro.setVisible(true);
    }

    public void editAll(int matriz1[][], int matriz2[][], int matriz3[][], int matriz4[][]){
        editarf1(matriz1);
        editarf2(matriz2);
        editarf3(matriz3);
        editarf4(matriz4);
        /*Timer timer = new Timer(100, ActionListenerAtu);
        timer.start();*/
        SwingUtilities.updateComponentTreeUI(quadro); 
        quadro.setVisible(true);
    }

    public void editarf2(int matriz[][]) {
        //label1 = new JLabel(new ImageIcon(img));
        int p, width = 530, height = 280;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch(matriz[x][y]){
                    case 0:
                        p = (250 << 24) | (255 << 16) | (255 << 8) | 255;
                        img2.setRGB(x, y, p);
                        break;
                    case 1:
                        p = (250 << 24) | (255 << 16) | (0 << 8) | 0;
                        img2.setRGB(x, y, p);
                        break;
                    case 2:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 255;
                        img2.setRGB(x, y, p);
                        break;
                    case 3:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                        img2.setRGB(x, y, p);
                        break;
                }
            }
        }
        //quadro.setVisible(true);
    }
    public void editarf3(int matriz[][]) {
        //label1 = new JLabel(new ImageIcon(img));
        int p, width = 530, height = 280;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch(matriz[x][y]){
                    case 0:
                        p = (250 << 24) | (255 << 16) | (255 << 8) | 255;
                        img3.setRGB(x, y, p);
                        break;
                    case 1:
                        p = (250 << 24) | (255 << 16) | (0 << 8) | 0;
                        img3.setRGB(x, y, p);
                        break;
                    case 2:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 255;
                        img3.setRGB(x, y, p);
                        break;
                    case 3:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                        img3.setRGB(x, y, p);
                        break;
                }
            }
        }
       // quadro.setVisible(true);
    }
    public void editarf4(int matriz[][]) {
        //label1 = new JLabel(new ImageIcon(img));
        int p, width = 530, height = 280;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch(matriz[x][y]){
                    case 0:
                        p = (250 << 24) | (255 << 16) | (255 << 8) | 255;
                        img4.setRGB(x, y, p);
                        break;
                    case 1:
                        p = (250 << 24) | (255 << 16) | (0 << 8) | 0;
                        img4.setRGB(x, y, p);
                        break;
                    case 2:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 255;
                        img4.setRGB(x, y, p);
                        break;
                    case 3:
                        p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                        img4.setRGB(x, y, p);
                        break;
                }
            }
        }
  
        //quadro.setVisible(true);
    }

    public void setInic() {
        int width = 530; // 545
        int height = 280; // 300
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 3; x++) {
                if( y == 0 || x == 0 || x == width-1 || y == height-1){
                    int p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                    img1.setRGB(x, y, p);
                    img2.setRGB(x, y, p);
                    img3.setRGB(x, y, p);
                    img4.setRGB(x, y, p);
                }else{
                    int a = 250;
                    int r = 250;
                    int g = 250;
                    int b = 250;
                int p = (a << 24) | (r << 16) | (g << 8) | b;

                img1.setRGB(x, y, p);
                img2.setRGB(x, y, p);
                img3.setRGB(x, y, p);
                img4.setRGB(x, y, p);
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = width / 3; x < 2 * width / 3; x++) {
                if( y == 0 || x == 0 || x == width-1 || y == height-1){
                    int p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                    img1.setRGB(x, y, p);
                    img2.setRGB(x, y, p);
                    img3.setRGB(x, y, p);
                    img4.setRGB(x, y, p);
                }else{
                    int a = 250;
                    int r = 0;
                    int g = 0;
                    int b = 250;
                int p = (a << 24) | (r << 16) | (g << 8) | b;

                img1.setRGB(x, y, p);
                img2.setRGB(x, y, p);
                img3.setRGB(x, y, p);
                img4.setRGB(x, y, p);
                }
            }
        }

        for (int y = 0; y < height; y++) {
            for (int x = 2 * width / 3; x < width; x++) {
                if( y == 0 || x == 0 || x == width-1 || y == height-1){
                    int p = (250 << 24) | (0 << 16) | (0 << 8) | 0;
                    img1.setRGB(x, y, p);
                    img2.setRGB(x, y, p);
                    img3.setRGB(x, y, p);
                    img4.setRGB(x, y, p);
                }else{
                    int a = 250;
                    int r = 250;
                    int g = 250;
                    int b = 250;
                int p = (a << 24) | (r << 16) | (g << 8) | b;

                img1.setRGB(x, y, p);
                img2.setRGB(x, y, p);
                img3.setRGB(x, y, p);
                img4.setRGB(x, y, p);
             }
        }
    }

}

}