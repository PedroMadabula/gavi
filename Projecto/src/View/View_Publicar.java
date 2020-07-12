package View;

import Controler.Configs;
import Controler.JFilePicker;
import Controler.Secure;
import Controler.Validacao;
import Model.Dao.DAO_Anuncio;
import Model.Value.Anuncios;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class View_Publicar extends JFrame implements ActionListener{
    private DAO_Anuncio dauA ;
    private JButton publicar,voltar;
    private JLabel txt_Id,txt_titulo,txt_descricao,txt_bairro,txt_preco,txt_tipo,txt_,img,txt_imagens,txt_tipoImovel,txt_Rua,txt_ncasa,txt_quarteirao;
    private JRadioButton tipo1,tipo2,tipo3,tipo4;
    private JTextField querytxt_Id,querytxt_titulo,querytxt_descricao,querytxt_bairro,querytxt_preco,querytxt_tipo,querytxt_Rua;
    private JFileChooser f;
    private JButton b1,b2,b3,b4;
    private JPanel imagens; 
    private JComboBox cb,cb1,cb2,cb3,cb4;
    private JSpinner scasa,squarterao;
    private PainelFundo principal,root;
    private PainelFundo imgP[]=new PainelFundo[4];
    private String url,url1,url2,url3;
    private String Bi;
    private int IdImovel=0;
    private  View_Principal viewP;
    private String erro;
    private Validacao val;
    
    public View_Publicar(String bi) {
        erro="";
        val = new Validacao();
        dauA = new DAO_Anuncio();
        url="default.jpg";
        url1="default.jpg";
        url2="default.jpg";
        url3="default.jpg";
        
        this.Bi=bi;
        setTitle("PUBLICAR ANUNCIO");
       
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
        
                imagens = new JPanel();
                imagens.setLayout(new BorderLayout());
                imagens.setBackground(Color.BLACK);
              
                ButtonGroup g1 = new ButtonGroup();
                ButtonGroup g2 = new ButtonGroup();
                ButtonGroup g3 = new ButtonGroup();
                b1 = new JButton("Escolher 1");
                b2 = new JButton("Escolher 2");
                b3 = new JButton("Escolher 3");
                b4 = new JButton("Escolher 4");
                
                tipo1 = new JRadioButton("Venda");
                tipo2 = new JRadioButton("Aluguel");
                tipo3 = new JRadioButton("Casa");
                tipo4= new JRadioButton("Flat");
                
//                b1.setBounds(420, 50, 100, 40);
//                b2.setBounds(420, 100, 100, 40);
//                b3.setBounds(420, 150, 100, 40);
//                b4.setBounds(420, 200, 100, 40);
//                
                b1.setBackground(Configs.color);
                b2.setBackground(Configs.color);
                b3.setBackground(Configs.color);
                b4.setBackground(Configs.color);
                g2.add(tipo1);
                g2.add(tipo2);
                g3.add(tipo3);
                g3.add(tipo4);
                g1.add(b1);
                g1.add(b2);
                g1.add(b3);
                g1.add(b4);
                principal.add(b1);
                principal.add(b2);
                principal.add(b3);
                principal.add(b4);
                principal.add(tipo1);
                principal.add(tipo2);
                principal.add(tipo3);
                principal.add(tipo4);
                
                scasa = new JSpinner(new SpinnerNumberModel(1, 1, 400, 1));
                squarterao = new JSpinner(new SpinnerNumberModel(1, 1, 400, 1));
               
                cb1 = new JComboBox();
                
                    java.util.List<String> listaBairros;
                try {
                    listaBairros = dauA.getBairro();
                    for (int i=1;i<listaBairros.size();i++){
                            cb1.addItem(listaBairros.get(i).substring(listaBairros.get(i).indexOf(';')+1));
                    }
                } catch (Exception ex) {
                    Logger.getLogger(View_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                cb1.setBounds(200,300,100,40);
                principal.add(cb1);
                
                txt_Rua=new JLabel("Rua :");
                txt_imagens=new JLabel("Escolher Imagens :");
                txt_Id=new JLabel("Id :");
                txt_titulo=new JLabel("Titulo :");
                txt_descricao=new JLabel("Descricao :");
                txt_bairro=new JLabel("Bairro :");
                txt_preco=new JLabel("Preco :");
                txt_tipo=new JLabel("Tipo :");
                txt_tipoImovel=new JLabel("Tipo de Imovel:");
                txt_ncasa=new JLabel("Nr. Casa:");
                txt_quarteirao=new JLabel("Nr. Quarteirao:");
                querytxt_Id=new JTextField("");
                querytxt_titulo=new JTextField("");
                querytxt_descricao=new JTextField("");
                querytxt_bairro=new JTextField("");
                querytxt_preco=new JTextField("");
                querytxt_tipo=new JTextField("");
                querytxt_Rua=new JTextField("");
                
               // imagens.setBounds(100,50,300,140);
                txt_imagens.setBounds(100, 50, 200, 40);
                txt_Id.setBounds(460,50,30,40);
                txt_preco.setBounds(460,100,100,40);
                txt_tipo.setBounds(460,150,100,40);
                txt_titulo.setBounds(100,250,100,40);
                txt_bairro.setBounds(100,300,100,40);
                txt_descricao.setBounds(100,400,130,40);
                txt_ncasa.setBounds(320,300,100,40);
                txt_quarteirao.setBounds(520,300,155,40);
                
                querytxt_Id.setBounds(500,50,100,40);
                querytxt_preco.setBounds(540,100,150,40);
                tipo1.setBounds(540,150,70,40);
                tipo2.setBounds(620,150,70,40);
                tipo3.setBounds(270,350,70,40);
                tipo4.setBounds(360,350,70,40);
                //tipo3.setBackground(new Color(0,51,255));
                //tipo4.setBackground(new Color(0,51,255));
                querytxt_titulo.setBounds(200,250,520,40);
                txt_tipoImovel.setBounds(100,350,150,40);
                querytxt_descricao.setBounds(220,400,500,40);
                
                scasa.setBounds(420,300,50,40);
                squarterao.setBounds(666,300,50,40);
                
                publicar = new JButton("Publicar");
                voltar = new JButton("voltar");
                publicar.setBackground(Configs.color);
                voltar.setBackground(Configs.color);
                publicar.setBounds(590, 560, 100, 40);
                voltar.setBounds(700, 560, 100, 40);
                
                
                txt_imagens.setFont(Configs.fonte);
                txt_Id.setFont(Configs.fonte);
                txt_bairro.setFont(Configs.fonte);
                txt_descricao.setFont(Configs.fonte);
                txt_preco.setFont(Configs.fonte);
                txt_tipo.setFont(Configs.fonte);
                txt_tipoImovel.setFont(Configs.fonte);
                txt_titulo.setFont(Configs.fonte);
                txt_ncasa.setFont(Configs.fonte);
                txt_quarteirao.setFont(Configs.fonte);
                
                querytxt_Id.setFont(Configs.fonte);
                querytxt_bairro.setFont(Configs.fonte);
                querytxt_descricao.setFont(Configs.fonte);
                querytxt_preco.setFont(Configs.fonte);
                querytxt_tipo.setFont(Configs.fonte);
                querytxt_titulo.setFont(Configs.fonte);
                
                querytxt_descricao.setText(parametrizar(querytxt_descricao.getText()));
                principal.add(imagens);
                principal.add(txt_ncasa);
                principal.add(scasa);
                principal.add(squarterao);
                principal.add(txt_quarteirao);
                principal.add(txt_imagens);
                principal.add(txt_bairro);
                principal.add(txt_descricao);
                principal.add(txt_preco);
                principal.add(txt_tipo);
                principal.add(txt_titulo);
                
                principal.add(txt_tipoImovel);
                principal.add(querytxt_bairro);
                principal.add(querytxt_descricao);
                principal.add(querytxt_preco);
                principal.add(querytxt_tipo);
                principal.add(querytxt_titulo);
                
                principal.add(imagens);
                principal.add(publicar);
                principal.add(voltar);
                setBackground(Color.green);
                
                for (int i = 0; i < imgP.length; i++) {
                    imgP[i]= new PainelFundo(("upload\\"+url)); //uRL
                    imgP[i].setBounds(100,50,300,140);
                    imgP[i].setBackground(Color.red);
                    principal.add(imgP[i]);
                    imgP[i].setVisible(false);
                }imgP[0].setVisible(true);
                
                
                publicar.addActionListener(this);   tipo1.setSelected(true); tipo3.setSelected(true);
                voltar.addActionListener(this);
                
                
                b1.addActionListener(this);
                b2.addActionListener(this);
                b3.addActionListener(this);
                b4.addActionListener(this);

               
                

                b1.setBounds(100, 200, 65, 20);
                b2.setBounds(180, 200, 65, 20);
                b3.setBounds(260, 200, 65, 20);
                b4.setBounds(340, 200, 65, 20);
               
              
                root.add(principal);
                add(root);
            //    setVisible(true);
            }

    View_Publicar(String Bi, View_Principal aThis) {
        this(Bi);
        viewP = aThis;
    }
    public void Update(int id){
        Anuncios aux = dauA.recuperar(id);
                querytxt_titulo.setText(aux.getTitulo());
                String Bairro=(String) cb1.getSelectedItem();
                cb1.setSelectedItem(aux.getBairro());
                querytxt_descricao.setText(aux.getDescricao());
                querytxt_preco.setText(aux.getPreco()+"");
                scasa.setValue(aux.getNrCasa());
                squarterao.setValue(aux.getQuarteirao());
                   
                if(aux.getTipo().equalsIgnoreCase("CASA"))
                   tipo3.setSelected(true);
                if(aux.getTipo().equalsIgnoreCase("FLAT"))
                   tipo4.setSelected(true);
                if(aux.getTipoAnuncio().equalsIgnoreCase("VENDA"))
                   tipo1.setSelected(true);
                if(aux.getTipoAnuncio().equalsIgnoreCase("ALUGUEL"))
                   tipo2.setSelected(true);

        url=aux.getURL(); b1.setText(url);
        url1=aux.getURL1(); b2.setText(url1);
        url2=aux.getURL2(); b3.setText(url2);
        url3=aux.getURL3(); b4.setText(url3);
            carregarImgs();
            imgP[0].setImagem("upload\\"+url);
            imgP[0].setVisible(true);
        IdImovel=id;
    }
    private String parametrizar(String text) {
        String x="";
        for (int i = 0; i < text.length(); i++) {
            if(i%200==0){ 
            }
        }
        x=text;
        return x;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==publicar){
            String Tipo ="",TipoA ="";
            Anuncios temp =new Anuncios();
            
            validar();
            if(erro.equals("")){
                temp.setId(IdImovel);
                temp.setTitulo(querytxt_titulo.getText());
                String Bairro=(String) cb1.getSelectedItem();
                
                    if(tipo3.isSelected())
                        Tipo="CASA";
                    if(tipo4.isSelected())
                        Tipo="FLAT";
                    if(tipo1.isSelected())
                        TipoA="VENDA";
                    if(tipo2.isSelected())
                        TipoA="ALUGUEL";
                temp.setBairro(Bairro);
                temp.setDescricao(querytxt_descricao.getText());
                float preco=Float.parseFloat(querytxt_preco.getText());
                temp.setPreco(preco);
                int ncasa = (int) scasa.getValue();
                temp.setNrCasa(ncasa);
                int nQua = (int) squarterao.getValue();
                temp.setQuarteirao(nQua);
                temp.setTipo(Tipo);
                temp.setTipoAnuncio(TipoA);
                
                
                temp.setURL(url);   //imagems default ou nao
                temp.setURL1(url1); //imagems default ou nao
                temp.setURL2(url2); //imagems default ou nao
                temp.setURL3(url3); //imagems default ou nao

                if(temp.getId()<=0){
                   dauA.inserir(temp,Bi); 
                }else{
                   dauA.alterar(temp);
                }
            }else{
                    JOptionPane.showMessageDialog(null, erro);
            }
                
                
        }
        if(e.getSource()==voltar){
            if(viewP == null){
                super.setVisible(false);
            }else{
                Secure.areaUser = true;
                Secure.publicarAD = false;
                viewP.controlePainel(); 
            }
        }
        if(e.getSource()==b1){
            try{
            Controler.Controle_EscolherFoto picF = new Controler.Controle_EscolherFoto();
            if(picF.getPath()!=null){
                url=picF.getPath();
                b1.setText(url);
                b1.setBackground(Color.GREEN);
            }
            }catch(NullPointerException erro){
                url="default.jpg";
            }
            carregarImgs();
            imgP[0].setImagem("upload\\"+url);
            imgP[0].setVisible(true);
        }
        if(e.getSource()==b2){
            try{
            Controler.Controle_EscolherFoto picF = new Controler.Controle_EscolherFoto();
            if(picF.getPath()!=null){
                url1=picF.getPath();
                b2.setText(url1);
                b2.setBackground(Color.GREEN);
            }
            }catch(NullPointerException erro){
                url1="default.jpg";
            }
            carregarImgs();
            imgP[1].setImagem("upload\\"+url1);
            imgP[1].setVisible(true);
        }
        if(e.getSource()==b3){
            try{
            Controler.Controle_EscolherFoto picF = new Controler.Controle_EscolherFoto();
            if(picF.getPath()!=null){
                url2=picF.getPath();
                b3.setText(url2);
                b3.setBackground(Color.GREEN);
            }
            }catch(NullPointerException erro){
                url2="default.jpg";
            }
            carregarImgs();
            imgP[2].setImagem("upload\\"+url2);
            imgP[2].setVisible(true);
        }
        if(e.getSource()==b4){
            try{
            Controler.Controle_EscolherFoto picF = new Controler.Controle_EscolherFoto();
            if(picF.getPath()!=null){
                url3=picF.getPath();
                b4.setText(url3);
                b4.setBackground(Color.GREEN);
            }
            }catch(NullPointerException erro){
                url3="default.jpg";
            }
            carregarImgs();
            imgP[3].setImagem("upload\\"+url3);
            imgP[3].setVisible(true);
        }
    }
  

    public PainelFundo getPrincipal() {
        return principal;
    }
    private void carregarImgs(){
        for (int i = 0; i < imgP.length; i++) {
            imgP[i].setVisible(false);
        }
    }
    
     private void validar() {
        erro ="";
        if(val.validarRua(querytxt_titulo.getText(),5,50)){
                   querytxt_titulo.setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                   querytxt_titulo.setBorder(new LineBorder(Color.RED, 5)); erro+="\n1 - Titulo deve  ter entre 5 e 50 letras";
                }
                if(val.validarRua(querytxt_descricao.getText(),5,50)){
                    querytxt_descricao.setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    querytxt_descricao.setBorder(new LineBorder(Color.RED, 5)); erro+="\n2 - Descricao deve  ter entre 5 e 20 letras";
                }
                if(val.validarPreco(querytxt_preco.getText(),1,9)){
                    querytxt_preco.setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    querytxt_preco.setBorder(new LineBorder(Color.RED, 5)); erro+="\n3 - Preco deve ter entre 1 e 9 digitos.";
                }
    }
}