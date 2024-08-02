package DAO;

import Modelo.Conexion;
import Modelo.TipoCliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diego
 */
public class TipoClienteDAOImp implements TipoClienteDAO {

    Conexion conexion = new Conexion();
    Connection con;

    @Override
    public List<TipoCliente> obtenerTipoCliente() {
        List<TipoCliente> tipoClientes = new ArrayList<>();
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "select * from tipo_cliente";
        ResultSet rs;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                TipoCliente tipoCliente = new TipoCliente();
                tipoCliente.setId(rs.getInt("id"));
                tipoCliente.setDescripcion(rs.getString("descripcion"));
                tipoClientes.add(tipoCliente);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return tipoClientes;
    }

    @Override
    public TipoCliente obtenerTipoClientePorId(int id) {
        TipoCliente tipoCliente = new TipoCliente();
        con = conexion.getConection();
        PreparedStatement ps = null;
        String sql = "select * from tipo_cliente where id =?";
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                tipoCliente.setId(Integer.parseInt(rs.getString("id")));
                tipoCliente.setDescripcion(rs.getString("descripcion"));

            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return tipoCliente;
    }

}
