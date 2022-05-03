package com.compass.partidos.entity;

import com.compass.partidos.enums.Ideologia;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeDoPartido;
    private String sigla;
    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFundacao;

//    @OneToMany
//    List <Associado> associados;


}
