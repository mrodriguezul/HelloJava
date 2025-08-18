package dev.mrodriguezul.enums;

public enum GeneroContenido {
    ACCION("Acción"),
    AVENTURA("Aventura"),
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    FANTASIA("Fantasía"),
    HISTORICO("Histórico"),
    MUSICAL("Musical"),
    MISTERIO("Misterio"),
    ROMANCE("Romance"),
    SUSPENSO("Suspenso"),
    TERROR("Terror"),
    ANIMADA("Animada");

    private final String genero;

    GeneroContenido(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }
}
