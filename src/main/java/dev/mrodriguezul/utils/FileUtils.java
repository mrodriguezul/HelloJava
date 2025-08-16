package dev.mrodriguezul.utils;

import dev.mrodriguezul.entity.Pelicula;
import dev.mrodriguezul.enums.GeneroPelicula;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtils {

    public static void savePelicula(String nombreArchivo, String separador, Pelicula pelicula) {
        String linea = String.join(separador,
                pelicula.getTitulo(),
                String.valueOf(pelicula.getDuracion()),
                pelicula.getGenero().name(),
                String.valueOf(pelicula.getCalificacion()),
                pelicula.getFechaEstreno().toString()
        );

        try {
            Files.writeString(Paths.get(nombreArchivo),
                    linea + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Error al escribir en archivo. " + e.getMessage());
        }
    }

    public static List<Pelicula> getPeliculasFromFile(String nombreArchivo, String separador) {
        List<Pelicula> listaPeliculas = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));

            lineas.forEach(linea -> {
                String[] datos = linea.split(Pattern.quote(separador));
                if (datos.length == 5) {
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    GeneroPelicula genero = GeneroPelicula.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);

                    Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion);
                    pelicula.setFechaEstreno(fechaEstreno);

                    listaPeliculas.add(pelicula);
                }
            });
        } catch (IOException e) {
            System.out.println("Error al leer archivo: "+nombreArchivo+". "+ e.getMessage());
        }
        return listaPeliculas;
    }
}