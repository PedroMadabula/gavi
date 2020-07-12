/*
 Esta classe é responsavel pela comunicação do Aplicativo com a base de dados MySQL;
 */
package Model.Dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class BDconexao implements Serializable {

    private  String Host;
    private  String BD;
    private  String user;
    private  String password;
    private  String porta;
 

    public BDconexao() {
        this.Host = "127.0.0.1";//35.202.18.130"; // 127.0.0.1 = localhost
        this.porta = "3306";
        this.BD = "gavi";//admin_GAVI";// gavi
        this.user = "root";//admin_gavi"; //root
        this.password = "";//36723673BB"; //""
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        BDconexao bDconexao = new BDconexao();
        return DriverManager.getConnection("jdbc:mysql://" + bDconexao.Host + ":" + bDconexao.porta + "/" + bDconexao.BD + "?user="+bDconexao.user+"&password="+bDconexao.password+"&noAccessToProcedureBodies=true");
    }
    public Connection ConexaoSimples() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String URL = "jdbc:mysql://" + Host + ":" + porta;
            Connection conexao = DriverManager.getConnection(URL, user, password);
            conexao.setAutoCommit(false);
            return conexao;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ClassNotFoundException("O driver de conexão não foi adicionado ao projeto.\n" + e.getMessage());
        } catch (SQLException e) {
            throw new SQLException("Problemas nos parâmetros de conexão.\n" + e.getMessage());
        }
    }
    public String getBD() {
        return BD;
    }

    public void setBD(String BD) {
        this.BD = BD;
    }

    public String getHost() {
        return Host;
    }

    public void setHost(String Host) {
        this.Host = Host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    @Override
    public String toString() {
        return "BDconexao{" + "Host=" + Host + ", BD=" + BD + ", user=" + user + ", password=" + password + ", porta=" + porta + '}';
    }

   
}
