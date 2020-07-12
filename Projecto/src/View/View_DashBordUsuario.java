
package View;

import Model.Table.PedidoTableModel;
import Controler.Configs;
import Controler.Secure;
import Model.Dao.DAO_Anuncio;
import Model.Value.Anuncios;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class View_DashBordUsuario extends JFrame implements ActionListener{
    private DAO_Anuncio dauA ;
    private JButton proximo,anterior,minhaConta,editarConta,pedidos,publicarAnuncio,voltar;
    private PainelFundo principal,root;
    private JScrollPane sPane;
    private int pagina=1,totalPaginas=1;
    private JButton a[];
    private JPanel aux,panelAds,prefab,loginPanel;
    private java.util.List<Anuncios> lista;
    private JLabel meuanucios,txtpagina;
    private String Bi;
    private JScrollPane pane;
    private JTable table;
    private View_Principal viewP;
    
    public View_DashBordUsuario(String a) {
        Secure.isOwner = true;
         this.Bi=a;
         dauA = new DAO_Anuncio();
         setTitle("DASH AREA DO CLIENTE");
         
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
        
         publicarAnuncio = new JButton("Publicar Anuncio",new View_BoxIcon(Configs.iconPublicar));
         voltar = new JButton("voltar",new View_BoxIcon(Configs.iconCancelar));
         publicarAnuncio.setBackground(Configs.color);
         voltar.setBackground(Configs.color);
         publicarAnuncio.setBounds(20, 240, 150, 40);
         voltar.setBounds(630, 560, 120, 40);
         
        principal.add(publicarAnuncio);
        principal.add(voltar);
        
        publicarAnuncio.addActionListener(this);
        voltar.addActionListener(this);
        panelAds =new JPanel();
        
        
        meuanucios = new JLabel("Os Meus Anuncios"); meuanucios.setBackground(Configs.color); 
        meuanucios.setFont(Configs.fonte);
        aux = new JPanel();
        aux.setLayout(null);
        
        
        meuanucios.setBounds(220, 100,200, 40);
        principal.add(meuanucios);
        
        proximo = new JButton(">>>");
        anterior = new JButton("<<<");
        proximo.setBackground(Configs.color);
        anterior.setBackground(Configs.color);
        
        minhaConta = new JButton("Minha Conta",new View_BoxIcon(Configs.iconUser));
        editarConta  = new JButton("Editar Conta",new View_BoxIcon(Configs.iconEditar));
        minhaConta.setBackground(Configs.color);
        editarConta.setBackground(Configs.color);
        minhaConta.setBounds(20, 140, 150, 40);
        editarConta.setBounds(20, 190, 150, 40);
        pedidos = new JButton("Pedidos");
        pedidos.setBackground(Configs.color);
        pedidos.setBounds(20, 290, 150, 40);
        pedidos.addActionListener(this);
        principal.add(pedidos);
        
        principal.add(minhaConta);
        principal.add(editarConta);
        
        anterior.setBounds(410, 560, 100, 40);
        proximo.setBounds(520, 560, 100, 40);
         
        principal.add(proximo);
        principal.add(anterior);
        proximo.addActionListener(this);
        anterior.addActionListener(this);
        minhaConta.addActionListener(this);
        editarConta.addActionListener(this);
        
       
        //********************************************
        pane = new JScrollPane();
        pane.setBounds(220, 140, 530, 400);
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setBackground(Configs.colorAD);
        //********************************************
        txtpagina = new JLabel("Pagina : "+pagina+" de "+totalPaginas); txtpagina.setBackground(Configs.color);
        txtpagina.setFont(Configs.fonte);
        principal.add(txtpagina);
        
        carregarDados();
        mostrarPagina();
        
        root.add(principal);
        add(root);
        //setVisible(true);
    }

    View_DashBordUsuario(String Bi, View_Principal aThis) {
        this(Bi);
        viewP = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        posicaoFrame();
        if(e.getSource()==publicarAnuncio){
            Secure.areaUser = false;
            Secure.publicarAD = true;
        }
        if(e.getSource()==voltar){
            Secure.areaUser = false;
        }
        if(e.getSource()==pedidos){
            listarPedidos();
        }
        if(e.getSource()==editarConta){
            Secure.areaUser = false;
            Secure.editarConta = true;
        }
        if(e.getSource()==minhaConta){
            View_Usuario u = new View_Usuario(Bi);
            u.getComprar().setVisible(false);
        }
        //*******************************************************
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
                             atualizar();
                   }
                   k+=3;
                } 
            }    
        }
        if(e.getSource()==proximo){
            
            if (pagina == totalPaginas){
                pagina = 1 ;
            }else{
               pagina++;
            }
            try{
                mostrarPagina();
            }catch(ArrayIndexOutOfBoundsException s){
                pagina=1;mostrarPagina();//System.err.println("dasd");
            }
            txtpagina.setText("Pagina : "+pagina+" de "+totalPaginas);
        }
        if(e.getSource()==anterior){
            if (pagina == 1){
                pagina = totalPaginas ;
            }else{
               pagina--;
            }
            try{
                mostrarPagina();
            }catch(ArrayIndexOutOfBoundsException s){
                pagina=1; mostrarPagina();//System.err.println("sdad");
            }
            txtpagina.setText("Pagina : "+pagina+" de "+totalPaginas);
        }
        //*********************************************************
        Secure.isOwner = false;
        viewP.controlePainel();
    }
    
    private void carregarDados(){
        lista = dauA.listarTodosDoUsuario(Bi);
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
                if(!anuncioTemp.isAtivo())
                    preAd.anuncio().setBackground(Configs.colorADdesativado);
            v.add(preAd);
        } 
        
        a= new JButton[4]; 
        for (int i = 0; i < a.length; i++) {
            a[i] = new JButton("");
            a[i].setBackground(Configs.colorAD);
            a[i].addActionListener(this);
        }
        
            panelAds.setBackground(Configs.colorAD);
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
            }        
            panelAds.add(a[0]);
            panelAds.add(a[1]);
            panelAds.add(a[2]);
            panelAds.add(a[3]);
            principal.add(panelAds);
        
    }
    private void listarPedidos(){
        panelAds.setVisible(false);
        table.setModel(new PedidoTableModel());
        PedidoTableModel ctm = (PedidoTableModel) table.getModel();
            ctm.setDados(dauA.listarTodosDoUsuario(Bi));
        pane.setViewportView(table);
        principal.add(pane);
        desativarNext();
    }
    private void desativarNext(){
        proximo.setVisible(false);
        anterior.setVisible(false);
    }
    private void atualizar() {
        panelAds.setVisible(false);
        carregarDados();mostrarPagina();
        panelAds.setVisible(true);
    }
    public PainelFundo getPrincipal() {
        return principal;
    }
    private void posicaoFrame() {
        Configs.posFX = this.getX();
        Configs.posFY = this.getY();
    }   
}
