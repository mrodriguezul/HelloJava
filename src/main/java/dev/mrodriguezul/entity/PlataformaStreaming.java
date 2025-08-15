package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroPelicula;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PlataformaStreaming {
    private String nombre;
    private List<Pelicula> contenido;

    public PlataformaStreaming(String nombre) {
        this.nombre = nombre;
        contenido = new ArrayList<Pelicula>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

    public void add(Pelicula elemento) {
        this.contenido.add(elemento);
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

}
