package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbContext {

    private String url = "jdbc:postgresql...";
    private String usuario = "";
    private String senha = "";


    // representar a conexão com o banco de dados
    public Connection connection = null;

    public DbContext() {
    }

    // CONEXÃO
    public void conectarBanco() {
        try {
			this.connection = DriverManager.getConnection(this.url, this.usuario, this.senha);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
	}

    // DESCONECTAR
    public void desconectarBanco() {
        try {
            this.connection.close();        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // (SELECT)
    public ResultSet executarQuerySql(String query) {
        try {
            Statement stmt = this.connection.createStatement();

            ResultSet resultSet = stmt.executeQuery(query);
            return resultSet;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //(INSERT/UPDATE/DELETE)
    public Integer executarUpdateSqlRow(String query) {
        int linhasAfetadas = 0;
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas;

        } catch (Exception e) {
            e.printStackTrace();
            return linhasAfetadas;
        }
    }

    //(INSERT/UPDATE/DELETE)
    public Boolean executarUpdateSql(String query) {
        try {
            PreparedStatement stmt = this.connection.prepareStatement(query);
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
