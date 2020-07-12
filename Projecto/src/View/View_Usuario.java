
package View;

import Controler.Configs;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import Model.Dao.DAO_Usuario;
import Model.Value.Usuario;
import com.sun.java.swing.plaf.windows.WindowsBorders;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import javax.swing.border.LineBorder;

/**
 *
 * @author Administrator
 */
public class View_Usuario extends JFrame implements ActionListener{
    private JButton comprar,voltar;
    private JLabel  BI, nome, Apelido, Celular, Bairro, Rua, Quarteirao, totalAnuncioPublicado, Conta;
    private PainelFundo principal,foto,root;
    private Font fonte = Configs.fonte;
    private DAO_Usuario daoU;
    
    public View_Usuario(String Bi){
        
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
         
         Usuario user =daoU.recuperar(Bi);
       
         foto = new PainelFundo("upload\\Usuarios\\"+user.getUrlfoto());
              BI=new JLabel(("BI :  "+user.getBi()));
            nome=new JLabel(("Nome   : "+user.getNome())); 
         Apelido=new JLabel(("Apelido: "+user.getApelido()));
         Celular=new JLabel(("Celular: "+user.getCelular()));
          Bairro=new JLabel(("Bairro : "+user.getBairro()));
             Rua=new JLabel(("Rua    :  "+user.getRua()));
        Quarteirao=new JLabel(("Quarteirao: "+user.getQuarteirao()));
         
         BI.setFont(Configs.fonte);
         nome.setFont(Configs.fonte);
         Apelido.setFont(Configs.fonte);
         Celular.setFont(Configs.fonte);
         Bairro.setFont(Configs.fonte);
         Rua.setFont(Configs.fonte);
         Quarteirao.setFont(Configs.fonte);
         
         foto.setBounds(100, 50, 250, 90);
         BI.setBounds(100, 150, 250, 40);
         nome.setBounds(100, 200, 250, 40);
         Apelido.setBounds(100, 250, 250, 40);
         Celular.setBounds(100, 300, 250, 40);
         Bairro.setBounds(100, 350, 250, 40);
         Rua.setBounds(100, 400, 250, 40);
         Quarteirao.setBounds(100, 450, 250, 40);
         
         principal.add(foto);
         principal.add(BI);
         principal.add(nome);
         principal.add(Apelido);
         principal.add(Celular);
         principal.add(Bairro);
         principal.add(Rua);
         principal.add(Quarteirao);
         
         
         comprar = new JButton("Request");
         voltar = new JButton("voltar");
         comprar.setBackground(Configs.color);
         voltar.setBackground(Configs.color);
         comprar.setBounds(100, 560, 100, 40);
         voltar.setBounds(280, 560, 100, 40);
         principal.add(comprar);
         principal.add(voltar);
         
         voltar.addActionListener(this);
        
        root.add(principal);
        add(root);
        setVisible(true);
    }

    public JButton getComprar() {
        return comprar;
    }

    public void setComprar(JButton comprar) {
        this.comprar = comprar;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==voltar){
            super.setVisible(false);
        }
    }
    private void posicaoFrame() {
        Configs.posFX = this.getX();
        Configs.posFY = this.getY();
    }
}
