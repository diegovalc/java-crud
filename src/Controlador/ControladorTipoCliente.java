
package Controlador;

import DAO.TipoClienteDAO;
import Modelo.TipoCliente;
import Vista.VistaCrud;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author diego
 */
public class ControladorTipoCliente  {
    
    private TipoCliente tipoCliente;
    private VistaCrud vistaCrud;
    private TipoClienteDAO tipoClienteDao;

    public ControladorTipoCliente(TipoCliente tipoCliente, VistaCrud vistaCrud, TipoClienteDAO tipoClienteDao) {
        this.tipoCliente = tipoCliente;
        this.vistaCrud = vistaCrud;
        this.tipoClienteDao = tipoClienteDao;
        
    }
    
    public void llenarCombo(){
        vistaCrud.cmbTipo.removeAllItems();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        vistaCrud.cmbTipo.setModel(model);
        List<TipoCliente> tipoClientes = tipoClienteDao.obtenerTipoCliente();
        
        for (TipoCliente tipoCliente : tipoClientes) {
            System.out.println("ID: " + tipoCliente.getId());
            System.out.println("Descripcion: " + tipoCliente.getDescripcion());
            //vistaCrud.cmbTipo.addItem(String.valueOf(tipoCliente.getId()));
            // vistaCrud.cmbTipo.addItem(tipoCliente.getDescripcion()); 
            model.addElement(tipoCliente);
            
        }
    }
      
}
