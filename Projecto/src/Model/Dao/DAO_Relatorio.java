package Model.Dao;

import Model.Value.Anuncios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * Classe de acesso a dados
 *
 * @author Manuel
 */
public class DAO_Relatorio {
    private Connection c;
    public static boolean sucesso;
    
    public DAO_Relatorio(){
        try {
            sucesso = false;
            this.c = BDconexao.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAO_Relatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public List<String> listarAdUsuario(String Bi){
        try{
        String sql = "SELECT i.idImovel, i.preco, i.bairro, i.tipoImovel, u.bi, u.nome, u.Apelido FROM imovel i, usuario_imovel ui, usuario u where ui.idImovel = i.idImovel and u.bi = ui.bi and ui.bi=? ";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, Bi);
        ResultSet rs = ps.executeQuery();

        List<String> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(rs.getInt("idImovel")+"");
            lista.add(rs.getInt("preco")+" MZN");
            lista.add(rs.getString("bairro")+"");
            lista.add(rs.getString("tipoImovel")+"");
            lista.add(rs.getString("bi")+"");
            lista.add(rs.getString("nome")+"");
            lista.add(rs.getString("Apelido")+"");
        }
         ps.close();
        return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Relatorio.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
}