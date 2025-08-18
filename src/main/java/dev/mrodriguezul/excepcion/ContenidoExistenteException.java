package dev.mrodriguezul.excepcion;

public class ContenidoExistenteException extends RuntimeException {
    public ContenidoExistenteException(String titulo) {
        super("La película '" + titulo + "' ya existe en la plataforma.");
    }
}
