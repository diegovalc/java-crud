package DAO;

import Modelo.Cliente;
import Modelo.Conexion;
import Modelo.TipoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOImp implements ClienteDAO {
    Conexion conexion = new Conexion();
    Connection con;

    @Override
    public boolean insertar(Cliente cliente) {
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "insert into cliente (nombre,email,telefono,tipo_cliente) values (?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getEmail());
            ps.setString(3, cliente.getTelefono());
            ps.setInt(4, cliente.getTipo_cliente().getId());
            ps.execute();
            return true; // quitar booleanos
        } catch (SQLException e) {
            System.err.println(e); // utilizar loggers
            return false;
        }
    }

    @Override
    public boolean modificar(Cliente cliente) {
        return true;
    }

    @Override
    public boolean eliminar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean buscar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> obtenerClientes() {
        List<Cliente> clientes = new ArrayList<>();
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "select * from cliente";
        ResultSet rs;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) { 
                Cliente cliente = new Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setEmail(rs.getString("email"));
                cliente.setTelefono(rs.getString("telefono"));
                // cliente.setTipo_cliente(rs.getInt("tipo_cliente"));
                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setId(rs.getInt("tipo_cliente"));
                cliente.setTipo_cliente(tipoCliente);
                clientes.add(cliente); 
            }

        } catch (SQLException e) {
            System.err.println(e);
        }
        return clientes;
    }

}
