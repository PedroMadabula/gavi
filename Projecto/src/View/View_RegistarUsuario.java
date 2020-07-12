
package View;

import Controler.Configs;
import Controler.Handler.Handler_RegistarUsuario;
import Model.Dao.DAO_Anuncio;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Model.Dao.DAO_Usuario;
import Model.Value.Anuncios;
import Model.Value.Usuario;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Administrator
 */
public class View_RegistarUsuario extends JFrame{
    private JButton Registar,voltar,escolher;
    private JLabel  BI, nome, Apelido, Celular, Bairro, Rua, Quarteirao, totalAnuncioPublicado, Conta,nomeUsuario,senha,email;
    private JTextField  tfBI, tfnome, tfApelido, tfCelular, tfRua, tftotalAnuncioPublicado, tfConta, tfnomeUsuario,tfsenha,tfemail;
    private JSpinner sQuarteirao;
    private JComboBox cb;
    private PainelFundo principal,foto,root;
    private Font fonte = Configs.fonte;
    private DAO_Usuario daoU;
    private String url = "default.jpg";
    private View_Principal viewP;
    public View_RegistarUsuario(){
        Handler_RegistarUsuario h = new Handler_RegistarUsuario(this);
    }

    View_RegistarUsuario(View_Principal aThis) {
        this();
        viewP = aThis;
    }
      public void Update(String BI){
          Usuario temp = daoU.recuperar(BI);
          iniciarComponentes();
          
            getTfBI().setText(temp.getBi());
            getTfnome().setText(temp.getNome());
            getTfApelido().setText(temp.getApelido());
            getCb().setSelectedItem(temp.getBairro());
            getTfCelular().setText(temp.getCelular()+"");       
            getsQuarteirao().setValue(temp.getQuarteirao());      
            getTfRua().setText(temp.getRua());
            getTfnomeUsuario().setText(temp.getNomeUsuario()); 
            getTfsenha().setText(temp.getSenha()+"");
            getTfemail().setText(temp.getEmail());
          
    }
    public void iniciarComponentes(){
         daoU = new DAO_Usuario();
         setTitle("JANELA");
         
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
         
         
        tfBI = new JTextField();
        tfBI.checkImage(new ImageIcon(Configs.iconLogin).getImage(), 0, 0, null);
        tfnome = new JTextField();
        tfApelido = new JTextField();
        tfCelular = new JTextField();
        
        cb = new JComboBox();
        java.util.List<String> listaBairros;
        DAO_Anuncio dauA = new DAO_Anuncio();
        listaBairros = dauA.getBairro();
            for (int i=1;i<listaBairros.size();i++){
                cb.addItem(listaBairros.get(i).substring(listaBairros.get(i).indexOf(';')+1));
        }
      
        tfRua = new JTextField();
        sQuarteirao = new JSpinner(new SpinnerNumberModel(1, 1, 200, 1));
        tfnomeUsuario = new JTextField();
        tfsenha = new JTextField();
        tfemail = new JTextField();
         
        foto = new PainelFundo("upload\\Usuarios\\"+url);
        BI=new JLabel("BI :  ");  
        nome=new JLabel("Nome   : "); 
        Apelido=new JLabel("Apelido: "); 
        Celular=new JLabel("Celular: "); 
        Bairro=new JLabel("Bairro : "); 
        Rua=new JLabel("Rua    :  "); 
        Quarteirao=new JLabel("Quarteirao: "); 
        nomeUsuario = new JLabel("Nome Usuario: ");
        senha = new JLabel("Senha: ");
        email = new JLabel("Email: ");
        
        BI.setFont(Configs.fonte);
         nome.setFont(Configs.fonte);
         Apelido.setFont(Configs.fonte);
         Celular.setFont(Configs.fonte);
         Bairro.setFont(Configs.fonte);
         Rua.setFont(Configs.fonte);
         Quarteirao.setFont(Configs.fonte);
         nomeUsuario.setFont(Configs.fonte);
         senha.setFont(Configs.fonte);
         email.setFont(Configs.fonte);
         
         escolher = new JButton("Escolher");
         escolher.setBounds(380, 50, 100, 40);
         escolher.setBackground(Configs.color);
         principal.add(escolher);
          
         foto.setBounds(100, 50, 250, 90);
         BI.setBounds(100, 150, 250, 40);
         nome.setBounds(100, 200, 250, 40);
         Apelido.setBounds(100, 250, 250, 40);
         Celular.setBounds(100, 300, 250, 40);
         Bairro.setBounds(100, 350, 250, 40);
         Rua.setBounds(100, 400, 250, 40);
         Quarteirao.setBounds(100, 450, 250, 40);
         nomeUsuario.setBounds(480, 150, 250, 40);
         senha.setBounds(480, 200, 250, 40);
         email.setBounds(480, 250, 250, 40);
 
         tfBI.setBounds(200, 150, 150, 40);       tfBI.setToolTipText("12 Digitos e uma letra");
         tfnome.setBounds(200, 200, 150, 40);     
         tfApelido.setBounds(200, 250, 150, 40);
         tfCelular.setBounds(200, 300, 150, 40);
         cb.setBounds(200, 350, 150, 40);
         tfRua.setBounds(200, 400, 150, 40);
         sQuarteirao.setBounds(200, 450, 150, 40);
         tfnomeUsuario.setBounds(600, 150, 150, 40);
         tfsenha.setBounds(600, 200, 150, 40);
         tfemail.setBounds(600, 250, 150, 40);
         
         
         principal.add(foto);
         principal.add(BI);
         principal.add(nome);
         principal.add(Apelido);
         principal.add(Celular);
         principal.add(Bairro);
         principal.add(Rua);
         principal.add(Quarteirao);
         principal.add(nomeUsuario);
         principal.add(senha);
         principal.add(email);
         
         principal.add(foto);
         principal.add(tfBI);
         principal.add(tfnome);
         principal.add(tfApelido);
         principal.add(tfCelular);
         principal.add(cb);
         principal.add(tfRua);
         principal.add(sQuarteirao);
         principal.add(tfnomeUsuario);
         principal.add(tfsenha);
         principal.add(tfemail);
         
         Registar = new JButton("Registar");
         voltar = new JButton("voltar");
         Registar.setBackground(Configs.color);
         voltar.setBackground(Configs.color);
         Registar.setBounds(100, 560, 100, 40);
         voltar.setBounds(280, 560, 100, 40);
         principal.add(Registar);
         principal.add(voltar);
         
        
        
        root.add(principal);
        add(root);
        //setVisible(true);
    }

