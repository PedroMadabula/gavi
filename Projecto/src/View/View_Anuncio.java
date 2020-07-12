package View;

import Controler.Configs;
import Model.Value.Anuncios;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import Model.Dao.DAO_Anuncio;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import javax.swing.border.LineBorder;

public class View_Anuncio extends JFrame implements ActionListener{
    
    
    private JButton comprar,voltar;
    private JLabel txt_Id,txt_titulo,txt_descricao,txt_bairro,txt_preco,txt_tipo;
    private JRadioButton rb1,rb2,rb3,rb4;
    private JLabel Id,titulo,descricao,bairro,preco,tipo;
    private String imgPath[]=new String[4];
    private PainelFundo principal,root;
    private PainelFundo img[]=new PainelFundo[4];
    private DAO_Anuncio daoA;
    
    public View_Anuncio(Anuncios AdTemp){
                setTitle("VER ANUNCIO");
                
                setLocation(Configs.posFX,Configs.posFY);
                setSize(Configs.posX,Configs.posY);  
                Dimension d = new Dimension(Configs.posX,Configs.posY);

                root = new PainelFundo(Configs.imgRoot);root.setLayout(new GridBagLayout());
                principal = new PainelFundo(Configs.imgPrincipal);
                principal.setLayout(null);
                principal.setPreferredSize(d);
                principal.setMaximumSize(d);
                principal.setMinimumSize(d);
                principal.setBorder(new WindowsBorders.DashedBorder(Color.WHITE, 5));
                this.getRootPane().setBorder(new LineBorder(Color.WHITE));
                
                daoA = new DAO_Anuncio();
                imgPath[0]=AdTemp.getURL();
                imgPath[1]=AdTemp.getURL1();
                imgPath[2]=AdTemp.getURL2();
                imgPath[3]=AdTemp.getURL3();
                
                for (int i = 0; i < img.length; i++) {
                    img[i]= new PainelFundo(("upload\\"+imgPath[i])); //uRL
                    img[i].setBounds(100,50,300,140);
                    principal.add(img[i]);
                    img[i].setVisible(false);
                }
                
                
                
                ButtonGroup g1 = new ButtonGroup();
                rb1 = new JRadioButton("img 1");
                rb2 = new JRadioButton("img 2");
                rb3 = new JRadioButton("img 3");
                rb4 = new JRadioButton("img 4");
                
                rb1.setSelected(true);
                rb1.setBounds(100, 200, 65, 20);
                rb2.setBounds(180, 200, 65, 20);
                rb3.setBounds(260, 200, 65, 20);
                rb4.setBounds(340, 200, 65, 20);
                g1.add(rb1);
                g1.add(rb2);
                g1.add(rb3);
                g1.add(rb4);
                principal.add(rb1);
                principal.add(rb2);
                principal.add(rb3);
                principal.add(rb4);
                setBackground(Color.WHITE);

                txt_Id=new JLabel("Id :");
                txt_titulo=new JLabel("Titulo :");
                txt_descricao=new JLabel("Descricao :");
                txt_bairro=new JLabel("Bairro :");
                txt_preco=new JLabel("Preco :");
                txt_tipo=new JLabel("Tipo :");
                Id=new JLabel("0000254");
                titulo=new JLabel("[URGENTE] - Imovel Para venda.");
                descricao=new JLabel("Casa com pequenos problema de goteiras!");
                bairro=new JLabel("Mahotas");
                preco=new JLabel("5.350.096 MZN");
                tipo=new JLabel("Venda");           
                
                txt_Id.setBounds(460,50,30,40);
                txt_preco.setBounds(460,100,100,40);
                txt_tipo.setBounds(460,150,100,40);
                txt_titulo.setBounds(100,250,100,40);
                txt_bairro.setBounds(100,300,100,40);
                txt_descricao.setBounds(100,350,130,40);
                
                Id.setBounds(500,50,100,40);
                preco.setBounds(540,100,180,40);
                tipo.setBounds(520,150,100,40);
                titulo.setBounds(200,250,520,40);
                bairro.setBounds(200,300,100,40);
                descricao.setBounds(220,350,500,40);
                
                
                
                comprar = new JButton("Comprar");
                voltar = new JButton("voltar");
                comprar.setBackground(Configs.color);
                voltar.setBackground(Configs.color);
                comprar.setBounds(590, 560, 100, 40);
                voltar.setBounds(700, 560, 100, 40);

                
                txt_Id.setFont(Configs.fonte);
                txt_bairro.setFont(Configs.fonte);
                txt_descricao.setFont(Configs.fonte);
                txt_preco.setFont(Configs.fonte);
                txt_tipo.setFont(Configs.fonte);
                txt_titulo.setFont(Configs.fonte);
                
                Id.setFont(Configs.fonte);
                bairro.setFont(Configs.fonte);
                descricao.setFont(Configs.fonte);
                preco.setFont(Configs.fonte);
                tipo.setFont(Configs.fonte);
                titulo.setFont(Configs.fonte);

                principal.add(txt_Id);
                principal.add(txt_bairro);
                principal.add(txt_descricao);
                principal.add(txt_preco);
                principal.add(txt_tipo);
                principal.add(txt_titulo);
                
                principal.add(Id);
                principal.add(bairro);
                principal.add(descricao);
                principal.add(preco);
                principal.add(tipo);
                principal.add(titulo);
                principal.add(comprar);
                principal.add(voltar);
                setBackground(Color.green);
                
                Id.setText(AdTemp.getId()+"");
                bairro.setText(AdTemp.getBairro());
                descricao.setText(AdTemp.getDescricao());
                preco.setText(AdTemp.getPreco()+"");
                tipo.setText(AdTemp.getTipoAnuncio());
                if(AdTemp.getTipoAnuncio().equalsIgnoreCase("ALUGUEL"))
                    comprar.setText("Alugar");
                titulo.setText(AdTemp.getTitulo());
                
                rb1.addActionListener(this);
                rb2.addActionListener(this);
                rb3.addActionListener(this);
                rb4.addActionListener(this);
                
                comprar.addActionListener(this);
                voltar.addActionListener(this);
                
                img[0].setVisible(true);
                
                
        root.add(principal);
        add(root);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        posicaoFrame();
        if(rb1.isSelected()){
            carregarImgs();
            img[0].setVisible(true);
        }
        if(rb2.isSelected()){
            carregarImgs();
            img[1].setVisible(true);
        }
        if(rb3.isSelected()){
            carregarImgs();
            img[2].setVisible(true);
        }
        if(rb4.isSelected()){
            carregarImgs();
            img[3].setVisible(true);
        }
        
        if(e.getSource()==voltar){
            super.setVisible(false);
        }
        if(e.getSource()==comprar){
            
            Anuncios aux = daoA.recuperar(Integer.parseInt(Id.getText()));
            View_Usuario vendedor = new View_Usuario(aux.getBI());
        }
     
    }
    
    private void carregarImgs(){
        for (int i = 0; i < img.length; i++) {
            img[i].setVisible(false);
        }
    }
    private void posicaoFrame() {
        Configs.posFX = this.getX();
        Configs.posFY = this.getY();
    }
}