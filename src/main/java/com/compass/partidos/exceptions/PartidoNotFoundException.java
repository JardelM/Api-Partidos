package com.compass.partidos.exceptions;

import static java.lang.String.format;

public class PartidoNotFoundException extends RuntimeException {
    public PartidoNotFoundException(Long idPartido) {
        super(format("Não há registro de partido com id %s", idPartido));
    }
}
