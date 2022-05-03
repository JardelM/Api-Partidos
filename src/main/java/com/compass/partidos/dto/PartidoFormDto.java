package com.compass.partidos.dto;

import com.compass.partidos.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoFormDto {

    @NotBlank
    private String nomeDoPartido;
    @NotBlank
    private String sigla;
    @NotNull(message = "Preecha com Esquerda, Centro ou Direita!")
    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;
}
