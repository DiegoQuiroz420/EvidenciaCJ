package DAO;

import entidades.Paciente;

import java.io.*;
import java.util.ArrayList;

public class PacientesCRUD {
    public void insertarPaciente(Paciente a){
        try{
            FileInputStream leer =
                    new FileInputStream("\\listaPaciente.txt");
            ObjectInputStream miStream2 = new ObjectInputStream(leer);
            Object o = miStream2.readObject();
            ArrayList<Paciente> otraLista = (ArrayList<Paciente>)o;
            otraLista.add(a);

            //escribir de vuelta al archivo
            FileOutputStream escribir =
                    new FileOutputStream("\\listaPaciente.txt");
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
    public Paciente buscarPacientePorMat(String id){
        //obtener lista de pacientes desde Archivo
        try{
            FileInputStream leer =
                    new FileInputStream("\\listaPaciente.txt");
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
}
