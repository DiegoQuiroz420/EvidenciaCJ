package entidades;

import java.io.Serializable;

public class Cita implements Serializable {
    private static String idPaciente;
    private static String idDoctor;
    private static String Fecha;
    private static String Motivo;

    public void setIdPaciente(String idPaciente) {
        this.idPaciente = idPaciente;
    }

    public static String getIdPaciente() {
        return idPaciente;
    }
    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public static String getIdDoctor() {
        return idDoctor;
    }
    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public static String getFecha() {
        return Fecha;
    }
    public void setMotivo(String Motivo) {
        this.Motivo = Motivo;
    }

    public static String getMotivo() {
        return Motivo;
    }
}
