package com.compass.partidos.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Cargo {

    VEREADOR("Vereador"),
    PREFEITO("Prefeito"),
    DEPUTADO_ESTADUAL("Deputado Estadual"),
    DEPUTADO_FEDERAL("Deputado Federal"),
    SENADOR("Senador"),
    GOVERNADOR("Governador"),
    PRESIDENTE("Presidente"),
    NENHUM("Nenhum");

    private final String descricao;

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static Cargo paraValores(@JsonProperty("cargoPolitico") String des) {
        for (Cargo cargo : Cargo.values()) {
            if (cargo.descricao.equalsIgnoreCase(des))
                return cargo;
        }
        return null;
    }
}
