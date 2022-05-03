package com.compass.partidos.controller;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.AssociadoFormDto;
import com.compass.partidos.dto.VinculoFormDto;
import com.compass.partidos.enums.Cargo;
import com.compass.partidos.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController {

    @Autowired
    private AssociadoService associadoService;

    @GetMapping
    public List<AssociadoDto> buscaAssociados(@RequestParam(required = false) Cargo cargo, @RequestParam(required = false) String filtro) {
        return associadoService.buscaAssociados(cargo, filtro);
    }

    @GetMapping("/{id}")
    public AssociadoDto buscaAssociado(@PathVariable Long id) {
        return associadoService.getAssociado(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoDto postaAssociado(@RequestBody @Valid AssociadoFormDto associadoFormDto) {
        return associadoService.criaAssociado(associadoFormDto);
    }

    @PutMapping("/{id}")
    public AssociadoDto atualizaAssociado(@PathVariable Long id, @RequestBody @Valid AssociadoFormDto associadoFormDto) {
        return associadoService.atualizaAssociado(id, associadoFormDto);
    }


    @PostMapping("/partidos")
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoDto vinculaAssociado(@RequestBody @Valid VinculoFormDto vinculoFormDto) {
        return associadoService.vinculaAssociado(vinculoFormDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deletaAssociado(@PathVariable Long id) {
        associadoService.deletaAssociado(id);
    }


    @DeleteMapping("/{id1}/partidos/{id2}")
    @ResponseStatus(HttpStatus.OK)
    public void desvincula(@PathVariable Long id1, @PathVariable Long id2) {
        associadoService.desvinculaAssociado(id1, id2);
    }
}
