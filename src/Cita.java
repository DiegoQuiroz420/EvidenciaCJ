import entidades.Doctor;
import entidades.Paciente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cita extends  JFrame {
    private JPanel miPanel;
    private JComboBox cmbPacientes;
    private JTextArea txtMotivo;
    private JTextField txtFecha;
    private JButton btnCita;
    private JButton bntVisualizar;
    private JComboBox cmbPaciente;
    private JComboBox cmbDoctor;
    private JTextField txtPaciente;
    private JComboBox cmbDia;
    private JComboBox cmbMes;
    private JComboBox cmbAno;
    private JTextField txtDoctor;
    private String idPaciente;
    ArrayList<Paciente> otraLista;
    ArrayList<Doctor> otraListaDoctor;
    ArrayList<Cita> listaCitas = new ArrayList<>();


    private String obtenerCitasTexto() {
        StringBuilder citasTexto = new StringBuilder();

        // Itera sobre la lista de citas y agrega la información al StringBuilder
        for (Cita cita : listaCitas) {
            String nombrePaciente = obtenerNombrePaciente(entidades.Cita.getIdPaciente());
            String nombreDoctor = obtenerNombreDoctor(entidades.Cita.getIdDoctor());

            citasTexto.append("Fecha: ").append(entidades.Cita.getFecha()).append("\n");
            citasTexto.append("Paciente: ").append(nombrePaciente).append("\n");
            citasTexto.append("Doctor: ").append(nombreDoctor).append("\n");
            citasTexto.append("Motivo: ").append(entidades.Cita.getMotivo()).append("\n\n");
        }

        return citasTexto.toString();
    }
    private String obtenerNombrePaciente(String idPaciente) {
        // Implementa lógica para obtener el nombre del paciente
        for (Paciente p : otraLista) {
            if (p.getId().equals(idPaciente)) {
                return p.getNombre() + " " + p.getApPaterno();
            }
        }
        return "";
    }
    private String obtenerNombreDoctor(String idDoctor) {
        // Implementa lógica para obtener el nombre del doctor
        for (Doctor d : otraListaDoctor) {
            if (d.getId().equals(idDoctor)) {
                return d.getNombre() + " " + d.getApPaterno();
            }
        }
        return "";
    }
    public boolean validarFecha(String fecha){
        SimpleDateFormat formatoFecha =
                new SimpleDateFormat("dd/MM/yyyy");
        try{
            formatoFecha.setLenient(false);
            Date miFecha = formatoFecha.parse(fecha);
            System.out.print(miFecha);
        }catch (Exception e){
            return false;
        }
        return true;
    }
    public Cita(){
        try{
            FileInputStream leer =
                    new FileInputStream("C:\\temp\\listaPaciente.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            otraLista = (ArrayList<Paciente>)o;
            for (Paciente p: otraLista){
                cmbPaciente.addItem(p.getId());
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        cmbPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(miPanel,cmbPaciente.getSelectedItem());
                String nombreCompleto = " ";
                String idP = cmbPaciente.getSelectedItem().toString();
                for(Paciente p: otraLista){
                    if(p.getId().equals(idP)){
                        nombreCompleto = p.getNombre() + " " + p.getApPaterno();
                        break;
                    }
                }
                txtPaciente.setText(nombreCompleto);
            }
        });
        cmbDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreCompleto = " ";
                String idD = cmbDoctor.getSelectedItem().toString();
                for(Doctor d: otraLista){
                    if(d.getId().equals(idD)){
                        nombreCompleto = d.getNombre() + " " + d.getApPaterno();
                        break;
                    }
                }
                txtDoctor.setText(nombreCompleto);
            }
        });
        btnCita.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fecha = cmbDia.getSelectedItem().toString() + "/" + cmbMes.getSelectedItem().toString() + "/" + cmbAno.getSelectedItem().toString();
                boolean resultado = validarFecha(fecha);
                if(resultado==true){
                    JOptionPane.showMessageDialog(miPanel, "Cita agregada.");
                }else{
                    JOptionPane.showMessageDialog(miPanel, "Error al ingresar fecha.");

                }
            }
        });
        bntVisualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String citasTexto = obtenerCitasTexto(); // Método que devuelve el texto de las citas

                if (!citasTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(miPanel, citasTexto, "Citas", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(miPanel, "No hay citas para mostrar", "Citas", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    public static void main(String[] args) {
        Cita c = new Cita();
        c.setContentPane(c.miPanel);
        c.setSize(500,500);
        c.setTitle("Citas");
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setVisible(true);
    }
}
