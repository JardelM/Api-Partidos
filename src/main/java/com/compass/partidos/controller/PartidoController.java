package com.compass.partidos.controller;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.PartidoDto;
import com.compass.partidos.dto.PartidoFormDto;
import com.compass.partidos.enums.Ideologia;
import com.compass.partidos.service.PartidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    private PartidoService partidoService;


    @GetMapping
    public List<PartidoDto> buscaPartidos(@RequestParam(required = false) Ideologia ideologia) {
        return partidoService.buscaPartidos(ideologia);
    }

    @GetMapping("/{id}")
    public PartidoDto buscaPartido(@PathVariable Long id) {
        return partidoService.buscaPartido(id);
    }

    @GetMapping("/{id}/associados")
    public List<AssociadoDto> buscaAssociadosPartido(@PathVariable Long id) {
        return partidoService.buscaAssociadosPartido(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PartidoDto criaPartido(@RequestBody @Valid PartidoFormDto partidoFormDto) {
        return partidoService.criaPartido(partidoFormDto);
    }

    @PutMapping("/{id}")
    public PartidoDto atualizaPartido(@PathVariable Long id, @RequestBody @Valid PartidoFormDto partidoFormDto) {
        return partidoService.atualizaPartido(partidoFormDto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletaPartido(@PathVariable Long id) {
        partidoService.deletaPartido(id);
    }
}
