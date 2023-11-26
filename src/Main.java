import entidades.Paciente;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

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
                    new FileOutputStream("\\listaPaciente.txt");
            ObjectOutputStream miStream =
                    new ObjectOutputStream(escribir);
            miStream.writeObject(lista);
            miStream.close();
            //LEER lista desde archivo
            FileInputStream leer =
                    new FileInputStream("\\listaPaciente.txt");
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
    }
}