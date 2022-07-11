package src.com.dev.basic;

public class Main {
    public static void main(String[] args) {
        System.out.println("hey!");

        Mensaje mensaje = new Mensaje();
        System.out.println(mensaje.lanzarMensaje("Bebito fiu fiu"));

        System.out.println(mensaje.lanzarAlarma("bebito!"));

        // esta es una nueva línea en el tiempo
        System.out.println(mensaje.lanzarAlarma("Multiverso!"));

    }
}
