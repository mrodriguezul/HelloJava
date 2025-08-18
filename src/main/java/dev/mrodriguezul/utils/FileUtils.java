package dev.mrodriguezul.utils;

import dev.mrodriguezul.entity.Contenido;
import dev.mrodriguezul.enums.GeneroContenido;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FileUtils {

    public static void saveContenido(String nombreArchivo, String separador, Contenido contenido) {
        String linea = String.join(separador,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
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

    public static List<Contenido> getContenidoFromFile(String nombreArchivo, String separador) {
        List<Contenido> listaContenidos = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(nombreArchivo));

            lineas.forEach(linea -> {
                String[] datos = linea.split(Pattern.quote(separador));
                if (datos.length == 5) {
                    String titulo = datos[0];
                    int duracion = Integer.parseInt(datos[1]);
                    GeneroContenido genero = GeneroContenido.valueOf(datos[2].toUpperCase());
                    double calificacion = datos[3].isBlank() ? 0 : Double.parseDouble(datos[3]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[4]);

                    Contenido contenido = new Contenido(titulo, duracion, genero, calificacion);
                    contenido.setFechaEstreno(fechaEstreno);

                    listaContenidos.add(contenido);
                }
            });
        } catch (IOException e) {
            System.out.println("Error al leer archivo: "+nombreArchivo+". "+ e.getMessage());
        }
        return listaContenidos;
    }
}