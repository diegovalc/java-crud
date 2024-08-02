
package DAO;

import Modelo.TipoCliente;
import java.util.List;

/**
 *
 * @author diego
 */
public interface TipoClienteDAO {
    List<TipoCliente> obtenerTipoCliente();
    public TipoCliente obtenerTipoClientePorId(int id);
    
    
}
