package View;

import Controler.Configs;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import javax.swing.*;

public class View_Inicial extends JFrame{
    
    private JProgressBar progress;
    private JLabel text,carregando,descrisao;
    private PainelFundo principal,imgLoad,root,jarvin,logo;
    
    public View_Inicial(){
        this.setUndecorated(true);
        //this.setAlwaysOnTop(true);
        //this.setResizable(false);
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int ) tk.getScreenSize().getWidth();
        int ysize = (int ) tk.getScreenSize().getHeight();
       
       // System.out.println("X:"+xsize);
       // System.out.println("Y:"+ysize);
        
        setSize(800,400);
        setTitle("Inicio");
        setLocation((int)(xsize/4.553),(int)(ysize/5.12));
        Dimension d = new Dimension(800,400);

        root = new PainelFundo(Configs.imgRoot);root.setLayout(new GridBagLayout());
        root.setBackground(Color.BLACK);
        principal = new PainelFundo("storage\\1.jpg");//Configs.imgPrincipal);
        principal.setLayout(null);
        principal.setPreferredSize(d);
        principal.setMaximumSize(d);
        principal.setMinimumSize(d);
        
        jarvin = new PainelFundo("storage\\2.gif");
        jarvin.setBounds(0, 210, 300, 250);
        //principal.add(jarvin);
        
        logo = new PainelFundo("storage\\logo.jpg");
        //logo.setLayout(null);
        logo.setBounds(0, 0, 800, 115);
        
        
        carregando = new JLabel("Carregando");
        carregando.setBackground(Color.WHITE);carregando.setFont(new Font("SensSrif",Font.BOLD,20));
        
        
        principal.add(carregando);
        principal.add(logo);
        
        
        progress = new JProgressBar(0, 100);
        progress.setBorderPainted(false);
        progress.setForeground(Color.WHITE);
        progress.setBackground(Color.BLACK);
        progress.setStringPainted(true);
        progress.setString("Conentando a Base de Dados");
        
        
        carregando.setBounds(380,320,100,30);
        progress.setBounds(50,350,700,20);
        
        

        principal.add(progress);
        
        root.add(principal);
        add(root);
        setVisible(true);
    }

    public JProgressBar getProgress() {
        return progress;
    }

    public void setProgress(JProgressBar progress) {
        this.progress = progress;
    }

    public JLabel getCarregando() {
        return carregando;
    }

    public void setCarregando(JLabel carregando) {
        this.carregando = carregando;
    }
    
}
