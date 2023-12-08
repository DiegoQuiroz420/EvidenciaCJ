package DAO;

import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;

public class PacientesCRUD {
    public void insertarPaciente(Paciente a){
        try{
            FileInputStream leer =
                    new FileInputStream("C:\\temp\\listaPaciente.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>)o;
            otraLista.add(a);

            //escribir de vuelta al archivo
            FileOutputStream escribir =
                    new FileOutputStream("C:\\temp\\listaPaciente.txt");
            ObjectOutputStream miStream = new ObjectOutputStream(escribir);
            miStream.writeObject(otraLista);
            miStream.close();
            miStream2.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public Paciente buscarPacientePorId(String id){
        //obtener lista de pacientes desde Archivo
        try{
            FileInputStream leer =
                    new FileInputStream("C:\\temp\\listaPaciente.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>)o;
            for(Paciente a: otraLista){
                if(a.getId().equals(id)){
                    return a;
                }
            }
            //saliendo del for
            miStream2.close();
            return null;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private Paciente obtenerDatosPaciente(String id) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaPaciente.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>) o;

            for (Paciente paciente : otraLista) {
                if (paciente.getId().equals(id)) {
                    return paciente;
                }
            }
            // Saliendo del bucle
            return null;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarPaciente(String id) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaPaciente.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Paciente> listaPacientes = (ArrayList<Paciente>) o;

            // Crear una nueva lista sin el paciente a eliminar
            ArrayList<Paciente> nuevaLista = new ArrayList<>();
            for (Paciente paciente : listaPacientes) {
                if (!paciente.getId().equals(id)) {
                    nuevaLista.add(paciente);
                }
            }

            // Escribir la nueva lista al archivo
            try (ObjectOutputStream miStream = new ObjectOutputStream(new FileOutputStream("C:\\temp\\listaPaciente.txt"))) {
                miStream.writeObject(nuevaLista);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarPaciente(Paciente paciente) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaPaciente.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Paciente> listaPacientes = (ArrayList<Paciente>) o;

            // Buscar el paciente a actualizar por ID
            for (int i = 0; i < listaPacientes.size(); i++) {
                if (listaPacientes.get(i).getId().equals(paciente.getId())) {
                    listaPacientes.set(i, paciente);
                    break;
                }
            }

            // Escribir la lista actualizada al archivo
            try (ObjectOutputStream miStream = new ObjectOutputStream(new FileOutputStream("C:\\temp\\listaPaciente.txt"))) {
                miStream.writeObject(listaPacientes);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
