package Controler;

import Model.Dao.BDconexao;
import View.View_Inicial;
import View.View_Principal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Inicio extends Thread{ 
   private View_Inicial v;
   
public Inicio(){ 
    v =new View_Inicial();
    run();
} 

  public void run(){ 
    try{ 
        
        for(int i=1; i <= 100; i++){ 
            v.getProgress().setValue(i);
            sleep((long) ((long)50*1.7*0)); 
            v.getCarregando().setText(i+"%");
            if(i==20){
                try {
                    Connection c = BDconexao.getConnection();
                } catch (SQLException ex) {
                    erroConexao();
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if(v.getProgress().getValue()==100){
            
            View_Principal p = new View_Principal();
           
                //sleep((long) ((long)150*1.7)); 
                v.removeAll();
                v.setVisible(false);
            
        }
    } 
    catch (InterruptedException ie) { 
    } 
    
  } 

    private void erroConexao() {
        v.getCarregando().setText("Erro...");
        v.getProgress().setString("Falha ao Conectar a Base de dados");
        JOptionPane.showMessageDialog(null, "erro de conexao");
        System.out.println("erro de conexao");
    }
  
}
