package entidades;

import java.io.Serializable;
import java.util.Date;

public class Doctor implements Serializable{
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPaterno() {
        return apPaterno;
    }

    public void setApPaterno(String apPaterno) {
        this.apPaterno = apPaterno;
    }

    public String getApMaterno() {
        return apMaterno;
    }

    public void setApMaterno(String apMaterno) {
        this.apMaterno = apMaterno;
    }

    public String getEspecialidad() {
        return getEspecialidad();
    }

    public void setEspecialidad(String Especialidad) {
        this.Especialidad = Especialidad;
    }

    String nombre;
    String apPaterno;
    String apMaterno;
    String Especialidad;
}
