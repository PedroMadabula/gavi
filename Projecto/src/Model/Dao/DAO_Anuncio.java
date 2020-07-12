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
public class DAO_Anuncio {
    private Connection c;
    public static boolean sucesso;
    
    public DAO_Anuncio(){
        try {
            sucesso = false;
            this.c = BDconexao.getConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    public void inserir(Anuncios anuncios,String Bi){
        try{
        String sql;
        PreparedStatement ps;
        
        sql = "SELECT `idImovel` FROM imovel ORDER BY `idImovel` DESC LIMIT 1";
        ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int idImovel=99999;
        while (rs.next()) {
         idImovel = rs.getInt("idImovel");
        }
        idImovel++;

        
        sql = "INSERT INTO `imovel` (`idImovel`, `preco`, `bairro`, `nrCasa`, `quarteirao`, `tipoImovel`, `url`, `url1`, `url2`, `url3`, `titulo`, `descricao`, `tipoAnuncio`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        ps = c.prepareStatement(sql);
        ps.setInt(1, idImovel);
        ps.setFloat(2, anuncios.getPreco());
        ps.setString(3, anuncios.getBairro());
        ps.setInt(4, anuncios.getNrCasa());
        ps.setInt(5, anuncios.getQuarteirao());
        ps.setString(6, anuncios.getTipo());
        ps.setString(7, anuncios.getURL());
        ps.setString(8, anuncios.getURL1());
        ps.setString(9, anuncios.getURL2());
        ps.setString(10, anuncios.getURL3());
        ps.setString(11, anuncios.getTitulo());
        ps.setString(12, anuncios.getDescricao());
        ps.setString(13, anuncios.getTipoAnuncio());
        ps.execute();
        
        sql = "INSERT INTO `usuario_imovel`(`BI`, `idImovel`, `dataInicio`) VALUES ( ?, ?,Now())";
        ps = c.prepareStatement(sql);
        ps.setString(1, Bi);
        ps.setInt(2, idImovel);
        ps.execute();
        
        ps.close();
        JOptionPane.showMessageDialog(null, "Anuncio Publicado");
         } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void alterar(Anuncios anuncios){
        try{
            String sql;
            PreparedStatement ps;
            sql = "UPDATE `imovel` SET `preco`=?,`bairro`=?,`nrCasa`=?,`quarteirao`=?,`tipoImovel`=?,`url`=?,`url1`=?,`url2`=?,`url3`=?,`titulo`=?,`descricao`=?,`tipoAnuncio`=? WHERE `imovel`.`idImovel` = ?";
            ps = c.prepareStatement(sql);
            ps.setFloat(1, anuncios.getPreco());
            ps.setString(2, anuncios.getBairro());
            ps.setInt(3, anuncios.getNrCasa());
            ps.setInt(4, anuncios.getQuarteirao());
            ps.setString(5, anuncios.getTipo());
            ps.setString(6, anuncios.getURL());
            ps.setString(7, anuncios.getURL1());
            ps.setString(8, anuncios.getURL2());
            ps.setString(9, anuncios.getURL3());
            ps.setString(10, anuncios.getTitulo());
            ps.setString(11, anuncios.getDescricao());
            ps.setString(12, anuncios.getTipoAnuncio());
            ps.setInt(13, anuncios.getId());
            ps.execute();
            ps.close();

        JOptionPane.showMessageDialog(null, "Anuncio Publicado");
        
         } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<Anuncios> listarTodos(){
        try{
        String sql = "SELECT * FROM `imovel` ORDER by `idImovel` DESC";
        PreparedStatement ps = c.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Anuncios> lista = new ArrayList<>();
        while (rs.next()) {
           Anuncios anuncios = new Anuncios();
              anuncios.setId(rs.getInt("idImovel"));
              anuncios.setPreco(rs.getFloat("preco"));
              anuncios.setDescricao(rs.getString("Descricao"));
              anuncios.setBairro(rs.getString("bairro"));
              anuncios.setTitulo(rs.getString("titulo"));
              anuncios.setQuarteirao(rs.getInt("quarteirao"));
              anuncios.setTipo(rs.getString("tipoImovel"));
              anuncios.setTipoAnuncio(rs.getString("TipoAnuncio"));
              anuncios.setURL(rs.getString("URL"));
              anuncios.setURL1(rs.getString("URL1"));
              anuncios.setURL2(rs.getString("URL2"));
              anuncios.setURL3(rs.getString("URL3"));
              anuncios.setNrCasa(rs.getInt("nrCasa"));
              anuncios.setAtivo(rs.getBoolean("activo"));
            lista.add(anuncios);
        }
         ps.close();
        return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    public List<Anuncios> listarTodosDoUsuario(String Bi){
        try{
        String sql = "SELECT * FROM usuario_imovel as a , imovel i , usuario u WHERE a.idImovel = i.idImovel AND a.BI = u.BI AND u.BI =?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setString(1, Bi);
        ResultSet rs = ps.executeQuery();

        List<Anuncios> lista = new ArrayList<>();
        while (rs.next()) {
           Anuncios anuncios = new Anuncios();
              anuncios.setId(rs.getInt("idImovel"));
              anuncios.setPreco(rs.getFloat("preco"));
              anuncios.setDescricao(rs.getString("Descricao"));
              anuncios.setBairro(rs.getString("bairro"));
              anuncios.setTitulo(rs.getString("titulo"));
              anuncios.setQuarteirao(rs.getInt("quarteirao"));
              anuncios.setTipo(rs.getString("tipoImovel"));
              anuncios.setTipoAnuncio(rs.getString("TipoAnuncio"));
              anuncios.setURL(rs.getString("URL"));
              anuncios.setURL1(rs.getString("URL1"));
              anuncios.setURL2(rs.getString("URL2"));
              anuncios.setURL3(rs.getString("URL3"));
              anuncios.setNrCasa(rs.getInt("nrCasa"));
              anuncios.setDataInicio(rs.getString("dataInicio"));
              anuncios.setBI(rs.getString("BI"));
              anuncios.setAtivo(rs.getBoolean("activo"));
            lista.add(anuncios);
        }
         ps.close();
        return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    public Anuncios recuperar(int id){
        try{
        String sql = "SELECT * FROM usuario_imovel as a , imovel i , usuario u WHERE a.idImovel = i.idImovel AND a.BI = u.BI AND i.idImovel =?";
        PreparedStatement ps = c.prepareStatement(sql);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Anuncios anuncios = new Anuncios();
        
        if (rs.next()) {
              anuncios.setId(rs.getInt("idImovel"));
              anuncios.setPreco(rs.getFloat("preco"));
              anuncios.setDescricao(rs.getString("Descricao"));
              anuncios.setBairro(rs.getString("bairro"));
              anuncios.setTitulo(rs.getString("titulo"));
              anuncios.setQuarteirao(rs.getInt("quarteirao"));
              anuncios.setTipo(rs.getString("tipoImovel"));
              anuncios.setTipoAnuncio(rs.getString("TipoAnuncio"));
              anuncios.setURL(rs.getString("URL"));
              anuncios.setURL1(rs.getString("URL1"));
              anuncios.setURL2(rs.getString("URL2"));
              anuncios.setURL3(rs.getString("URL3"));
              anuncios.setNrCasa(rs.getInt("nrCasa"));
//              anuncios.setDataInicio(rs.getString("dataInicio")+"");
              anuncios.setAtivo(rs.getBoolean("activo"));
              anuncios.setBI(rs.getString("BI"));
        }

        return anuncios;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return new Anuncios();
        }
    }
    public List<Anuncios> listarTodosFiltro(float precoMin, float precoMax, String Bairro, String Tipo, String tipoAD)throws Exception  {
        try{
        String sql ="";
        PreparedStatement ps;
        if(Bairro.equalsIgnoreCase("BAIRRO")){ // so precos
            sql = "SELECT * FROM `imovel` WHERE `preco` BETWEEN ? and ? AND `tipoAnuncio`=?  AND `tipoImovel` =? ORDER by `preco`  DESC";
            ps = c.prepareStatement(sql);
            ps.setFloat(1, precoMin);
            ps.setFloat(2, precoMax);
            ps.setString(3,tipoAD);
            ps.setString(4, Tipo);
        }else{
            sql = "SELECT * FROM `imovel` WHERE `bairro` =? AND `tipoImovel` =? And `preco` BETWEEN ? and ? AND `tipoAnuncio`=? ORDER by `idImovel` DESC";
            ps = c.prepareStatement(sql);
            ps.setString(1, Bairro);
            ps.setString(2, Tipo);
            ps.setFloat(3, precoMin);
            ps.setFloat(4, precoMax);
            ps.setString(5,tipoAD);
        }
        
        
        ResultSet rs = ps.executeQuery();

        List<Anuncios> lista = new ArrayList<>();
        while (rs.next()) {
              Anuncios anuncios = new Anuncios();
              anuncios.setAtivo(rs.getBoolean("activo"));
              anuncios.setId(rs.getInt("idImovel"));
              anuncios.setPreco(rs.getFloat("preco"));
              anuncios.setDescricao(rs.getString("Descricao"));
              anuncios.setBairro(rs.getString("bairro"));
              anuncios.setTitulo(rs.getString("titulo"));
              anuncios.setQuarteirao(rs.getInt("quarteirao"));
              anuncios.setTipo(rs.getString("tipoImovel"));
              anuncios.setTipoAnuncio(rs.getString("TipoAnuncio"));
              anuncios.setURL(rs.getString("URL"));
              anuncios.setURL1(rs.getString("URL1"));
              anuncios.setURL2(rs.getString("URL2"));
              anuncios.setURL3(rs.getString("URL3"));
              anuncios.setNrCasa(rs.getInt("nrCasa"));
            lista.add(anuncios);
        }

        return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    public List<String> getBairro() {
        try{
            String sql = "SELECT * FROM `bairro` ORDER by `id` ASC";
            PreparedStatement ps = c.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<String> lista = new ArrayList<>();
            while (rs.next()) {
                lista.add(rs.getString("id")+";"+rs.getString("bairro"));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<>();
        }
    }
    public boolean addBairro(String Bairro) {
        try{
            String sql = "INSERT INTO `bairro`(`id`, `Bairro`) VALUES (null,?)";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, Bairro);
            if(Bairro!=null){
                ps.execute();
                return true;
            }else{
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean excluirAdid(Object id) {
        try{
            String sql = "DELETE FROM `imovel` WHERE `idImovel` = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, (int) id);
            ps.execute();
            JOptionPane.showMessageDialog(null, "Anuncio Removido");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public void desativar(int id) {
        try{
            String sql;
            PreparedStatement ps;
            sql = "UPDATE `imovel` SET `activo` = '0' WHERE `imovel`.`idImovel` = ?";
            ps = c.prepareStatement(sql);
            ps.setFloat(1, id);
            ps.execute();
            ps.close();

        JOptionPane.showMessageDialog(null, "Anuncio Desativado");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ativar(int id) {
        try{
            String sql;
            PreparedStatement ps;
            sql = "UPDATE `imovel` SET `activo` = '1' WHERE `imovel`.`idImovel` = ?";
            ps = c.prepareStatement(sql);
            ps.setFloat(1, id);
            ps.execute();
            ps.close();

        JOptionPane.showMessageDialog(null, "Anuncio activado");
        } catch (SQLException ex) {
            Logger.getLogger(DAO_Anuncio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}