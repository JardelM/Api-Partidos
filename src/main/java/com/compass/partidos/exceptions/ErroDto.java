package com.compass.partidos.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroDto {

    private String campo;
    private String erro;
}
