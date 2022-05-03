package com.compass.partidos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Ideologia {

    DIREITA("Direita"),
    CENTRO("Centro"),
    ESQUERDA("Esquerda");

    private final String descricao;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Ideologia paraValores(@JsonProperty("ideologia") String des) {
        for (Ideologia ideologia : Ideologia.values()) {
            if (ideologia.descricao.equalsIgnoreCase(des))
                return ideologia;
        }
        return null;
    }
}
