package com.compass.partidos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VinculoFormDto {

    private Long idAssociado;
    private Long idPartido;
}
