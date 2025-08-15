package dev.mrodriguezul.excepcion;

public class PeliculaExistenteException extends RuntimeException {
    public PeliculaExistenteException(String titulo) {
        super("La película '" + titulo + "' ya existe en la plataforma.");
    }
}
