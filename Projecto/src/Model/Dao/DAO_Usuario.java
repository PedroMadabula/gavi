package Model.Dao;

import Model.Value.Anuncios;
import Model.Value.Usuario;
import java.sql.Connection;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
public class DAO_Usuario {
    private Connection c;
    
    public DAO_Usuario(){
        try {
            this.c = BDconexao.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void inserir(Usuario usuario){
       try{
        String sql;
        PreparedStatement ps;
        
        sql = "SELECT `nrConta` FROM conta ORDER BY `nrConta` DESC LIMIT 1";
        ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int nrUsuario=99999;
        while (rs.next()) {
         nrUsuario = rs.getInt("nrConta");
        }
        nrUsuario++;
        
        sql = "INSERT INTO `conta`(`nrConta`, `nomeUsuario`, `senha`, `email`) VALUES ( ?, ?, ?, ?)";
        ps = c.prepareStatement(sql);
        ps.setInt(1, nrUsuario);
        ps.setString(2, usuario.getNomeUsuario());
        ps.setInt(3, usuario.getSenha());
        ps.setString(4, usuario.getEmail());
        ps.execute();
        ps.close();
        
        sql = "INSERT INTO `usuario`(`BI`, `nome`, `Apelido`, `celular`, `bairro`, `rua`, `quarteirao`, `totalAnuncioPublicado`, `nrConta`, `Url`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ps = c.prepareStatement(sql);
        ps.setString(1, usuario.getBi());
        ps.setString(2, usuario.getNome());
        ps.setString(3, usuario.getApelido());
        ps.setInt(4, usuario.getCelular());
        ps.setString(5, usuario.getBairro());
        ps.setString(6, usuario.getRua());
        ps.setInt(7, usuario.getQuarteirao());
        ps.setInt(8, 0);
        ps.setInt(9, nrUsuario);
        ps.setString(10, usuario.getUrlfoto());
        ps.execute();
        ps.close();
        
        JOptionPane.showMessageDialog(null, "Registado");
         } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Usuario> listarTodos(){
        try{
        String sql = "SELECT * FROM usuario u, conta c WHERE u.nrConta = c.nrConta";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Usuario> lista = new ArrayList<>();
        while (rs.next()) {
           Usuario usuario = new Usuario();
            usuario.setBi(rs.getString("BI"));
            usuario.setApelido(rs.getString("Apelido"));
            usuario.setCelular(rs.getInt("celular"));
            usuario.setNome(rs.getString("nome"));
            usuario.setBairro(rs.getString("bairro"));
            usuario.setSenha(rs.getInt("senha"));
            usuario.setEmail(rs.getString("email"));
            usuario.setNomeUsuario(rs.getString("nomeUsuario"));
            usuario.setNrConta(rs.getInt("nrConta"));
            usuario.setRua(rs.getString("rua"));
            usuario.setQuarteirao(rs.getInt("quarteirao"));
            usuario.setTotalAd(rs.getInt("totalAnuncioPublicado"));
            usuario.setUrlfoto(rs.getString("Url"));
           lista.add(usuario);
        }
         ps.close();
        return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    public Usuario recuperar(String BI){
        try{
            String sql = "SELECT * FROM usuario u, conta c WHERE u.nrConta = c.nrConta AND u.BI=?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, BI);
            ResultSet rs = ps.executeQuery();
            Usuario usuario = new Usuario();
        
        if (rs.next()) {
            usuario.setBi(rs.getString("BI"));
            usuario.setApelido(rs.getString("Apelido"));
            usuario.setCelular(rs.getInt("celular"));
            usuario.setNome(rs.getString("nome"));
            usuario.setBairro(rs.getString("bairro"));
            usuario.setSenha(rs.getInt("senha"));
            usuario.setEmail(rs.getString("email"));
            usuario.setNomeUsuario(rs.getString("nomeUsuario"));
            usuario.setNrConta(rs.getInt("nrConta"));
            usuario.setRua(rs.getString("rua"));
            usuario.setQuarteirao(rs.getInt("quarteirao"));
            usuario.setTotalAd(rs.getInt("totalAnuncioPublicado"));
            usuario.setUrlfoto(rs.getString("Url"));
        }

        return usuario;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return new Usuario();
        }
    }
    
    public void alterar(Usuario usuario) {
       try{
        String sql;
        PreparedStatement ps;
        
        
        sql = "UPDATE `conta` SET  `nomeUsuario` = ?, `senha` = ?, `email` = ? WHERE `nrConta` = ?";
        ps = c.prepareStatement(sql);
        ps.setString(1, usuario.getNomeUsuario());
        ps.setInt(2, usuario.getSenha());
        ps.setString(3, usuario.getEmail());
        ps.setInt(4, usuario.getNrConta());
        ps.execute();
        ps.close();
        
        sql = "UPDATE `usuario` SET `BI` = ?, `nome` = ?, `Apelido` = ?, `celular` = ?, `bairro`=?, `rua`=?, `quarteirao`=?,  `Url`=? where `nrConta`=?";
        ps = c.prepareStatement(sql);
        ps.setString(1, usuario.getBi());
        ps.setString(2, usuario.getNome());
        ps.setString(3, usuario.getApelido());
        ps.setInt(4, usuario.getCelular());
        ps.setString(5, usuario.getBairro());
        ps.setString(6, usuario.getRua());
        ps.setInt(7, usuario.getQuarteirao());
        ps.setString(8, usuario.getUrlfoto());
        ps.setInt(9, usuario.getNrConta());
        ps.execute();
        ps.close();
        
        JOptionPane.showMessageDialog(null, "Actualizado");
         } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void excluir(Usuario user) {
        try{
        String sql;
        PreparedStatement ps;
        
        sql = "DELETE FROM `usuario_imovel` WHERE `BI` = ?";
        ps = c.prepareStatement(sql);
        ps.setString(1, user.getBi());
        ps.execute();
        ps.close();
        
        sql = "DELETE FROM `usuario` WHERE `BI` = ?";
        ps = c.prepareStatement(sql);
        ps.setString(1, user.getBi());
        ps.execute();
        ps.close();
        
        
        sql = "DELETE FROM `conta` WHERE `nrConta` = ?";
        ps = c.prepareStatement(sql);
        ps.setInt(1, user.getNrConta());
        ps.execute();
        ps.close();
        
        JOptionPane.showMessageDialog(null, "Deletado");
         } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean existeNomeUsuario(String nomeUsuario) {
        boolean x=false;
        try {
            String sql = "SELECT `nomeUsuario` FROM `conta` WHERE `nomeUsuario` =?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              if(rs.getString("nomeUsuario").equalsIgnoreCase(nomeUsuario)){
                   x= true;
              }
            }
              return  x;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getSenha(String nomeUsuario) {
        String x="";
        try {
            String sql = "SELECT `senha` FROM `conta` WHERE `nomeUsuario` = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              x = rs.getString("senha");
            }
            return  x;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    
    public boolean isAdmin(String nomeUsuario) {
         boolean x=false;
        try {
            String sql = "SELECT NomeUsuario FROM `admin` WHERE `NomeUsuario` =?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              if(rs.getString("NomeUsuario").equalsIgnoreCase(nomeUsuario)){
                   x= true;
              }
            }
              return  x;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public String getBi(String nomeUsuario) {
      String x="";
        try {
            String sql = "SELECT u.BI FROM `conta` as c , usuario as u WHERE c.`nrConta` = u.`nrConta` and c.`nomeUsuario`= ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, nomeUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              x = rs.getString("BI");
            }
            return  x;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