    private void posicaoFrame() {
        Configs.posFX = this.getX();
        Configs.posFY = this.getY();
    }

    public JButton getRegistar() {
        return Registar;
    }

    public JButton getVoltar() {
        return voltar;
    }

    public JButton getEscolher() {
        return escolher;
    }

    public JLabel getBI() {
        return BI;
    }

    public JLabel getNome() {
        return nome;
    }

    public JLabel getApelido() {
        return Apelido;
    }

    public JLabel getCelular() {
        return Celular;
    }

    public JLabel getBairro() {
        return Bairro;
    }

    public JLabel getRua() {
        return Rua;
    }

    public JLabel getQuarteirao() {
        return Quarteirao;
    }

    public JLabel getTotalAnuncioPublicado() {
        return totalAnuncioPublicado;
    }

    public JLabel getConta() {
        return Conta;
    }

    public JLabel getNomeUsuario() {
        return nomeUsuario;
    }

    public JLabel getSenha() {
        return senha;
    }

    public JLabel getEmail() {
        return email;
    }

    public JTextField getTfBI() {
        return tfBI;
    }

    public JTextField getTfnome() {
        return tfnome;
    }

    public JTextField getTfApelido() {
        return tfApelido;
    }

    public JTextField getTfCelular() {
        return tfCelular;
    }

    public JTextField getTfRua() {
        return tfRua;
    }

    public JTextField getTftotalAnuncioPublicado() {
        return tftotalAnuncioPublicado;
    }

    public JTextField getTfConta() {
        return tfConta;
    }

    public JTextField getTfnomeUsuario() {
        return tfnomeUsuario;
    }

    public JTextField getTfsenha() {
        return tfsenha;
    }

    public JTextField getTfemail() {
        return tfemail;
    }

    public JSpinner getsQuarteirao() {
        return sQuarteirao;
    }

    public JComboBox getCb() {
        return cb;
    }

    public PainelFundo getPrincipal() {
        return principal;
    }

    public PainelFundo getFoto() {
        return foto;
    }

    public PainelFundo getRoot() {
        return root;
    }

    public Font getFonte() {
        return fonte;
    }

    public DAO_Usuario getDaoU() { return daoU;}
    public String getUrl() {return url;}
    public void setUrl(String url) {this.url = url;}

    public View_Principal getViewP() {
        return viewP;
    }
    
}