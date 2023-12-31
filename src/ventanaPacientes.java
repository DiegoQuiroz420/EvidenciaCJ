import DAO.PacientesCRUD;
import entidades.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

public class ventanaPacientes extends JFrame {
    private JLabel lblID;
    private JLabel lblNombre;
    private JLabel lblApellidoPaterno;
    private JLabel lblApellidoMaterno;
    private JLabel lblFechaNacimiento;
    private JLabel lblSexo;
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JComboBox cmbSexo;
    private JTextField txtFechaNacimiento;
    private JButton btnEliminar;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnBuscar;
    JPanel miPanel;

    public ventanaPacientes() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    //buscar paciente
                    PacientesCRUD crud = new PacientesCRUD();
                    String id = txtID.getText();
                    Paciente a = crud.buscarPacientePorId(id);
                    if(a == null){
                        //JOptionPane.showMessageDialog(miPanel,"No se encuentra el paciente con el ID: " + matricula,"Alumnos",JOptionPane.ERROR_MESSAGE);
                        int respuesta = JOptionPane.showConfirmDialog(miPanel,"No se encuentra el paciente con el ID: " + id + "\n¿Desea dar de alta?" ,"Paciente",JOptionPane.YES_NO_OPTION);
                        if(respuesta == 0){
                            //sí quiere dar de alta el paciente inexistente
                            btnAgregar.setEnabled(true);
                            txtNombre.requestFocus();
                        }else if(respuesta == 1){

                        }
                    }
                    else{
                        txtNombre.setText(a.getNombre());
                        txtApellidoPaterno.setText(a.getApPaterno());
                        txtApellidoMaterno.setText(a.getApMaterno());
                    }
                }
            }
        });
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los datos del formulario
                String id = txtID.getText();
                String nombre = txtNombre.getText();
                String apPaterno = txtApellidoPaterno.getText();

                // Validar que los campos requeridos no estén en blanco
                if (id.isEmpty() || nombre.isEmpty() || apPaterno.isEmpty()) {
                    JOptionPane.showMessageDialog(miPanel, "Por favor, complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Detener la operación si hay campos en blanco
                }
                //instanciar objeto de la clase Paciente
                Paciente a = new Paciente();
                a.setId(txtID.getText());
                a.setNombre(txtNombre.getText());
                a.setApPaterno(txtApellidoPaterno.getText());
                a.setApMaterno(txtApellidoMaterno.getText());
                a.setFechaNacimiento(new Date());

                //invocar metodo de insertarPaciente
                PacientesCRUD crud = new PacientesCRUD();
                crud.insertarPaciente(a);
            }
        });

        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPaciente();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPaciente();
            }
        });
    }

    private void actualizarPaciente() {
        // Obtener el ID del paciente a actualizar
        String id = txtID.getText();

        // Verificar si el ID está vacío
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Por favor, ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos del paciente actualizados desde los campos de texto
        String nombre = txtNombre.getText();
        String apPaterno = txtApellidoPaterno.getText();
        String apMaterno = txtApellidoMaterno.getText();

        // Verificar si algún campo requerido está vacío
        if (nombre.isEmpty() || apPaterno.isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Por favor, complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Paciente con los datos actualizados
        Paciente pacienteActualizado = new Paciente();
        pacienteActualizado.setId(id);
        pacienteActualizado.setNombre(nombre);
        pacienteActualizado.setApPaterno(apPaterno);
        pacienteActualizado.setApMaterno(apMaterno);

        // Invocar el método actualizarPaciente del CRUD
        PacientesCRUD crud = new PacientesCRUD();
        crud.actualizarPaciente(pacienteActualizado);

        // Limpiar los campos después de la actualización
        limpiarCampos();

        JOptionPane.showMessageDialog(miPanel, "Paciente actualizado correctamente");
    }

    private void limpiarCampos() {
        // Limpia los campos de texto y realiza otras tareas de limpieza si es necesario
        txtID.setText("");
        txtNombre.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        // Limpiar otros campos si es necesario
    }
    private void eliminarPaciente() {
        PacientesCRUD crud = new PacientesCRUD();
        String id = txtID.getText();

        int respuesta = JOptionPane.showConfirmDialog(miPanel,
                "¿Seguro que desea eliminar al paciente con el ID: " + id + "?",
                "Eliminar Paciente", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                crud.eliminarPaciente(id);
                limpiarCampos();
                JOptionPane.showMessageDialog(miPanel, "Paciente eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(miPanel, "Error al eliminar paciente: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private Paciente obtenerDatosPaciente(String id) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaPaciente.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>) o;

            for (Paciente paciente : otraLista) {
                if (paciente.getId().equals(id)) {
                    miStream2.close(); // Cerrar el flujo de lectura
                    return paciente;
                }
            }
            // Saliendo del bucle
            miStream2.close(); // Cerrar el flujo de lectura
            return null;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ventanaPacientes().setVisible(true);
            }
        });
        ventanaPacientes v = new ventanaPacientes();
        v.setContentPane(v.miPanel);
        v.setSize(500,500);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
