package com.compass.partidos.service;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.PartidoDto;
import com.compass.partidos.dto.PartidoFormDto;
import com.compass.partidos.enums.Ideologia;

import java.util.List;

public interface PartidoService {

    List<PartidoDto> buscaPartidos(Ideologia ideologia);

    PartidoDto buscaPartido(Long id);

    List<AssociadoDto> buscaAssociadosPartido(Long id);

    PartidoDto criaPartido(PartidoFormDto partidoFormDto);

    PartidoDto atualizaPartido(PartidoFormDto partidoFormDto , Long id);

    void deletaPartido(Long id);
}
