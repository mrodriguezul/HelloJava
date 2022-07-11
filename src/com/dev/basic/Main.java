package src.com.dev.basic;

public class Main {
    public static void main(String[] args) {
        System.out.println("hey!");

        Mensaje mensaje = new Mensaje();
        System.out.println(mensaje.lanzarMensaje("Bebito fiu fiu"));

        System.out.println(mensaje.lanzarAlarma("bebito!"));

        // esta es una nueva línea en el tiempo
        System.out.println(mensaje.lanzarAlarma("Multiverso!"));
        //System.out.println(mensaje.lanzarAlarma("Multiverso2!"));

        // esta línea de código agregar en la base, el 2 ya no existe
        System.out.println(mensaje.lanzarAlarma("Multiverso3!"));
    }
}
