package dev.mrodriguezul.estdatos;

public class Arreglos {
    public static void main(String[] args) {
        String [] productos = {"prod1","prod2","prod3"};
        double [] precios = new double[3];
        String [][] dias = {{"Monday","MON"},{"Tusday","TUS"}};

        System.out.println(productos);
        System.out.println(precios);

        for (String[] lstdias : dias){
            for (String dia : lstdias){
                System.out.println(dia);
            }
        }
    }
}
