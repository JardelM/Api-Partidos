package com.compass.partidos.exceptions;


public class AssociadoNotFoundException extends RuntimeException {
    public AssociadoNotFoundException(Long idAssociado) {
        super(String.format("Associado com id %s inexistente", idAssociado));
    }
}
