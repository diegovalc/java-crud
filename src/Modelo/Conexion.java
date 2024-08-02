package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    private String db = "clientes";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/" + db;
    
    public Connection getConection(){
        Connection con= null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(url, user, password);
            // JOptionPane.showMessageDialog(null, "Conexion exitosa!!");
        }catch (SQLException e){
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "Error al conectarse a la BD");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
