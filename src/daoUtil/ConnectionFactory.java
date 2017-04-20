package daoUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by murilo on 06/04/2017.
 */
public class ConnectionFactory {

    private Connection con = null;

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petShop", "root", "root");
            return con;
        } catch (Exception e) {
            System.out.println("Erro na conex‹o: " + e.getMessage());
        }
        return con;
    }

    public void setClose() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println("Erro na conexão: " + e.getMessage());
        }
    }
}