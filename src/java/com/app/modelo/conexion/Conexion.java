package com.app.modelo.conexion;

/**
 *
 * @author Usuario
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getconexionBD() 
            throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        Connection cnn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario","javauser","1234");
        cnn.setAutoCommit(false);
        return cnn;
    }
    
    public static void cerrarCnn(Connection cnn) throws SQLException{
        cnn.commit();
        cnn.close();
    }
    public static void reversarCnn(Connection cnn) throws SQLException{
        cnn.rollback();
        cnn.close();
    }
}
