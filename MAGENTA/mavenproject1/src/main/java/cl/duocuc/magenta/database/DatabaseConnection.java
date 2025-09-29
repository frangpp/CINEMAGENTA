/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package cl.duocuc.magenta.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author franciscagoeppinger
 */

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Cine_DB";
    private static final String USER = "root";
    private static final String PASSWORD = "CONTRASEÑAAQUI";
    
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Se logró la conexion!");
            return connection;
        } catch (SQLException e) {
            System.err.println("Error de conexión: " + e.getMessage());
            System.err.println("URL: " + URL);
            throw e;
        }
    }
    
    public static boolean probarConexion() {
        try (Connection conn = getConnection()) {
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
