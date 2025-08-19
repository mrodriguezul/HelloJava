package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroContenido;
import dev.mrodriguezul.interfaces.IPromocionable;

public class Documental extends Contenido implements IPromocionable {
    private String tema;

    public Documental(String titulo, int duracion, GeneroContenido genero) {
        super(titulo, duracion, genero);
    }

    @Override
    public void play() {
        System.out.println("Reproduciendo documental: " + getTitulo() + " sobre: " + tema);
    }

    @Override
    public String getDetallesTecnicos() {
        return super.getDetallesTecnicos()+
                "\nTema: " + tema;
    }

    @Override
    public String promocionar() {
        return "¡No te pierdas el documental " + getTitulo() + "! Un fascinante viaje a través de " + tema + ".";
    }

    public Documental(String titulo, int duracion, GeneroContenido genero, double calificacion, String tema) {
        super(titulo, duracion, genero, calificacion);
        this.tema = tema;
    }

    public String getTema() {
        return tema;
    }


}
