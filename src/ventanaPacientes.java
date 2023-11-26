import DAO.PacientesCRUD;
import entidades.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton btnGuardar;
    private JButton btnBuscar;
    private JPanel miPanel;

    public ventanaPacientes() {
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                {
                    //buscar paciente
                    PacientesCRUD crud = new PacientesCRUD();
                    String id = txtID.getText();
                    Paciente a = crud.buscarPacientePorMat(id);
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
    }

    public static void main(String[] args) {
        ventanaPacientes v = new ventanaPacientes();
        v.setContentPane(v.miPanel);
        v.setSize(500,500);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(true);
    }
}
