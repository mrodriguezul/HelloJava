package dev.mrodriguezul.practice.streaming;

import dev.mrodriguezul.entity.Pelicula;
import dev.mrodriguezul.entity.PlataformaStreaming;
import dev.mrodriguezul.enums.GeneroPelicula;
import dev.mrodriguezul.excepcion.PeliculaExistenteException;
import dev.mrodriguezul.utils.FileUtils;
import dev.mrodriguezul.utils.ScannerUtils;

import java.util.List;

public class AppMain {
    public static final String NOMBRE_PLATAFORMA = "HBO MAX";
    public static final String VERSION = "1.0.0";

    public static final String CONTEXTO_STREAMING = "src/main/java/dev/mrodriguezul/practice/streaming/";
    public static final String NOMBRE_ARCHIVO_BD_INICIAL = "peliculas.db";
    public static final String SEPARADOR_BD_INICIAL = "|";

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int REPRODUCIR = 6;
    public static final int MAS_VISTO = 7;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {
        PlataformaStreaming plataforma = new PlataformaStreaming(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Disfruta de " + plataforma.getDuracionTotal() + " minutos de contenido! \n");

        while (true) {
            int opcion = ScannerUtils.readInt(
                "\nIngrese una de las siguientes opciones:\n" +
                "1. Agregar contenido\n" +
                "2. Mostrar todo\n" +
                "3. Buscar por titulo\n" +
                "4. Buscar por genero\n" +
                "5. Ver populares\n" +
                "6. Reproducir\n" +
                "7. Mas Visto\n" +
                "8. Eliminar\n" +
                "9. Salir\n"
            );

            switch (opcion) {
                case AGREGAR:
                    String nombre = ScannerUtils.readString("Nombre del contenido");
                    GeneroPelicula genero = ScannerUtils.readGeneroPelicula("Genero del contenido");
                    int duracion = ScannerUtils.readInt("Duracion del contenido");
                    double calificacion = ScannerUtils.readDouble("Calificacion del contenido");

                    try {
                        plataforma.add(new Pelicula(nombre, duracion, genero, calificacion));
                        System.out.println(nombre + " agregado! ✅");
                    }catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case MOSTRAR_TODO:
                    List<String> titulos = plataforma.getTitulos();
                    titulos.forEach(System.out::println);
                    break;
                case BUSCAR_POR_TITULO:
                    String nombreBuscado = ScannerUtils.readString("Nombre del contenido a buscar");
                    Pelicula contenido = plataforma.buscarPorTitulo(nombreBuscado);

                    if (contenido != null) {
                        System.out.println(contenido.getDetallesTecnicos());
                    } else {
                        System.out.println(nombreBuscado + " no existe dentro de " + plataforma.getNombre());
                    }
                    break;
                case BUSCAR_POR_GENERO:
                    GeneroPelicula generoBuscado = ScannerUtils.readGeneroPelicula("Genero del contenido a buscar");

                    List<Pelicula> peliculasPorGenero = plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(peliculasPorGenero.size() + " encontrados para el genero " + generoBuscado);
                    peliculasPorGenero.forEach(pelicula -> System.out.println(pelicula.getDetallesTecnicos()));
                    break;
                case VER_POPULARES:
                    int cantidad = ScannerUtils.readInt("Cantidad de resultados a mostrar");

                    List<Pelicula> peliculasPopulares = plataforma.getPopulares(cantidad);
                    peliculasPopulares.forEach(pelicula -> System.out.println(pelicula.getDetallesTecnicos()));
                    break;
                case REPRODUCIR:
                    String nombreReproducir = ScannerUtils.readString("Nombre de la película a reproducir");
                    Pelicula peliculaReproducir = plataforma.buscarPorTitulo(nombreReproducir);

                    if (peliculaReproducir != null) {
                        plataforma.reproducir(peliculaReproducir);
                    } else {
                        System.out.println(nombreReproducir + " no se encuentra en la plataforma.");
                    }
                    break;
                case MAS_VISTO:
                    Pelicula masVisto = plataforma.getMasVisto();
                    if (masVisto != null) {
                        System.out.println("El contenido mas visto es: " + masVisto.getTitulo() + " con " + plataforma.getVisualizaciones().get(masVisto)+ " reproducciones.");
                    } else {
                        System.out.println("No hay contenido reproducido en " + plataforma.getNombre());
                    }
                    break;
                case ELIMINAR:
                    String nombreAEliminar = ScannerUtils.readString("Nombre del contenido a eliminar");
                    Pelicula pelicula = plataforma.buscarPorTitulo(nombreAEliminar);

                    if (pelicula != null) {
                        plataforma.remove(pelicula);
                        System.out.println(nombreAEliminar + " eliminado! ❌");
                    } else {
                        System.out.println(nombreAEliminar + " no existe dentro de " + plataforma.getNombre());
                    }
                    break;
                case SALIR:
                    System.exit(0);
                    break;
            }
        }

    }

    private static void cargarPeliculas(PlataformaStreaming plataforma) {
        plataforma.getContenido().addAll(FileUtils.getPeliculasFromFile(CONTEXTO_STREAMING +NOMBRE_ARCHIVO_BD_INICIAL, SEPARADOR_BD_INICIAL));
    }

}
