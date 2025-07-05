package dev.mrodriguezul.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Mensaje {

    Map<Integer, String> map = new HashMap<Integer, String>();
    Map<Integer, String> treeMap = new TreeMap<Integer, String>();

    public Mensaje() {
    }

    public String lanzarMensaje(String mensaje){
        return "Un saludo especial para "+mensaje;
    }

    public String lanzarAlarma(String mensaje) {
        return "Alarma !!! correo "+mensaje;
    }
}
