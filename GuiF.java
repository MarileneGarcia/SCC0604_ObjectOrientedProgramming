import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GuiF
{
    public static void main (String args[]){
        GuiF teste = new GuiF();
        //teste.editarf1(img1);
        //teste.editarf2(img2);
        //teste.editarf3(img3);
       // teste.editarf4(img4);
    }
            
    //Campos
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


    //Construtor
    public GuiF(){
        this.criarQuadro();
    }
    
    ////////////// QUADRO /////////////////////////
    //Criar quadro
    private void criarQuadro(){
        quadro = new JFrame( "Formigueiros" );
                
        JFrame.setDefaultLookAndFeelDecorated(true);
        boton1 = new JButton( "Iniciar" );
        boton2 = new JButton( "Pausar" );
        boton3 = new JButton( "Parar" );
        boton1.addActionListener(new ReconhecedorActionListener1());
        boton2.addActionListener(new ReconhecedorActionListener2());
        boton3.addActionListener(new ReconhecedorActionListener3());
  

        container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
        container.setSize(1200,700);
        container1 = new JPanel();
        container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
        container1.setSize(1200,350);

        container2 = new JPanel();
        container2.setLayout(new BoxLayout(container2, BoxLayout.Y_AXIS));
        container2.setSize(1200,350);


        controle = new JPanel();

        formigueiro1 = new JPanel();
        formigueiro2 = new JPanel();
        formigueiro3 = new JPanel();
        formigueiro4 = new JPanel();

        int width = 490;  //545
        int height = 290; //300 
        img1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        img4 = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        setInic();

        controle.setMaximumSize(new Dimension(100,650));
        formigueiro1.setMaximumSize(new Dimension(557,325));
        formigueiro2.setMaximumSize(new Dimension(557,325));
        formigueiro3.setMaximumSize(new Dimension(557,325));
        formigueiro4.setMaximumSize(new Dimension(557,325));

        controle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formigueiro1.setAlignmentX(Component.TOP_ALIGNMENT);
        formigueiro2.setAlignmentX(Component.TOP_ALIGNMENT);
        formigueiro3.setAlignmentX(Component.TOP_ALIGNMENT);
        formigueiro4.setAlignmentX(Component.TOP_ALIGNMENT);
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

        controle.setLayout( new FlowLayout());
        controle.add(boton1);
        //controle.add(boton2);
        controle.add(boton3);

        container.add(controle);

        container1.add(formigueiro1);
        container1.add(formigueiro3);
        container2.add(formigueiro2);
        container2.add(formigueiro4);
        container.add(container1);
        container.add(container2);

        quadro.add(container);
        quadro.setSize(1200,700);
        quadro.setVisible(true);
        
    }



    class ReconhecedorActionListener1 implements ActionListener {
        public void actionPerformed(ActionEvent event){
          /*  String massa = campoMassa.getText();
            String altura = campoAlt.getText();
            float fator = Float.valueOf(massa);
            float fator1 = Float.valueOf(altura);
            if(fator1 <= 0){
                JOptionPane optionPane = new JOptionPane("Error: Altura Inválida", JOptionPane.ERROR_MESSAGE);    
                JDialog dialog = optionPane.createDialog("Failure");
                dialog.setAlwaysOnTop(true);
                dialog.setVisible(true);
            }else{
                float resultado = fator/(fator1*fator1);
                rotuloResult.setText(String.valueOf(resultado));
                if(resultado < 18.5) rotuloClass.setText("Abaixo do Peso");
                else if(resultado <24.9) rotuloClass.setText("Peso Normal");
                else if(resultado < 29.9) rotuloClass.setText("Sobrepeso");
                else if(resultado < 34.9) rotuloClass.setText("Obesidade grau I");
                else if(resultado < 39.9) rotuloClass.setText("Obesidade grau II");
                else rotuloClass.setText("Obesidade grau III");
            }*/
        }
    }

    class ReconhecedorActionListener2 implements ActionListener {
            public void actionPerformed(ActionEvent event){
            /*rotuloResult.setText(" ");
            rotuloClass.setText(" ");
            campoMassa.setText(" ");
            campoAlt.setText(" ");*/
        }
    } 

    class ReconhecedorActionListener3 implements ActionListener {
        public void actionPerformed(ActionEvent event){
        /*rotuloResult.setText(" ");
        rotuloClass.setText(" ");
        campoMassa.setText(" ");
        campoAlt.setText(" ");*/
        }
    } 

    public void editarf1(BufferedImage img){
        label1 = new JLabel(new ImageIcon(img));
        formigueiro1.add(label1);
        container1.add(formigueiro1);

       /* container.add(controle);

        container1.add(formigueiro1);
        container1.add(formigueiro3);
        container2.add(formigueiro2);
        container2.add(formigueiro4);
        container.add(container1);
        container.add(container2);

        quadro.add(container);*/
        quadro.setVisible(true);
    }
    public void editarf2(BufferedImage img){
        label2 = new JLabel(new ImageIcon(img));
        formigueiro2.add(label2);
        container2.add(formigueiro2);
        /*container.add(controle);

        container1.add(formigueiro1);
        container1.add(formigueiro3);
        container2.add(formigueiro2);
        container2.add(formigueiro4);
        container.add(container1);
        container.add(container2);

        quadro.add(container);*/
        quadro.setVisible(true);
    }
    public void editarf3(BufferedImage img){
        label3 = new JLabel(new ImageIcon(img));
        formigueiro3.add(label3);
        container1.add(formigueiro3);
        quadro.add(container);
        quadro.setVisible(true);
    }
    public void editarf4 (BufferedImage img){
        label4 = new JLabel(new ImageIcon(img));
        formigueiro4.add(label4);
        container2.add(formigueiro4);
        quadro.add(container);
        quadro.setVisible(true);
    }

    public void setInic(){
        int width = 490;  //545
        int height = 290; //300

        for(int y= 0; y < height; y++){
            for(int x = 0; x < width/3; x++){
                int a = 250;
                int r = 0;
                int g = 250;
                int b = 0;

                int p = (a<<24) | (r<<16) | (g<<8) | b;

                img1.setRGB(x, y, p);
                img2.setRGB(x, y, p);
                img3.setRGB(x, y, p);
                img4.setRGB(x, y, p);
            }
        }


        for(int y= 0; y < height; y++){
            for(int x = width/3; x < 2*width/3; x++){
                int a = 250;
                int r = 0;
                int g = 0;
                int b = 250;

                int p = (a<<24) | (r<<16) | (g<<8) | b;

                img1.setRGB(x, y, p);
                img2.setRGB(x, y, p);
                img3.setRGB(x, y, p);
                img4.setRGB(x, y, p);
            }
        }
        
        for(int y= 0; y < height; y++){
            for(int x = 2*width/3; x < width; x++){
                int a = 250;
                int r = 0;
                int g = 250;
                int b = 0;

                int p = (a<<24) | (r<<16) | (g<<8) | b;

                img1.setRGB(x, y, p);
                img2.setRGB(x, y, p);
                img3.setRGB(x, y, p);
                img4.setRGB(x, y, p);
            } 
        }
    }


}