package Controlador;

import DAO.ClienteDAO;
import Modelo.Cliente;
import Modelo.TipoCliente;
import Vista.VistaCrud;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import DAO.TipoClienteDAO;

public class ControladorCliente implements ActionListener {

    private Cliente cliente;
    private VistaCrud vistaCrud;
    private ClienteDAO clienteDao;
    private TipoCliente tipoCliente;
    private TipoClienteDAO tipoClienteDao;

    public ControladorCliente(Cliente cliente, VistaCrud vistaCrud, ClienteDAO clienteDao, TipoClienteDAO tipoClienteDao) {
        this.cliente = cliente;
        this.vistaCrud = vistaCrud;
        this.clienteDao = clienteDao;
        this.tipoClienteDao = tipoClienteDao;
        this.vistaCrud.btnAgregar.addActionListener(this);
        this.vistaCrud.btnLimpiar.addActionListener(this);
        this.vistaCrud.txtTabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                llenarCampos(e);
            }
        });
    }

    public void iniciar() {
        vistaCrud.setTitle("CRUD Clientes");
        vistaCrud.setLocationRelativeTo(null);
        llenarTabla();
    }

    public void llenarTabla() {
        List<Cliente> clientes = clienteDao.obtenerClientes();
        DefaultTableModel model = new DefaultTableModel();
        // se configuran los nombres de las columnas de la tabla
        model.addColumn("ID");
        model.addColumn("Nombres");
        model.addColumn("Email");
        model.addColumn("Telefono");
        model.addColumn("Tipo Cliente");
        vistaCrud.txtTabla.setModel(model);
        String[] datos = new String[5];
        // recorremos la lista de clientes para agregarlos a la tabla
        for (Cliente cliente : clientes) {
            // Acceder a cada atributo del cliente
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nombre: " + cliente.getNombre());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("Teléfono: " + cliente.getTelefono());
            System.out.println("Tipo de Cliente: " + cliente.getTipo_cliente());
            System.out.println("------------------------");
            datos[0] = String.valueOf(cliente.getId());
            datos[1] = cliente.getNombre();
            datos[2] = cliente.getEmail();
            datos[3] = cliente.getTelefono();
            datos[4] = String.valueOf(cliente.getTipo_cliente().getId());
            model.addRow(datos);
        }

    }

    public void llenarCampos(MouseEvent e) {
        JTable tabla = (JTable) e.getSource();
        vistaCrud.txtNombres.setText(vistaCrud.txtTabla.getModel().getValueAt(tabla.getSelectedRow(), 1).toString());
        vistaCrud.txtEmail.setText(vistaCrud.txtTabla.getModel().getValueAt(tabla.getSelectedRow(), 2).toString());
        vistaCrud.txtTelefono.setText(vistaCrud.txtTabla.getModel().getValueAt(tabla.getSelectedRow(), 3).toString());
        //vistaCrud.cmbTipo.removeAllItems();
        /*
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        vistaCrud.cmbTipo.setModel(model);
        tipoCliente = tipoClienteDao.obtenerTipoClientePorId( Integer.parseInt( vistaCrud.txtTabla.getModel().getValueAt(tabla.getSelectedRow(), 4).toString()));
        model.addElement(tipoCliente);
         */
        vistaCrud.cmbTipo.setSelectedIndex(Integer.parseInt(vistaCrud.txtTabla.getModel().getValueAt(tabla.getSelectedRow(), 4).toString()) - 1);
    }

    public void llenarCombo() {
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // programacion del boton Agregar
        if (e.getSource() == vistaCrud.btnAgregar) {

            cliente.setNombre(vistaCrud.txtNombres.getText());
            cliente.setEmail(vistaCrud.txtEmail.getText());
            cliente.setTelefono(vistaCrud.txtTelefono.getText());
            cliente.setTipo_cliente((TipoCliente) vistaCrud.cmbTipo.getSelectedItem());
            //cliente.setTipo_cliente(vistaCrud.cmbTipo);
            if (clienteDao.insertar(cliente)) {
                JOptionPane.showMessageDialog(null, "Cliente Agregado!!");
                limpiar();
                llenarTabla();
                llenarCombo();
            } else {
                JOptionPane.showMessageDialog(null, "Error al agregar!");
            }
        }
        if (e.getSource() == vistaCrud.btnLimpiar) {
            System.out.println("limpiar");
            TipoCliente tipoCliente2 = tipoClienteDao.obtenerTipoClientePorId(2);
            System.out.println(tipoCliente2.getDescripcion());
        }

    }

    public void limpiar() {
        vistaCrud.txtNombres.setText(null);
        vistaCrud.txtEmail.setText(null);
        vistaCrud.txtTelefono.setText(null);
        vistaCrud.txtBuscar.setText(null);
    }

}
