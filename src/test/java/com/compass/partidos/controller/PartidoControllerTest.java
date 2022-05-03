package com.compass.partidos.controller;

import com.compass.partidos.dto.PartidoDto;
import com.compass.partidos.dto.PartidoFormDto;
import com.compass.partidos.enums.Ideologia;
import com.compass.partidos.exceptions.PartidoNotFoundException;
import com.compass.partidos.service.PartidoService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PartidoControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PartidoService partidoService;


    @Test
    void deveriaRetornarStatus200AoBuscarPorPartidos() throws Exception {

        mockMvc.perform(get("/partidos"))
                .andExpect(status().isOk());
    }


    @Test
    void dadoUmPartidoComCampoInvalidoRetornaUmaExcecao() throws Exception {

        PartidoFormDto partidoFormDto = criaPartidoFormDto();
        partidoFormDto.setNomeDoPartido(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/partidos")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partidoFormDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deveriaCriarUmPartidoComSucesso() throws Exception {

        PartidoFormDto partidoFormDto = criaPartidoFormDto();
        PartidoDto partidoDto = criaPartidoDto();

        Mockito.when(partidoService.criaPartido(partidoFormDto)).thenReturn(partidoDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/partidos")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partidoFormDto)))
                .andExpect(status().isCreated());

    }


    @Test
    void deveriaAtualizarUmPartidoComSucesso() throws Exception {

        PartidoFormDto partidoFormDto = criaPartidoFormDto();
        PartidoDto partidoDto = criaPartidoDto();

        Mockito.when(partidoService.criaPartido(partidoFormDto)).thenReturn(partidoDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/partidos/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partidoFormDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deveriaLancarExcecaoAoTentarAtualizarPartidoInexistente() throws Exception {

        Long id = 1L;
        PartidoFormDto partidoFormDto = criaPartidoFormDto();

        Mockito.when(partidoService.atualizaPartido(partidoFormDto, id)).thenThrow(PartidoNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.put("/partidos/1")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(partidoFormDto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deveriaLancarExcecaoAoTentarRecuperarPartidoInexistente() throws Exception {

        Long id = 1L;

        Mockito.when(partidoService.buscaPartido(id)).thenThrow(PartidoNotFoundException.class);

        mockMvc.perform(get("/partidos/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void deveriaRetornarMetodoInvalidoAoTentarDeletarSemID() throws Exception {

        mockMvc.perform(delete("/partidos")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void dadoUmIdValidoDeveriaRetornarOkAoRecuperar() throws Exception {

        Long id = 1L;
        PartidoDto partidoDto = criaPartidoDto();

        Mockito.when(partidoService.buscaPartido(id)).thenReturn(partidoDto);

        mockMvc.perform(get("/partidos/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sigla", is(partidoDto.getSigla())));
    }


    private PartidoDto criaPartidoDto() {
        return new PartidoDto(
                1L,
                "Partido x",
                "PX",
                Ideologia.CENTRO,
                LocalDate.now()
        );
    }


    private PartidoFormDto criaPartidoFormDto() {
        return new PartidoFormDto(
                "Partido x",
                "PX",
                Ideologia.CENTRO,
                LocalDate.now()
        );
    }


}