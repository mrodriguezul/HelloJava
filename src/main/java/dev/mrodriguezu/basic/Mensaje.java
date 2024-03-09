package dev.mrodriguezu.basic;

public class Mensaje {

    public Mensaje() {
    }

    public String lanzarMensaje(String mensaje){
        return "Un saludo especial para "+mensaje;
    }

    public String lanzarAlarma(String mensaje) {
        return "Alarma !!! correo "+mensaje;
    }
}
