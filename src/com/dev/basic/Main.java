package src.com.dev.basic;

public class Main {
    public static void main(String[] args) {
        System.out.println("hey!");

        Mensaje mensaje = new Mensaje();
        System.out.println(mensaje.lanzarMensaje("Bebito fiu fiu"));

        System.out.println(mensaje.lanzarAlarma("bebito!"));

        Persona persona = new Persona("Miguel", "Rodríguez");
        System.out.println(mensaje.lanzarMensaje(String.valueOf(persona)));

    }
}
