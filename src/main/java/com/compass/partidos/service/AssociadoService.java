package com.compass.partidos.service;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.AssociadoFormDto;
import com.compass.partidos.dto.VinculoFormDto;
import com.compass.partidos.enums.Cargo;

import java.util.List;

public interface AssociadoService {

    List<AssociadoDto> buscaAssociados(Cargo cargo, String filtro);

    AssociadoDto vinculaAssociado(VinculoFormDto vinculoFormDto);

    AssociadoDto getAssociado(Long id);

    AssociadoDto criaAssociado(AssociadoFormDto associadoFormDto);

    AssociadoDto atualizaAssociado(Long id, AssociadoFormDto associadoFormDto);

    void deletaAssociado(Long id);

    void desvinculaAssociado(Long id1, Long id2);
}
