package com.compass.partidos.exceptions;

import static java.lang.String.format;

public class AssociadoNaoVinculadoException extends RuntimeException {
    public AssociadoNaoVinculadoException(){
        super(format("Não existe vinculo desse associado com esse partido!"));
    }
}
