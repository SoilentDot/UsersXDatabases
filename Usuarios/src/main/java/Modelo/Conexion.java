
package Modelo;

import Vista.Registro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static String url = "jdbc:mariadb://localhost:3306/a";
    private static String user = "root";
    private static String password = "contra";

    public static Connection getConnection() {

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try {
            Connection conn = getConnection();
            if (conn.isValid(5)) {
                System.out.println("Conectado");
            }
            new Registro(conn);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
