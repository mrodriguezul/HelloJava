package dev.mrodriguezul.utils;

import dev.mrodriguezul.entity.Contenido;
import dev.mrodriguezul.entity.Documental;
import dev.mrodriguezul.entity.Pelicula;
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

    public static final String CONTENIDO_TIPO_PELICULA = "PELICULA";
    public static final String CONTENIDO_TIPO_DOCUMENTAL = "DOCUMENTAL";

    public static void saveContenido(String nombreArchivo, String separador, Contenido contenido) {
        String linea = String.join(separador,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
        );

        String lineaArchivo;

        if (contenido instanceof Documental) {
            Documental documental = (Documental) contenido;
            lineaArchivo = CONTENIDO_TIPO_DOCUMENTAL + separador + linea + separador + documental.getTema();
        } else {
            lineaArchivo = CONTENIDO_TIPO_PELICULA + separador + linea;
        }

        try {
            Files.writeString(Paths.get(nombreArchivo),
                    lineaArchivo + System.lineSeparator(),
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
                String tipoContenido = datos[0];

                if ((CONTENIDO_TIPO_PELICULA.equals(tipoContenido) && datos.length == 6) || (CONTENIDO_TIPO_DOCUMENTAL.equals(tipoContenido) && datos.length == 7)) {
                    String titulo = datos[1];
                    int duracion = Integer.parseInt(datos[2]);
                    GeneroContenido genero = GeneroContenido.valueOf(datos[3].toUpperCase());
                    double calificacion = datos[4].isBlank() ? 0 : Double.parseDouble(datos[4]);
                    LocalDate fechaEstreno = LocalDate.parse(datos[5]);

                    Contenido contenido;
                    if (CONTENIDO_TIPO_PELICULA.equals(tipoContenido)) {
                        contenido = new Pelicula(titulo, duracion, genero, calificacion);
                    } else {
                        String tema = datos[6];
                        contenido = new Documental(titulo, duracion, genero, calificacion, tema);
                    }
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