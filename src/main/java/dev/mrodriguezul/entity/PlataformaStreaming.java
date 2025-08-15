package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroPelicula;
import dev.mrodriguezul.excepcion.PeliculaExistenteException;

import java.util.*;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    private String nombre;
    private List<Pelicula> contenido;
    private Map<Pelicula, Integer> visualizaciones;

    public PlataformaStreaming(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<Pelicula>();
        this.visualizaciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

    public Map<Pelicula, Integer> getVisualizaciones() {
        return visualizaciones;
    }

    public void add(Pelicula elemento) {
        Pelicula peliculaExistente = buscarPorTitulo(elemento.getTitulo());
        if (peliculaExistente != null) {
            throw new PeliculaExistenteException(elemento.getTitulo());
        }
        this.contenido.add(elemento);
    }

    public void reproducir(Pelicula contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " se ha reproducido " + conteoActual + " veces.");

        this.contarVisualizacion(contenido);
        contenido.play();
    }

    private void contarVisualizacion(Pelicula contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }

    public void remove(Pelicula elemento) {
        this.contenido.remove(elemento);
    }

    public List<String> getTitulos() {
        return contenido.stream()
                .map(Pelicula::getTitulo)
                .collect(Collectors.toList());
    }

    public Pelicula buscarPorTitulo(String titulo) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().contains(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(GeneroPelicula genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Pelicula> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Pelicula::getCalificacion).reversed())
                .limit(cantidad)
                .collect(Collectors.toList());
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();
    }

    public Pelicula getMasVisto() {
        return visualizaciones.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}
