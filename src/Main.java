import entidades.Paciente;
import entidades.Doctor;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        //Paciente
        ArrayList<Paciente> lista = new ArrayList<Paciente>();
        Paciente a = new Paciente();
        a.setId("0000");
        a.setNombre("Andres");
        a.setApPaterno("López");
        a.setApMaterno("Pérez");

        Date fecha=new Date();
        a.setFechaNacimiento(fecha);
        lista.add(a);
        try{
            FileOutputStream escribir=
                    new FileOutputStream("C:\\temp\\listaPaciente.txt");
            ObjectOutputStream miStream =
                    new ObjectOutputStream(escribir);
            miStream.writeObject(lista);
            miStream.close();
            //LEER lista desde archivo
            FileInputStream leer =
                    new FileInputStream("C:\\temp\\listaPaciente.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>)o;
            Paciente pruebaPaciente = otraLista.get(0);
            System.out.println(pruebaPaciente.getNombre());
            miStream2.close();
        }catch(FileNotFoundException e){
            System.out.println("Archivo no encontrado.");
        }catch(IOException e){
            System.out.println("Error de E/S");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println("Error al leer lista de clase Paciente");
        }

        //Doctor
        ArrayList<Doctor> listaDoctor = new ArrayList<>();
        Doctor doctor = new Doctor();
        doctor.setId("0000");
        doctor.setNombre("Juan");
        doctor.setApPaterno("Gomez");
        doctor.setApMaterno("Perez");
        doctor.setEspecialidad("General");

        try (ObjectOutputStream miStream = new ObjectOutputStream(new FileOutputStream("C:\\temp\\listaDoctor.txt"))) {
            listaDoctor.add(doctor);
            miStream.writeObject(listaDoctor);
        } catch (IOException e) {
            System.out.println("Error de E/S");
            e.printStackTrace();
        }

        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaDoctor.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Doctor> otraListaDoctor = (ArrayList<Doctor>) o;
            Doctor pruebaDoctor = otraListaDoctor.get(0);
            System.out.println(pruebaDoctor.getNombre());
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer lista de clase Doctor");
            e.printStackTrace();
        }
        // Después de cargar pacientes y doctores, abre la ventana de login
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                abrirVentana();
            }
        });
    }
        private static void abrirVentana() {
            login vLogin = new login();
            vLogin.setContentPane(vLogin.miPanel);
            vLogin.setSize(300, 300);
            vLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vLogin.setVisible(true);
    }
}