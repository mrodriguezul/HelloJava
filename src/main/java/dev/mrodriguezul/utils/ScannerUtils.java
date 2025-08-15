package dev.mrodriguezul.utils;

import dev.mrodriguezul.enums.GeneroPelicula;

import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String readString(String message) {
        System.out.print(message+ ":");
        return SCANNER.nextLine();
    }

    public static int readInt(String message) {
        System.out.print(message+ ":");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Dato no aceptado. " + message + ": ");
            SCANNER.next();
        }
        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double readDouble(String message) {
        System.out.print(message+ ":");

        while (!SCANNER.hasNextDouble()) {
            System.out.println("Dato no aceptado. " + message + ": ");
            SCANNER.next();
        }
        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static GeneroPelicula readGeneroPelicula(String message) {
        System.out.print(message + ":" + "\nOpciones:");
        for (GeneroPelicula genero : GeneroPelicula.values()) {
            System.out.print(" * " + genero.name());
        }
        System.out.println(" : ");
        String input = SCANNER.nextLine().toUpperCase();

        try {
            return GeneroPelicula.valueOf(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Género de película no válido. Por favor, intente nuevamente.");
            return readGeneroPelicula(message);
        }
    }

}
