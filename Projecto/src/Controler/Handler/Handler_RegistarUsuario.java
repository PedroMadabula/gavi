package Controler.Handler;

import Controler.Secure;
import Controler.Validacao;
import Model.Value.Usuario;
import View.View_RegistarUsuario;
import static com.itextpdf.text.pdf.PdfFileSpecification.url;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

public class Handler_RegistarUsuario implements ActionListener{
    private View_RegistarUsuario view;
    private Validacao val;
    private String erro;

    public Handler_RegistarUsuario(View_RegistarUsuario aThis) {
        aThis.iniciarComponentes();
        aThis.getEscolher().addActionListener(this);
        aThis.getVoltar().addActionListener(this);
        aThis.getRegistar().addActionListener(this);
        aThis.getTfBI().addActionListener(this);
        view = aThis;
        val = new Validacao();
        erro="";
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==view.getTfBI()){
            if(val.validarBI(view.getTfBI().getText())){
                view.getTfBI().setBorder(new LineBorder(Color.GREEN, 5));
                System.out.println("yes");
            }else{
                view.getTfBI().setBorder(new LineBorder(Color.RED, 5));
                System.out.println("No");
            }
        }
        if(e.getSource()==view.getEscolher()){
          try{
            Controler.Controle_EscolherFoto picF = new Controler.Controle_EscolherFoto();
            if(picF.getPath()!=null){
                view.setUrl(picF.getPath());
                view.getEscolher().setText(view.getUrl());
                view.getEscolher().setBackground(Color.GREEN);
                ImageIcon imagem = new ImageIcon("upload\\Usuarios\\"+view.getUrl());
                view.getFoto().setImagem(imagem);
                view.getFoto().setVisible(false);
                view.getFoto().setVisible(true);
                
            }
            }catch(NullPointerException erro){
               view.setUrl("default.jpg");
            }
        }
        
        if(e.getSource()==view.getVoltar()){
            if(Secure.isRegistando )
                Secure.isRegistando = false;
            if(Secure.editarConta )
                Secure.areaUser = true;
                Secure.editarConta = false;
            view.getViewP().controlePainel();
        }
        if(e.getSource()==view.getRegistar()){
             Usuario temp =new Usuario();
                validar();
                
                if(erro.equals("")){
                    temp.setBi(view.getTfBI().getText());
                    temp.setNome(view.getTfnome().getText());
                    String Bairro=(String) view.getCb().getSelectedItem();
                    temp.setBairro(Bairro);
                    temp.setApelido(view.getTfApelido().getText());
                    int celular=Integer.parseInt(view.getTfCelular().getText());
                    temp.setCelular(celular);
                    int nQua = (int) view.getsQuarteirao().getValue();
                    temp.setQuarteirao(nQua);

                    temp.setRua(view.getTfRua().getText());
                    temp.setNomeUsuario(view.getTfnomeUsuario().getText()); 
                    int senhaA=Integer.parseInt(view.getTfsenha().getText());
                    temp.setSenha(senhaA);
                    temp.setEmail(view.getTfemail().getText());

                   // if(temp.getId()<=0){
                     view.getDaoU().inserir(temp); 
                    //}else{
                     //  dauA.alterar(temp);
                    //}
                }else{
                    JOptionPane.showMessageDialog(null, erro);
                }
                
                
        }
        
    } 
    private void validar() {
        erro ="";
        if(val.validarBI(view.getTfBI().getText())){
                    view.getTfBI().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfBI().setBorder(new LineBorder(Color.RED, 5)); erro+="\n1 - BI Deve ter 12 Digitos e 1 Letra";
                }
                if(val.validarString(view.getTfnome().getText(),3,10)){
                    view.getTfnome().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfnome().setBorder(new LineBorder(Color.RED, 5)); erro+="\n2 - Nome Deve Ter Entre 3 e 10 letrar";
                }
                if(val.validarString(view.getTfApelido().getText(),3,10)){
                    view.getTfApelido().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfApelido().setBorder(new LineBorder(Color.RED, 5)); erro+="\n3 - Apelido Deve Ter Entre 3 e 10 letrar";
                }
                if(val.validarEmail(view.getTfemail().getText())){
                    view.getTfemail().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfemail().setBorder(new LineBorder(Color.RED, 5)); erro+="\n4 - Email deve conter @ e um ponto";
                }
                if(val.validarInt(view.getTfCelular().getText(),820000000,870000000)){
                    view.getTfCelular().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfCelular().setBorder(new LineBorder(Color.RED, 5)); erro+="\n5 - Celular 8(2-7) XXX XXXX sem espaco";
                }
                if(val.validarSenha(view.getTfsenha().getText(),0000,9999)){
                    view.getTfsenha().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfsenha().setBorder(new LineBorder(Color.RED, 5)); erro+="\n6 - Senha Deve ter 4 Numeros";
                }
                if(val.validarRua(view.getTfRua().getText(),3,20)){
                    view.getTfRua().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfRua().setBorder(new LineBorder(Color.RED, 5)); erro+="\n7 - Rua Deve Ter Entre 3 e 10 Caracteres";
                }
                if(val.validarString(view.getTfnomeUsuario().getText(),3,10)){
                    view.getTfnomeUsuario().setBorder(new LineBorder(Color.GREEN, 5)); 
                }else{
                    view.getTfnomeUsuario().setBorder(new LineBorder(Color.RED, 5)); erro+="\n8 - Nome Usuario Deve Ter Entre 3 e 10 Caracteres";
                }
    }
}
