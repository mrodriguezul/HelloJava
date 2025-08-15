package dev.mrodriguezul.entity;

import dev.mrodriguezul.enums.GeneroPelicula;

import java.time.LocalDate;

public class Pelicula {
    private String titulo;
    private String descripcion;
    private int duracion;
    private GeneroPelicula genero;
    private LocalDate fechaEstreno;
    private double calificacion;
    private boolean disponible;

    public Pelicula(String titulo, int duracion, GeneroPelicula genero) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.genero = genero;
        this.fechaEstreno = LocalDate.now();
        this.disponible = true;
    }

    public Pelicula(String titulo, int duracion, GeneroPelicula genero, double calificacion) {
        this(titulo, duracion, genero);
        this.calificar(calificacion);
    }

    public void play() {
        System.out.println("Reproduciendo ... " + titulo);
    }

    public String getDetallesTecnicos() {
        return "\n" + titulo + " (" + fechaEstreno.getYear() + ")\n" +
               "Género: " + genero + "\n" +
               "Calificación: " + calificacion + "/5";
    }

    public void calificar(double calificacion) {
        if (calificacion >= 0 && calificacion <= 5) {
            this.calificacion = calificacion;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public GeneroPelicula getGenero() {
        return genero;
    }

    public LocalDate getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(LocalDate fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
}
