package dev.mrodriguezul.basic;

import dev.mrodriguezul.entity.Mensaje;
import dev.mrodriguezul.entity.Nurse;
import dev.mrodriguezul.entity.Person;

public class Main {
    public static void main(String[] args) {
        System.out.println("hey!");

        Mensaje mensaje = new Mensaje();
        System.out.println(mensaje.lanzarMensaje("Bebito fiu fiu"));

        System.out.println(mensaje.lanzarAlarma("bebito!"));

        Person persona = new Nurse("Nancy", "nancy@gmail.com", "Pediatrics");
        System.out.println(mensaje.lanzarMensaje(String.valueOf(persona)));

    }
}
