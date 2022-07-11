package src.com.dev.basic;

public class Main {
    public static void main(String[] args) {
        System.out.println("hey!");

        Mensaje mensaje = new Mensaje();
        System.out.println(mensaje.lanzarMensaje("Bebito fiu fiu"));

        System.out.println(mensaje.lanzarAlarma("bebito!"));

        System.out.println(mensaje.lanzarAlarma("bebito2!"));
        System.out.println(mensaje.lanzarAlarma("bebito3!"));
        System.out.println(mensaje.lanzarAlarma("bebito4!"));


    }
}
