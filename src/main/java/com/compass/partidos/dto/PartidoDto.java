package com.compass.partidos.dto;

import com.compass.partidos.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoDto {

    private Long id;
    private String nomeDoPartido;
    private String sigla;
    private Ideologia ideologia;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;

}
