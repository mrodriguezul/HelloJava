package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroContenido;
import dev.mrodriguezul.excepcion.ContenidoExistenteException;
import dev.mrodriguezul.utils.FileUtils;

import java.util.*;
import java.util.stream.Collectors;

import static dev.mrodriguezul.practice.streaming.AppMain.*;

public class PlataformaStreaming {
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public PlataformaStreaming(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<Contenido>();
        this.visualizaciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }

    public Map<Contenido, Integer> getVisualizaciones() {
        return visualizaciones;
    }

    public void add(Contenido elemento) {
        Contenido contenidoExistente = buscarPorTitulo(elemento.getTitulo());
        if (contenidoExistente != null) {
            throw new ContenidoExistenteException(elemento.getTitulo());
        }
        FileUtils.saveContenido(
                CONTEXTO_STREAMING + NOMBRE_ARCHIVO_BD_INICIAL,
                SEPARADOR_BD_INICIAL,
                elemento
        );
        this.contenido.add(elemento);
    }

    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " se ha reproducido " + conteoActual + " veces.");

        this.contarVisualizacion(contenido);
        contenido.play();
    }

    private void contarVisualizacion(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }

    public void remove(Contenido elemento) {
        this.contenido.remove(elemento);
    }

    public List<String> getTitulos() {
        return contenido.stream()
                .map(Contenido::getTitulo)
                .collect(Collectors.toList());
    }

    public Contenido buscarPorTitulo(String titulo) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().contains(titulo))
                .findFirst()
                .orElse(null);
    }

    public List<Contenido> buscarPorGenero(GeneroContenido genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .collect(Collectors.toList());
    }

    public List<Contenido> getPopulares(int cantidad) {
        return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido::getCalificacion).reversed())
                .limit(cantidad)
                .collect(Collectors.toList());
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public Contenido getMasVisto() {
        return visualizaciones.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

}
