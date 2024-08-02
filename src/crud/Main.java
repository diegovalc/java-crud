
package crud;

import Controlador.ControladorCliente;
import Controlador.ControladorTipoCliente;
import DAO.ClienteDAO;
import DAO.ClienteDAOImp;
import DAO.TipoClienteDAO;
import DAO.TipoClienteDAOImp;
import Modelo.Cliente;
import Modelo.TipoCliente;
import Vista.VistaCrud;

public class Main {

    public static void main(String[] args) {

        Cliente cliente = new Cliente();
        VistaCrud vistaCrud = new VistaCrud();
        ClienteDAO clienteDao = new ClienteDAOImp();
        TipoCliente tipoCliente = new TipoCliente();
        TipoClienteDAO tipoClienteDao = new TipoClienteDAOImp();

        ControladorCliente controladorCliente = new ControladorCliente(cliente,vistaCrud,clienteDao,tipoClienteDao);
        controladorCliente.iniciar();
        ControladorTipoCliente controladorTipoCliente = new ControladorTipoCliente(tipoCliente,vistaCrud,tipoClienteDao);
        controladorTipoCliente.llenarCombo();
        vistaCrud.setVisible(true);
        
    }  
}
