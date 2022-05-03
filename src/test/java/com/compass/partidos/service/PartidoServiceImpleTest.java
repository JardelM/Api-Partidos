package com.compass.partidos.service;

import com.compass.partidos.dto.PartidoDto;
import com.compass.partidos.dto.PartidoFormDto;
import com.compass.partidos.entity.Partido;
import com.compass.partidos.enums.Ideologia;
import com.compass.partidos.repository.PartidoRespository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class PartidoServiceImpleTest {


    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PartidoRespository partidoRespository;

    @Autowired
    private PartidoServiceImple partidoServiceImple;

    @Test
    void deveriaCriarUmPartidoComSucesso(){
        PartidoFormDto partidoFormDto = criaPartidoFormDto();
        PartidoDto partidoDtoEsperado = criaPartidoDto();
        Partido partido = criaPartido();

        Mockito.when(modelMapper.map(partidoFormDto , Partido.class)).thenReturn(partido);
        Mockito.when(partidoRespository.save(partido)).thenReturn(partido);
        Mockito.when(modelMapper.map(partido , PartidoDto.class)).thenReturn(partidoDtoEsperado);

        PartidoDto partidoDto = partidoServiceImple.criaPartido(partidoFormDto);

        assertEquals(partidoDto , partidoDtoEsperado);

    }

    @Test
    void deveriaRetornarUmaListaDePartidos(){
        Partido partido = criaPartido();
        PartidoDto partidoDtoEsperado = criaPartidoDto();

        Mockito.when(partidoRespository.findAll()).thenReturn(Collections.singletonList(partido));
        Mockito.when(modelMapper.map(partido , PartidoDto.class)).thenReturn(partidoDtoEsperado);

        List<PartidoDto> partidoDtosAtual = partidoServiceImple.buscaPartidos(null);
        assertEquals(Collections.singletonList(partidoDtoEsperado), partidoDtosAtual);

    }

    @Test
    void deveriaDeletarUmPartido(){
        Long id = 1L;
        Partido partido = criaPartido();

        Mockito.when(partidoRespository.findById(id)).thenReturn(Optional.of(partido));
        doNothing().when(partidoRespository).deleteById(id);

        partidoServiceImple.deletaPartido(id);
        verify(partidoRespository , times(1)).deleteById(id);

    }

    @Test
    void deveriaAtualizarUmPartidoComSucesso(){
        Long id = 1L;
        Partido partido = criaPartido();
        PartidoDto partidoDtoEsperado = criaPartidoDto();
        PartidoFormDto partidoFormDto = criaPartidoFormDto();

        Mockito.when(partidoRespository.findById(id)).thenReturn(Optional.of(partido));
        Mockito.when(modelMapper.map(partidoFormDto , Partido.class)).thenReturn(partido);
        Mockito.when(modelMapper.map(partido , PartidoDto.class)).thenReturn(partidoDtoEsperado);

        PartidoDto partidoDtoAtual = partidoServiceImple.atualizaPartido(partidoFormDto , id);

        assertEquals(partidoDtoAtual , partidoDtoEsperado);

    }





    private Partido criaPartido() {
        return new Partido(
                1L,
                "Partido de Testes",
                "PT",
                Ideologia.CENTRO,
                LocalDate.now()
        );
    }

    private PartidoDto criaPartidoDto() {
        return new PartidoDto(
                1L,
                "Partido de Testes",
                "PT",
                Ideologia.CENTRO,
                LocalDate.now()

        );
    }

    private PartidoFormDto criaPartidoFormDto() {
        return new PartidoFormDto(
                "Partido de Testes",
                "PT",
                Ideologia.CENTRO,
                LocalDate.now()
        );
    }



}