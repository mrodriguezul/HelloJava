package dev.mrodriguezul.practice.streaming;

import dev.mrodriguezul.entity.Pelicula;
import dev.mrodriguezul.entity.PlataformaStreaming;
import dev.mrodriguezul.enums.GeneroPelicula;
import dev.mrodriguezul.excepcion.PeliculaExistenteException;
import dev.mrodriguezul.utils.ScannerUtils;

import java.util.List;

public class AppMain {
    public static final String NOMBRE_PLATAFORMA = "HBO MAX";
    public static final String VERSION = "1.0.0";

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
        plataforma.add(new Pelicula("Shrek", 90, GeneroPelicula.ANIMADA));
        plataforma.add(new Pelicula("Inception", 148, GeneroPelicula.FANTASIA));
        plataforma.add(new Pelicula("Titanic", 195, GeneroPelicula.DRAMA, 4.6));
        plataforma.add(new Pelicula("John Wick", 101, GeneroPelicula.ACCION));
        plataforma.add(new Pelicula("El Conjuro", 112, GeneroPelicula.TERROR, 3.0));
        plataforma.add(new Pelicula("Coco", 105, GeneroPelicula.ANIMADA, 4.7));
        plataforma.add(new Pelicula("Interstellar", 169, GeneroPelicula.FANTASIA, 5));
        plataforma.add(new Pelicula("Joker", 122, GeneroPelicula.DRAMA));
        plataforma.add(new Pelicula("Toy Story", 81, GeneroPelicula.ANIMADA, 4.5));
        plataforma.add(new Pelicula("Avengers: Endgame", 181, GeneroPelicula.ACCION, 3.9));
        plataforma.add(new Pelicula("The Matrix", 136, GeneroPelicula.FANTASIA, 4.8));
        plataforma.add(new Pelicula("Parasite", 132, GeneroPelicula.DRAMA, 4.9));
        plataforma.add(new Pelicula("The Godfather", 175, GeneroPelicula.DRAMA, 4.8));
        plataforma.add(new Pelicula("The Dark Knight", 152, GeneroPelicula.ACCION, 4.7));
        plataforma.add(new Pelicula("Forrest Gump", 142, GeneroPelicula.DRAMA, 4.6));
        plataforma.add(new Pelicula("Gladiator", 155, GeneroPelicula.ACCION, 4.5));
        plataforma.add(new Pelicula("The Shawshank Redemption", 142, GeneroPelicula.DRAMA, 4.9));
        plataforma.add(new Pelicula("Pulp Fiction", 154, GeneroPelicula.FANTASIA, 4.6));
        plataforma.add(new Pelicula("The Lord of the Rings: The Return of the King", 201, GeneroPelicula.FANTASIA, 4.8));
        plataforma.add(new Pelicula("The Silence of the Lambs", 118, GeneroPelicula.MISTERIO, 4.7));
        plataforma.add(new Pelicula("The Social Network", 120, GeneroPelicula.DRAMA, 4.5));
        plataforma.add(new Pelicula("The Departed", 151, GeneroPelicula.DRAMA, 4.6));
        plataforma.add(new Pelicula("The Prestige", 130, GeneroPelicula.MISTERIO, 4.7));
        plataforma.add(new Pelicula("Fight Club", 139, GeneroPelicula.DRAMA, 4.8));
        plataforma.add(new Pelicula("The Lion King", 88, GeneroPelicula.ANIMADA, 4.9));
        plataforma.add(new Pelicula("Finding Nemo", 100, GeneroPelicula.ANIMADA, 4.6));
        plataforma.add(new Pelicula("The Incredibles", 115, GeneroPelicula.ANIMADA, 4.7));
        plataforma.add(new Pelicula("Up", 96, GeneroPelicula.ANIMADA, 4.8));
        plataforma.add(new Pelicula("Zootopia", 108, GeneroPelicula.ANIMADA, 4.5));
        plataforma.add(new Pelicula("Spider", 117, GeneroPelicula.ANIMADA, 4.6));
        plataforma.add(new Pelicula("Inside Out", 95, GeneroPelicula.ANIMADA, 4.7));
        plataforma.add(new Pelicula("WALL-E", 98, GeneroPelicula.ANIMADA, 4.8));
        plataforma.add(new Pelicula("Ratatouille", 111, GeneroPelicula.ANIMADA, 4.6));
        plataforma.add(new Pelicula("El Aro", 115, GeneroPelicula.TERROR, 3.5));
        plataforma.add(new Pelicula("El Exorcista", 122, GeneroPelicula.TERROR, 4.0));
        plataforma.add(new Pelicula("El Resplandor", 146, GeneroPelicula.TERROR, 4.2));
        plataforma.add(new Pelicula("El Orfanato", 105, GeneroPelicula.TERROR, 3.8));
        plataforma.add(new Pelicula("El Conjuro 2", 134, GeneroPelicula.TERROR, 3.9));
        plataforma.add(new Pelicula("Mr. Bean", 88, GeneroPelicula.COMEDIA, 4.5));
        plataforma.add(new Pelicula("La Vida es Bella", 116, GeneroPelicula.COMEDIA, 4.8));
        plataforma.add(new Pelicula("Superbad", 113, GeneroPelicula.COMEDIA, 4.6));
        plataforma.add(new Pelicula("The Hangover", 100, GeneroPelicula.COMEDIA, 4.7));
        plataforma.add(new Pelicula("Groundhog Day", 101, GeneroPelicula.COMEDIA, 4.5));
    }

}
