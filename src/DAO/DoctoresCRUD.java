package DAO;

import entidades.Doctor;

import java.io.*;
import java.util.ArrayList;

public class DoctoresCRUD {
    public void insertarDoctor(Doctor a){
        try{
            FileInputStream leer =
                    new FileInputStream("C:\\temp\\listaDoctor.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Doctor> otraLista = (ArrayList<Doctor>)o;
            otraLista.add(a);

            //escribir de vuelta al archivo
            FileOutputStream escribir =
                    new FileOutputStream("C:\\temp\\listaDoctor.txt");
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
    public Doctor buscarDoctorPorId(String id){
        //obtener lista de Doctores desde Archivo
        try{
            FileInputStream leer =
                    new FileInputStream("C:\\temp\\listaDoctor.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Doctor> otraLista = (ArrayList<Doctor>)o;
            for(Doctor a: otraLista){
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
    private Doctor obtenerDatosDoctor(String id) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaDoctor.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Doctor> otraLista = (ArrayList<Doctor>) o;

            for (Doctor doctor : otraLista) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
            // Saliendo del bucle
            return null;

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void eliminarDoctor(String id) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaDoctor.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Doctor> listaDoctores = (ArrayList<Doctor>) o;

            // Crear una nueva lista sin el Doctor a eliminar
            ArrayList<Doctor> nuevaLista = new ArrayList<>();
            for (Doctor doctor : listaDoctores) {
                if (!doctor.getId().equals(id)) {
                    nuevaLista.add(doctor);
                }
            }

            // Escribir la nueva lista al archivo
            try (ObjectOutputStream miStream = new ObjectOutputStream(new FileOutputStream("C:\\temp\\listaDoctor.txt"))) {
                miStream.writeObject(nuevaLista);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarDoctor(Doctor doctor) {
        try (ObjectInputStream miStream2 = new ObjectInputStream(new FileInputStream("C:\\temp\\listaDoctor.txt"))) {
            Object o = miStream2.readObject();
            ArrayList<Doctor> listaDoctores = (ArrayList<Doctor>) o;

            // Buscar el Doctor a actualizar por ID
            for (int i = 0; i < listaDoctores.size(); i++) {
                if (listaDoctores.get(i).getId().equals(doctor.getId())) {
                    listaDoctores.set(i, doctor);
                    break;
                }
            }

            // Escribir la lista actualizada al archivo
            try (ObjectOutputStream miStream = new ObjectOutputStream(new FileOutputStream("C:\\temp\\listaDoctor.txt"))) {
                miStream.writeObject(listaDoctores);
            }

        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public class InicializarArchivo {

        public static void main(String[] args) {
            // Especifica la ruta del archivo
            String rutaArchivo = "C:\\temp\\listaDoctor.txt";

            // Intenta crear el archivo si no existe
            try {
                File archivo = new File(rutaArchivo);

                // Verifica si el archivo ya existe
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado con éxito.");
                } else {
                    System.out.println("El archivo ya existe.");
                }
            } catch (IOException e) {
                System.out.println("Error al crear el archivo: " + e.getMessage());
            }
        }
    }
}
