
package DAO;

import Modelo.Cliente;
import java.util.List;

/**
 *
 * @author diego
 */
public interface ClienteDAO {
    public boolean insertar(Cliente cliente);
    public boolean modificar(Cliente cliente);
    public boolean eliminar(Cliente cliente);
    public boolean buscar(Cliente cliente);
    List<Cliente> obtenerClientes();
}
