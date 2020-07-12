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
public class DAO_Estatisticas {
    private Connection c;
    public static boolean sucesso;
    
    public DAO_Estatisticas(){
        try {
            sucesso = false;
            this.c = BDconexao.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAO_Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public int getTotalUsuarios() {
        try{
            String sql = "SELECT COUNT(*) as count FROM Usuario";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()) {
              total=rs.getInt("count");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int getTotalAnuncios() {
        try{
            String sql = "SELECT COUNT(*) as count FROM imovel";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()) {
              total=rs.getInt("count");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int getTotalParaAluguel() {
        try{
            String sql = "SELECT COUNT(*) as count FROM imovel WHERE tipoAnuncio = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "ALUGUEL");
            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()) {
              total=rs.getInt("count");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int getTotalParaVenda() {
        try{
            String sql = "SELECT COUNT(*) as count FROM imovel WHERE tipoAnuncio = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, "VENDA");
            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()) {
              total=rs.getInt("count");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int getTotalDesativados() {
        try{
            String sql = "SELECT COUNT(*) as count FROM imovel WHERE `activo`= 0";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int total = 0;
            while (rs.next()) {
              total=rs.getInt("count");
            }
            return total;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Estatisticas.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int getTotalApagados() {
        return 0;
    }
    public int getTotalVendidos() {
        return 0;
    }
    public int getTotalAlugados() {
        return 0;
    }
     
    
    public float getPerVendidos() {
        return (float)getTotalVendidos()/getTotalAnuncios();
    }
    public float getPerAlugados() {
        return (float)getTotalAlugados()/getTotalAnuncios();
    }
    public float getPerParaVenda() {
        return (float)getTotalParaVenda()/getTotalAnuncios();
    }
    public float getPerParaAluguel() {
        return (float)getTotalParaAluguel()/getTotalAnuncios();
    }
  
}
