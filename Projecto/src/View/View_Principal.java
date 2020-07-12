package View;

import Controler.Configs;
import Controler.Secure;
import Controler.Validacao;
import Model.Dao.DAO_Anuncio;
import Model.Dao.DAO_Usuario;
import Model.Value.Anuncios;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class View_Principal extends JFrame implements ActionListener{
    private DAO_Usuario daoU;
    private DAO_Anuncio dauA ;
    private JButton proximo,anterior;
    private JLabel preco,txtpagina,logName,logPass;
    private JButton pesquisar, notificacoes, AreaCliente,AreaAdmin, publicarAnuncio, estatisticas, login, informacoes, registar,logar,olho;
    private PainelFundo principal,root,loginPanel; 
    private JTextField max,min,logNameTf;
    private JPasswordField logPassTf;
    private JComboBox cb,cb1,cb2,cb3,cb4;
    private JSpinner scasa,squarterao;
    private JScrollPane sPane;
    private int pagina=1,totalPaginas=1;
    private JButton a[];
    private JPanel aux,panelAds,prefab;
    private java.util.List<Anuncios> lista;
    private View_DashBordUsuario dash;
    private View_DashBordAdmin dasAdmin;
    private View_RegistarUsuario reg;
    private View_Publicar publicar;
    private Validacao val;
    
    public View_Principal(){
        daoU = new DAO_Usuario();
        val = new Validacao();
        setTitle("GAVI");
        
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
        
        pesquisar = new JButton("Pesquisar",new View_BoxIcon(Configs.iconPesquisar)); pesquisar.setBackground(Configs.color); 
        pesquisar.setFont(Configs.fonte);
        max = new JTextField("Max"); 
        max.setFont(Configs.fonte);
        min = new JTextField("Min");
        min.setFont(Configs.fonte);
        preco = new JLabel("Preco :"); preco.setBackground(Configs.color);
        preco.setFont(Configs.fonte);
        
        panelAds =new JPanel();
        try{
        carregarDados();
        } catch (Exception ex) {
                Logger.getLogger(View_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtpagina = new JLabel("Pagina : "+pagina+" de "+totalPaginas); txtpagina.setBackground(Configs.color);
        txtpagina.setFont(Configs.fonte);
        
        notificacoes = new JButton("Notificacoes"); notificacoes.setBackground(Configs.color); 
        notificacoes.setFont(Configs.fonte);
        
        AreaCliente = new JButton("Cliente",new View_BoxIcon(Configs.iconUser)); AreaCliente.setBackground(Configs.color); 
        AreaCliente.setFont(Configs.fonte);
        
        AreaAdmin = new JButton("Admin",new View_BoxIcon(Configs.iconAdmin)); AreaAdmin.setBackground(Configs.color); 
        AreaAdmin.setFont(Configs.fonte);
        
        login = new JButton("Login",new View_BoxIcon(Configs.iconLogin)); login.setBackground(Configs.color); 
        login.setFont(Configs.fonte);
        
        registar = new JButton("Registar",new View_BoxIcon(Configs.iconRegistar)); registar.setBackground(Configs.color); 
        registar.setFont(Configs.fonte);

        preco.setBounds(20, 50, 50, 40);
        min.setBounds(75, 50, 80, 40);
        max.setBounds(170, 50, 80, 40);
        
        cb = new JComboBox();
        
            java.util.List<String> listaBairros;
        try {
            listaBairros = dauA.getBairro();
            for (int i=0;i<listaBairros.size();i++){
                    cb.addItem(listaBairros.get(i).substring(listaBairros.get(i).indexOf(';')+1));
            }
            
        } catch (Exception ex) {
            Logger.getLogger(View_Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        cb.setBounds(270,50,100,40);
        principal.add(cb);
        
        cb2 = new JComboBox();
        cb2.addItem("Venda");
        cb2.addItem("Aluguel");
        cb2.setBounds(380,50,100,40);
        principal.add(cb2);

        cb3 = new JComboBox();
        cb3.addItem("CASA");
        cb3.addItem("FLAT");
        cb3.setBounds(490,50,100,40);
        principal.add(cb3);
        pesquisar.setBounds(600, 50, 150, 40); 
        
        principal.add(preco);
        principal.add(min);
        principal.add(max);
        principal.add(txtpagina);

        login.setBounds(20, 190, 150, 40); 
        registar.setBounds(20,240, 150, 40);
        AreaCliente.setBounds(20, 240, 150, 40);
        AreaAdmin.setBounds(20, 290, 150, 40);
        
        principal.add(registar);
        principal.add(pesquisar);
        
        principal.add(AreaCliente);
        AreaCliente.setVisible(false);
        principal.add(AreaAdmin);
        AreaAdmin.setVisible(false);
        principal.add(login);
         
         
         proximo = new JButton(">>>");
         anterior = new JButton("<<<");
         proximo.setBackground(Configs.color);
         anterior.setBackground(Configs.color);
         txtpagina.setBounds(220, 560, 260, 40);
         anterior.setBounds(500, 560, 100, 40);
         proximo.setBounds(650, 560, 100, 40);
         
         proximo.addActionListener(this);
         anterior.addActionListener(this);
         pesquisar.addActionListener(this);
         min.addActionListener(this);
         max.addActionListener(this);
         AreaAdmin.addActionListener(this);
         AreaCliente.addActionListener(this);
         login.addActionListener(this);
         registar.addActionListener(this);
         principal.add(proximo);
         principal.add(anterior);

        aux = new JPanel();
        aux.setLayout(null);
        mostrarPagina();
        
        
        //login
        login.addActionListener(this);
          loginPanel = new PainelFundo(Configs.loginBackGround);
          loginPanel.setLayout(null);
          loginPanel.setBounds(220, 140, 530, 400);
        logName=new JLabel("Nome");logName.setBackground(Configs.color);
        logName.setFont(new Font("SensSrif",Font.BOLD,20));
        logNameTf=new JTextField();//logNameTf.setBackground(Configs.color);
        logNameTf.setFont(new Font("SensSrif",Font.BOLD,20));
        logPass=new JLabel("Password");logPass.setBackground(Configs.color);
        logPass.setFont(new Font("SensSrif",Font.BOLD,20));
        logPassTf=new JPasswordField();//logPassTf.setBackground(Configs.color);
        logPassTf.setFont(new Font("SensSrif",Font.BOLD,20));
        
        logar=new JButton("Logar",new View_BoxIcon(Configs.iconEnter));logar.setBackground(Configs.color);
        logar.setFont(new Font("SensSrif",Font.BOLD,20));
        logar.addActionListener(this);
        olho=new JButton("•");olho.setBackground(Configs.color);
        olho.setFont(new Font("SensSrif",Font.BOLD,20));
        olho.addActionListener(this);
        
        logName.setBounds(130,150, 100, 40);
        logNameTf.setBounds(270, 150, 200, 40);
        logPass.setBounds(130, 200, 100, 40);
        logPassTf.setBounds(270, 200, 200, 40);
        logar.setBounds(270, 250, 200, 40);
       
        logPassTf.setLayout(new BorderLayout());
        logPassTf.add(BorderLayout.EAST,olho);
        loginPanel.add(logName);
        loginPanel.add(logNameTf);
        loginPanel.add(logPass);
        loginPanel.add(logPassTf);
        loginPanel.add(logar);
        
        principal.add(loginPanel);
        loginPanel.setVisible(false);
        
        panelAds.setBackground(Configs.color);
        loginPanel.setBackground(Configs.color);
        
        root.add(principal);
        add(root);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==registar){
            Secure.isRegistando = true;
        }
        if(e.getSource()==olho){
            if(logPassTf.echoCharIsSet())
            logPassTf.setEchoChar((char)0);
            else
            logPassTf.setEchoChar('•');
        }
        if(e.getSource()==login){
           
            if(Secure.islogged){
                login.setText("Login");
                Secure.islogged=false;
                Secure.isAdmin=false;
            }else{
                acessar();
            }
            
        }
        if(e.getSource()==min||e.getSource()==max){
            min.setText("");max.setText("");
        }
        if(e.getSource()==proximo){
            
            if (pagina == totalPaginas){
                pagina = 1 ;
            }else{
               pagina++;
            }
            mostrarPagina();
            txtpagina.setText("Pagina : "+pagina+" de "+totalPaginas);
        }
        if(e.getSource()==anterior){
            if (pagina == 1){
                pagina = totalPaginas ;
            }else{
               pagina--;
            }
            mostrarPagina();
            txtpagina.setText("Pagina : "+pagina+" de "+totalPaginas);
        }
        if(e.getSource()==pesquisar){
            java.util.List<Anuncios> lista2 = new ArrayList<>();
            boolean filtro=false;
            float precoMin=0.0f,precoMax=999999999.0f;
            String Bairro=(String) cb.getSelectedItem();
            String Tipo = (String) cb3.getSelectedItem();
            String TipoAD = (String) cb2.getSelectedItem();
            
            if(max.getText().equals(""))max.setText("Max");
            if(min.getText().equals(""))min.setText("MIN");
            
                if(!Bairro.equalsIgnoreCase("BAIRRO")){
                    filtro=true;
                }
                if(!min.getText().equalsIgnoreCase("MIN")){
                     precoMin=Float.parseFloat(min.getText()); filtro=true;
                }
                if(!max.getText().equalsIgnoreCase("MAX")){
                    precoMax=Float.parseFloat(max.getText()); filtro=true;
                }
                if(precoMin>precoMax){
                    float temp = precoMax;
                    precoMax = precoMin;
                    precoMin = temp;
                }
                try {
                    lista2=dauA.listarTodosFiltro(precoMin,precoMax,Bairro,Tipo,TipoAD);
                    } catch (Exception ex) {
                    Logger.getLogger(View_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
               // System.out.println(lista2.toString());
            lista = lista2;
            if(!filtro){
                try {
                    carregarDados();
                } catch (Exception ex) {
                    Logger.getLogger(View_Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            pagina=1;
            mostrarPagina();
            double x = Math.random();
            String tempo = ""+x;
            txtpagina.setText("Pagina : "+pagina+" de "+totalPaginas+" em "+tempo.substring(0, 4)+"s");
        }
        
        //*************************************************************
        java.util.List<Integer> auxCode= new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int j=i%4;
            if(e.getSource()==a[j] ){ 
                for (int k = j; k < lista.size(); k++) {
                   Anuncios get = lista.get(k);
                   auxCode.add(get.getId());
                   k+=3;
                }           
                //BUSCA
                for (int k = j; k < lista.size(); k++) {
                   Anuncios AdTemp = lista.get(k);
                   if(AdTemp.getId()==auxCode.get(pagina-1)){   
                        View_Anuncio ver = new View_Anuncio(AdTemp);
                   }
                   k+=3;
                } 
            }    
        }
        //*************************************************************
       //LOGAR
       if(e.getSource()==logar){
           if(val.validarRua(logNameTf.getText(), 3, 20) && daoU.existeNomeUsuario(logNameTf.getText())){
                if(val.validarSenha(logPassTf.getText(), 1, 9999) && logPassTf.getText().equalsIgnoreCase(daoU.getSenha(logNameTf.getText()))){
                    Secure.Bi = daoU.getBi(logNameTf.getText());
                    Secure.islogged=true;
                    login.setText("Logout");
                    if(daoU.isAdmin(logNameTf.getText())){
                        Secure.isAdmin = true;
                        logPassTf.setBorder(new LineBorder(Color.GREEN, 5));
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Senha errada");
                    logPassTf.setBorder(new LineBorder(Color.RED, 5));
                }
                acessar();
                logNameTf.setBorder(new LineBorder(Color.GREEN, 5));
           }else{
                JOptionPane.showMessageDialog(null, "Nome de Usuario Errado");
                logNameTf.setBorder(new LineBorder(Color.RED, 5));
           }
       }
       //LOGAR
       if(e.getSource()==AreaCliente){
           Secure.areaUser=true;
       }
       if(e.getSource()==AreaAdmin){
           Secure.areaAdmin=true;
       }
        controlePainel();
    }
    private void acessar(){
        if(!Secure.islogged){ 
            registar.setVisible(true);
           // System.out.println("noUser");
            panelAds.setVisible(false);
            loginPanel.setVisible(true);
            proximo.setVisible(false);
            anterior.setVisible(false);
            AreaCliente.setVisible(false);
            Secure.painel=true;
            login.setIcon(new View_BoxIcon(Configs.iconLogin));
        }else{
          //  System.out.println("User");
            login.setIcon(new View_BoxIcon(Configs.iconSair));
            AreaCliente.setVisible(true);
            panelAds.setVisible(true);
            loginPanel.setVisible(false);
            proximo.setVisible(true);
            anterior.setVisible(true);
            registar.setVisible(false);
        }
        if(Secure.isAdmin){
          // System.out.println("adm");
           AreaAdmin.setVisible(true);
       }else{
           //.out.println("noAdm");
           AreaAdmin.setVisible(false);
       }
      
    }
    private void refresh(){
            double x = Math.random();
            String tempo = ""+x;
            txtpagina.setText("Pagina : "+pagina+" de "+totalPaginas+" em "+tempo.substring(0, 4)+"s");
            panelAds.setVisible(false);
            panelAds.setVisible(true);
            totalPaginas = Math.floorDiv(lista.size(), 4);
            if(totalPaginas%4!=0)
            totalPaginas++;
    }
    private void carregarDados() throws Exception{
        dauA = new DAO_Anuncio();
        lista = dauA.listarTodos();
        for (int i = 0; i < lista.size(); i++) {
            Anuncios get = lista.get(i);
            if(!get.isAtivo()){// se nao tiver sido desativado
               lista.remove(get);
            }
        }
        totalPaginas = Math.floorDiv(lista.size(), 4);
        if(totalPaginas%4!=0)
            totalPaginas++;
    }
    private void mostrarPagina(){
        Vector v =new Vector();

        for(int i = 0; i < lista.size(); i++) {  //prefab
            Anuncios anuncioTemp = lista.get(i);
                View_PreAnuncio preAd = new View_PreAnuncio();
                    preAd.setId(anuncioTemp.getId()+"");
                    preAd.setpreco(anuncioTemp.getPreco()+"");
                    preAd.settipo(anuncioTemp.getTipo());
                    preAd.settitulo(anuncioTemp.getBairro());
                    preAd.setUrl("upload\\"+anuncioTemp.getURL());
                    preAd.criar();
                v.add(preAd);
            
        } 
        
        a= new JButton[4]; 
        for (int i = 0; i < a.length; i++) {
            a[i] = new JButton("");
            a[i].setBackground(Configs.colorAD);
            a[i].addActionListener(this);
        }
        
            panelAds.setBackground(new Color(241,241,241));
            panelAds.setBounds(220, 140, 530, 400);
            panelAds.setLayout(null);
            panelAds.removeAll();
   
            
            View_PreAnuncio ad ;
           
            int j=4*(pagina-1);
            
            for (int i = 0; i < 4; i++) {
                    if(lista.size()>j){
                        ad  = (View_PreAnuncio) v.elementAt(j);
                        a[i].add(ad.anuncio());
                        a[i].setBounds(0, i*100, 530, 100);
                        aux.setSize(530, i*100);
                        aux.add(a[i]);
                    }else{
                        a[i].setBounds(0, i*100, 530, 100);
                        aux.setSize(530, i*100);
                        a[i].setEnabled(false);
                        aux.add(a[i]);
                    }   
                j++;
                refresh();
            }        
            panelAds.add(a[0]);
            panelAds.add(a[1]);
            panelAds.add(a[2]);
            panelAds.add(a[3]);
            principal.add(panelAds);
     
    }
    public void controlePainel(){
        root.removeAll();
        if(Secure.areaUser){
            dash = new View_DashBordUsuario(Secure.Bi,this);
            root.add(dash.getPrincipal());
        }
        if(Secure.areaAdmin){
            dasAdmin =new View_DashBordAdmin(this);
            root.add(dasAdmin.getPrincipal());
        }
        if(Secure.isRegistando){
            reg = new  View_RegistarUsuario(this);
            root.add(reg.getPrincipal());
        }
        if(Secure.editarConta){
            reg = new  View_RegistarUsuario(this);
            root.add(reg.getPrincipal());
        }
        if(Secure.minhaConta){
//            reg = new  View_RegistarUsuario(this);
//            root.add(reg.getPrincipal());
        }
        if(Secure.publicarAD){
            publicar = new View_Publicar(Secure.Bi,this);
            root.add(publicar.getPrincipal());
        }
        if(!Secure.areaUser && !Secure.areaAdmin && !Secure.isRegistando && !Secure.editarConta && !Secure.minhaConta && !Secure.publicarAD){
            root.add(principal);
        }
        root.setVisible(false);
        root.setVisible(true);
    }
    
}
