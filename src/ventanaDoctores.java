import DAO.DoctoresCRUD;
import entidades.Doctor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;

public class ventanaDoctores extends JFrame {
    private JTextField txtID;
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JTextField txtEspecialidad;
    private JButton btnBuscar;
    private JButton btnAgregar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    JPanel miPanel;


    public ventanaDoctores() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //buscar doctor
                DoctoresCRUD crud = new DoctoresCRUD();
                String id = txtID.getText();
                Doctor a;
                a = crud.buscarDoctorPorId(id);
                if(a == null){
                    //JOptionPane.showMessageDialog(miPanel,"No se encuentra el paciente con el ID: " + matricula,"Alumnos",JOptionPane.ERROR_MESSAGE);
                    int respuesta = JOptionPane.showConfirmDialog(miPanel,"No se encuentra el paciente con el ID: " + id + "\n¿Desea dar de alta?" ,"Paciente",JOptionPane.YES_NO_OPTION);
                    if(respuesta == 0){
                        //sí quiere dar de alta el doctor inexistente
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
                //instanciar objeto de la clase Doctor
                Doctor a = new Doctor();
                a.setId(txtID.getText());
                a.setNombre(txtNombre.getText());
                a.setApPaterno(txtApellidoPaterno.getText());
                a.setApMaterno(txtApellidoMaterno.getText());
                a.setEspecialidad(txtEspecialidad.getText());

                //invocar metodo de insertarDoctor
                DoctoresCRUD crud = new DoctoresCRUD();
                crud.insertarDoctor(a);
            }
        });
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                actualizarDoctor();
            }
        });
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                eliminarDoctor();
            }
        });
    }
    private void actualizarDoctor() {
        // Obtener el ID del Doctor a actualizar
        String id = txtID.getText();

        // Verificar si el ID está vacío
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Por favor, ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener los datos del Doctor actualizados desde los campos de texto
        String nombre = txtNombre.getText();
        String apPaterno = txtApellidoPaterno.getText();
        String apMaterno = txtApellidoMaterno.getText();

        // Verificar si algún campo requerido está vacío
        if (nombre.isEmpty() || apPaterno.isEmpty()) {
            JOptionPane.showMessageDialog(miPanel, "Por favor, complete todos los campos requeridos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear un objeto Doctor con los datos actualizados
        Doctor doctorActualizado = new Doctor();
        doctorActualizado.setId(id);
        doctorActualizado.setNombre(nombre);
        doctorActualizado.setApPaterno(apPaterno);
        doctorActualizado.setApMaterno(apMaterno);

        // Invocar el método actualizarDoctor del CRUD
        DoctoresCRUD crud = new DoctoresCRUD();
        crud.actualizarDoctor(doctorActualizado);

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
    private void eliminarDoctor() {
        DoctoresCRUD crud = new DoctoresCRUD();
        String id = txtID.getText();

        int respuesta = JOptionPane.showConfirmDialog(miPanel,
                "¿Seguro que desea eliminar al paciente con el ID: " + id + "?",
                "Eliminar Doctor", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {
            try {
                crud.eliminarDoctor(id);
                limpiarCampos();
                JOptionPane.showMessageDialog(miPanel, "Doctor eliminado correctamente");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(miPanel, "Error al eliminar Doctor: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private Doctor obtenerDatosDoctor(String id) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaDoctor.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Doctor> otraLista = (ArrayList<Doctor>) o;

            for (Doctor doctor : otraLista) {
                if (doctor.getId().equals(id)) {
                    miStream2.close(); // Cerrar el flujo de lectura
                    return doctor;
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
                new ventanaDoctores().setVisible(true);
            }
        });
        ventanaDoctores vD = new ventanaDoctores();
        vD.setContentPane(vD.miPanel);
        vD.setSize(500,500);
        vD.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vD.setVisible(true);
    }
}
