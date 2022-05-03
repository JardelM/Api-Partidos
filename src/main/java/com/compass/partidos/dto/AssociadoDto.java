package com.compass.partidos.dto;

import com.compass.partidos.enums.Cargo;
import com.compass.partidos.enums.Sexo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssociadoDto {

    private Long id;
    private String nome;
    private Cargo cargoPolitico;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private Sexo sexo;
    private PartidoDto partido;
}
