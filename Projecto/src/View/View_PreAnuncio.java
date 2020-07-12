package View;
import Controler.Configs;
import Controler.Secure;
import Model.Dao.DAO_Anuncio;
import Model.Value.Anuncios;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class View_PreAnuncio extends JFrame implements ActionListener{

    private JLabel txt_Id,txt_titulo,txt_preco,txt_tipo,img;  
    private String url,id,titulo,preco,tipo;
    private PainelFundo principal;
    private JButton deletar, editar , desativar;
    private DAO_Anuncio daoA;
public View_PreAnuncio(){
    daoA =new DAO_Anuncio();
}
public void criar(){
                principal = new PainelFundo("");
                principal.setLayout(null);
                principal.setSize(530,100);

                ImageIcon image = new ImageIcon(url);
                img = new JLabel(image);
                img.setBackground(new Color(241,241,241));
                principal.add(img);
                
                txt_Id=new JLabel(id);
                txt_titulo=new JLabel(titulo);
                txt_preco=new JLabel(preco);
                txt_tipo=new JLabel(tipo);
                
                img.setBounds(20,15,200,65);
                txt_Id.setBounds(250,15,200,20);
                txt_preco.setBounds(250,40,200,20);
                txt_tipo.setBounds(250,65,200,20);
                txt_titulo.setBounds(250,15,200,20);
                
                txt_Id.setFont(Configs.fontePreAd);
                txt_preco.setFont(Configs.fontePreAd);
                txt_tipo.setFont(Configs.fontePreAd);
                txt_titulo.setFont(Configs.fontePreAd);
                
               // principal.add(txt_Id);
                principal.add(txt_preco);
                principal.add(txt_tipo);
                principal.add(txt_titulo);   
                principal.setBackground(Configs.colorAD);
                
        deletar = new JButton("Deletar",new View_BoxIconADS(Configs.iconDeletar));
        editar = new JButton("Editar",new View_BoxIconADS(Configs.iconEditar));
        desativar = new JButton("Des/Ativar",new View_BoxIconADS(Configs.iconDesativar));
        
        editar.setBounds(470,15,200,20);
        desativar.setBounds(470,40,200,20);
        deletar.setBounds(470,65,200,20);
        
        deletar.setFont(Configs.fonte);
        editar.setFont(Configs.fonte);
        desativar.setFont(Configs.fonte);
        
        deletar.setBackground(Configs.color);
        editar.setBackground(Configs.color);
        desativar.setBackground(Configs.color);
        
        if(Secure.isOwner){
            principal.add(deletar);
            principal.add(editar);
            principal.add(desativar);
        }
        
        deletar.addActionListener(this);
        editar.addActionListener(this);
        desativar.addActionListener(this);
}
    public PainelFundo anuncio(){
        return principal;
    }

    public void setId(String  Id) {
        this.id=Id;
    }
    public void settitulo(String titulo) {
        this.titulo="Bairro:"+titulo;
    }
    public void setpreco(String preco) {
        this.preco="Preco :"+preco + " MZN";
    }
    public void settipo(String tipo) {
        this.tipo="Imovel :"+tipo; 
    }
    public void setUrl(String url){
        this.url = url;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==deletar){
            daoA.excluirAdid(Integer.parseInt(id));
        }
        if(e.getSource()==editar){
            //System.out.print("shdjas");
            View_Publicar p = new View_Publicar(Secure.Bi);
            p.Update(Integer.parseInt(id));
            p.setVisible(true);
            
        }
        if(e.getSource()==desativar){
            Anuncios AdTemp = daoA.recuperar(Integer.parseInt(id));
            if(AdTemp.isAtivo())
                daoA.desativar(AdTemp.getId());  //desativar
            else
                daoA.ativar(AdTemp.getId());  //ativar
            
        }
    }
}
