package com.compass.partidos.dto;

import com.compass.partidos.enums.Cargo;
import com.compass.partidos.enums.Sexo;
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
public class AssociadoFormDto {

    @NotBlank
    private String nome;
    @NotNull(message = "Valores válidos: Vereador, Prefeito, Deputado Estadual, Deputado Federal, Senador, Governador, Presidente, Nenhum")
    @Enumerated(EnumType.STRING)
    private Cargo cargoPolitico;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotNull(message = "Preecha com Masculino ou Feminino!")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private PartidoDto partido;

}
