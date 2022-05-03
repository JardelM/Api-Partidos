package com.compass.partidos.entity;

import com.compass.partidos.enums.Cargo;
import com.compass.partidos.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Associado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Cargo cargoPolitico;
    private LocalDate dataNascimento;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @ManyToOne
    private Partido partido;


}
