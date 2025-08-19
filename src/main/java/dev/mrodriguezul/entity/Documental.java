package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroContenido;

public class Documental extends Contenido{
    private String tema;

    public Documental(String titulo, int duracion, GeneroContenido genero) {
        super(titulo, duracion, genero);
    }

    public Documental(String titulo, int duracion, GeneroContenido genero, double calificacion, String tema) {
        super(titulo, duracion, genero, calificacion);
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }
}
