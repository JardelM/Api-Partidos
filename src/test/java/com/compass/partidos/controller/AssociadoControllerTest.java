package com.compass.partidos.controller;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.AssociadoFormDto;
import com.compass.partidos.dto.PartidoDto;
import com.compass.partidos.enums.Cargo;
import com.compass.partidos.enums.Ideologia;
import com.compass.partidos.enums.Sexo;
import com.compass.partidos.service.AssociadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssociadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AssociadoService associadoService;

    @Test
    void deveriaRetornarStatus200AoBuscarPorAssociados() throws Exception {

        mockMvc.perform(get("/associados"))
                .andExpect(status().isOk());
    }

    @Test
    void dadoUmAssociadoFormComCampoInvalidoRetornaUmaExcecao() throws Exception {

        AssociadoFormDto associadoFormDto = criaAssociadoFormDto();
        associadoFormDto.setNome(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/associados")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(associadoFormDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveriaCriarUmAssociadoComSucesso() throws Exception {

        AssociadoFormDto associadoFormDto = criaAssociadoFormDto();
        AssociadoDto associadoDto = criaAssociadoDto();

        Mockito.when(associadoService.criaAssociado(associadoFormDto)).thenReturn(associadoDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/associados")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(associadoFormDto)))
                .andExpect(status().isCreated());

    }

    @Test
    void dadoUmIdValidoDeveriaRetornarOkAoRecuperarAssociado() throws Exception {

        Long id = 1L;
        AssociadoDto associadoDto = criaAssociadoDto();

        Mockito.when(associadoService.getAssociado(id)).thenReturn(associadoDto);

        mockMvc.perform(get("/associados/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", is(associadoDto.getNome())));
    }


    private AssociadoDto criaAssociadoDto() {

        PartidoDto partido = new PartidoDto(1L,
                "Partido x",
                "PX",
                Ideologia.CENTRO,
                LocalDate.now());

        return new AssociadoDto(
                1L,
                "João Da Silva",
                Cargo.NENHUM,
                LocalDate.now(),
                Sexo.MASCULINO,
                partido
        );
    }


    private AssociadoFormDto criaAssociadoFormDto() {

        PartidoDto partido = new PartidoDto(1L,
                "Partido x",
                "PX",
                Ideologia.CENTRO,
                LocalDate.now());

        return new AssociadoFormDto(
                "João Da Silva",
                Cargo.NENHUM,
                LocalDate.now(),
                Sexo.MASCULINO,
                partido
        );
    }

}