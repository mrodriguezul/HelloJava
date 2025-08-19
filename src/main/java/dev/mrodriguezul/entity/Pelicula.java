package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroContenido;
import dev.mrodriguezul.interfaces.IPromocionable;

public class Pelicula extends Contenido {

    public Pelicula(String titulo, int duracion, GeneroContenido genero, double calificacion) {
        super(titulo, duracion, genero, calificacion);
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo película: " + getTitulo());
    }
}
