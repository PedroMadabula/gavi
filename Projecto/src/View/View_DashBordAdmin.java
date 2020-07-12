package View;

import Model.Table.AnunciosTableModel;
import Controler.Configs;
import Controler.EstatisticasGraficos;
import Controler.Relatorio_A4;
import Controler.Secure;
import Model.Table.UsuarioTableModel;
import Model.Dao.DAO_Anuncio;
import Model.Dao.DAO_Usuario;
import Model.Value.Anuncios;
import Model.Value.Usuario;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class View_DashBordAdmin extends JFrame implements ActionListener{
    private DAO_Anuncio dauA;
    private DAO_Usuario dauU;
    private JButton Estatisticas,AddicionarBairro,pesquisar,listarUsuarios,voltar,relatorio,apagar,editar,desativar,ver;
    private PainelFundo principal,root;
    private JScrollPane sPane;
    private java.util.List<Anuncios> lista;
    private JScrollPane pane;
    private JTable table;
    private View_Principal viewP;
    
    public View_DashBordAdmin(){
         dauA = new DAO_Anuncio();
         dauU = new DAO_Usuario();
        setTitle("DASH AREA DA ADMINISTRACAO");
        
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
         
         listarUsuarios = new JButton("- Usuarios",new View_BoxIcon(Configs.iconListar));
         voltar = new JButton("Voltar",new View_BoxIcon(Configs.iconCancelar));
         apagar = new JButton("Deletar",new View_BoxIcon(Configs.iconDeletar));
         editar = new JButton("Editar",new View_BoxIcon(Configs.iconEditar));
         desativar = new JButton("Des/Ativar",new View_BoxIcon(Configs.iconDesativar));
         ver = new JButton("Ver",new View_BoxIcon(Configs.iconVer));
                 
         listarUsuarios.setBackground(Configs.color);
         voltar.setBackground(Configs.color);
         apagar.setBackground(Configs.color);
         editar.setBackground(Configs.color);
         desativar.setBackground(Configs.color);
         ver.setBackground(Configs.color);
         
         voltar.setBounds(630, 560, 120, 40);
         
         
         ver.setBounds(20, 340, 150, 40);
         
//         editar.setBounds(220, 560, 130, 40);
//         desativar.setBounds(370, 560, 130, 40);
//         apagar.setBounds(520, 560, 100, 40);
//         
         editar.setBounds(20, 390, 150, 40);
         desativar.setBounds(20, 440, 150, 40);
         
         //apagar.setBounds(20, 490, 150, 40);
         apagar.setBounds(480, 560, 130, 40);
         
         principal.add(listarUsuarios);
         principal.add(voltar);
         principal.add(apagar);
         principal.add(editar);
         principal.add(desativar);
         principal.add(ver);
         
       
        
        pesquisar = new JButton("Pesquisar",new View_BoxIcon(Configs.iconPesquisar)); pesquisar.setBackground(Configs.color); 
        pesquisar.setFont(Configs.fonte);
        pesquisar.setBounds(600, 50, 150, 40); 
     
        Estatisticas = new JButton("Estatisticas",new View_BoxIcon(Configs.iconEstatistica));
        relatorio = new JButton("Relatorio",new View_BoxIcon(Configs.iconRelatorio));
        AddicionarBairro  = new JButton("Add Bairro ",new View_BoxIcon(Configs.iconAdd));
        relatorio.setBackground(Configs.color);
        Estatisticas.setBackground(Configs.color);
        AddicionarBairro.setBackground(Configs.color);
        
        
        
        Estatisticas.setBounds(20, 140, 150, 40);
        AddicionarBairro.setBounds(20, 190, 150, 40);
        listarUsuarios.setBounds(20, 240, 150, 40);
        relatorio.setBounds(20, 290, 150, 40);
        
        principal.add(relatorio);
        principal.add(Estatisticas);
        principal.add(AddicionarBairro);
        
        //********************************************
        pane = new JScrollPane();
        pane.setBounds(220, 140, 530, 400);
        table = new JTable();
        table.setAutoCreateRowSorter(true);
        table.setBackground(Configs.colorAD);
        //********************************************
        editar.addActionListener(this);
        desativar.addActionListener(this);
        apagar.addActionListener(this);
        ver.addActionListener(this);
        relatorio.addActionListener(this);
        pesquisar.addActionListener(this);
        Estatisticas.addActionListener(this);
        AddicionarBairro.addActionListener(this);
        listarUsuarios.addActionListener(this);
        voltar.addActionListener(this);
        
        listarAds();
        
        root.add(principal);
        add(root);
      //  setVisible(true);
         
    }

    View_DashBordAdmin(View_Principal aThis) {
        this();
        viewP = aThis;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        posicaoFrame();
        if(e.getSource()==relatorio){
            Relatorio_A4 relatorio = new Relatorio_A4();
        }
        if(e.getSource()==Estatisticas){
            EstatisticasGraficos egrafico = new EstatisticasGraficos();
            
        }
        if(e.getSource()==AddicionarBairro){
            String bairro = JOptionPane.showInputDialog("Digite o novo bairro");
            if(dauA.addBairro(bairro) && bairro!=null && bairro.length()>4){
                JOptionPane.showMessageDialog(null, "Sucesso");
            }else{
                JOptionPane.showMessageDialog(null, "Erro! Tente Novamente.");
            }
        }
        if(e.getSource()==listarUsuarios){
            if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                listarUser();
                listarUsuarios.setText("- Anuncios");
            }
            else{
                listarAds();
                listarUsuarios.setText("- Usuarios");
            }
            
        }
        if(e.getSource()==apagar){
            if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                if(temLinhaSelecionada()){
                    dauA.excluirAdid(table.getValueAt(table.getSelectedRow(), 0));
                    JOptionPane.showMessageDialog(null, "Sucesso");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro!Selecione uma linha  e Tente Novamente.");
                }
            }else{
                if(temLinhaSelecionada()){
                    Usuario aux =dauU.recuperar((String) table.getValueAt(table.getSelectedRow(), 0)+"");
                    dauU.excluir(aux);
                    JOptionPane.showMessageDialog(null, "Sucesso");
                }else{
                    JOptionPane.showMessageDialog(null, "Erro!Selecione uma linha  e Tente Novamente.");
                }
            }
        }
        if(e.getSource()==editar){
            if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                if(temLinhaSelecionada()){
                    if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                       View_Publicar p = new View_Publicar(Secure.Bi);
                       p.Update((int) table.getValueAt(table.getSelectedRow(), 0));
//                       Secure.publicarAD = true;
//                       Secure.areaAdmin = false;
//                       viewP.controlePainel();
                       p.setVisible(true);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Erro!Selecione uma linha e Tente Novamente.");

                }
            }
        }
        if(e.getSource()==desativar){
            if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                if(temLinhaSelecionada()){
                    if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                        Anuncios AdTemp = dauA.recuperar((int) table.getValueAt(table.getSelectedRow(), 0));
                        if(AdTemp.isAtivo())
                            dauA.desativar(AdTemp.getId());  //desativar
                            else
                            dauA.ativar(AdTemp.getId());  //ativar
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Erro!Selecione uma linha e Tente Novamente.");

                }
            }
        }
        if(e.getSource()==ver){
             if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                if(temLinhaSelecionada()){
                    if(listarUsuarios.getText().equalsIgnoreCase("- Usuarios")){
                      View_Anuncio adnum = new View_Anuncio(dauA.recuperar((int) table.getValueAt(table.getSelectedRow(), 0)));
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Erro!Selecione uma linha e Tente Novamente.");

                }
            }
                        
        }
        if(e.getSource()==voltar){
           // Secure.isOwner = false;
            Secure.areaAdmin = false;
            viewP.controlePainel();
        }
    }   
    private void listarAds(){
        table.setModel(new AnunciosTableModel());
        AnunciosTableModel ctm = (AnunciosTableModel) table.getModel();
            ctm.setDados(dauA.listarTodos());
        pane.setViewportView(table);
        principal.add(pane);
    }
    private void listarUser(){
        table.setModel(new UsuarioTableModel());
        UsuarioTableModel ctm = (UsuarioTableModel) table.getModel();
            ctm.setDados(dauU.listarTodos());
        pane.setViewportView(table);
        principal.add(pane);
    }
    private void posicaoFrame() {
        Configs.posFX = this.getX();
        Configs.posFY = this.getY();
    }
    private boolean temLinhaSelecionada() {
        boolean x =false;
        int c=0;
         for (int i = 0; i < table.getRowCount(); i++) {
           if(table.isRowSelected(i)){
               c++;
           }
        }
        if (c==1) {
            x=true;
        }
        return x;
    }

    public PainelFundo getPrincipal() {
        return principal;
    }
    
}