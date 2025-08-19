package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroContenido;

public class Pelicula extends Contenido{

    public Pelicula(String titulo, int duracion, GeneroContenido genero, double calificacion) {
        super(titulo, duracion, genero, calificacion);
    }
}
