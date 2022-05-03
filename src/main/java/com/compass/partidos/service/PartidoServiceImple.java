package com.compass.partidos.service;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.PartidoDto;
import com.compass.partidos.dto.PartidoFormDto;
import com.compass.partidos.entity.Associado;
import com.compass.partidos.enums.Ideologia;
import com.compass.partidos.entity.Partido;
import com.compass.partidos.exceptions.PartidoNotFoundException;
import com.compass.partidos.repository.AssociadoRepository;
import com.compass.partidos.repository.PartidoRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImple implements PartidoService {

    @Autowired
    private PartidoRespository partidoRespository;

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<PartidoDto> buscaPartidos(Ideologia ideologia) {

        List<Partido> partidos;

        if (ideologia == null)
            partidos = partidoRespository.findAll();
        else
            partidos = partidoRespository.findAllByIdeologia(ideologia);

        return partidos.stream().map(partido -> modelMapper.map(partido, PartidoDto.class)).collect(Collectors.toList());


    }

    @Override
    public PartidoDto buscaPartido(Long id) {

        Partido partido = verificaExistenciaPartido(id);
        return modelMapper.map(partido , PartidoDto.class);
    }

    @Override
    public List<AssociadoDto> buscaAssociadosPartido(Long id) {

        Partido partido = verificaExistenciaPartido(id);
        List<Associado> associados = associadoRepository.findAllByPartido(partido);
        return associados.stream().map(obj -> modelMapper.map(obj , AssociadoDto.class)).collect(Collectors.toList());

/*        verificaExistenciaPartido(id);
        Optional<Partido> buscaPartido = partidoRespository.findById(id);

        Partido partido = modelMapper.map(buscaPartido.get(), Partido.class);

        List<Associado> associados = associadoRepository.findAllByPartido(partido);;

        return associados.stream().map(obj -> modelMapper.map(obj ,AssociadoDto.class)).collect(Collectors.toList());*/

    }

    @Override
    public PartidoDto criaPartido(PartidoFormDto partidoFormDto) {

        Partido partido = modelMapper.map(partidoFormDto , Partido.class);
        partidoRespository.save(partido);
        return modelMapper.map(partido , PartidoDto.class);

    }

    @Override
    public PartidoDto atualizaPartido(PartidoFormDto partidoFormDto , Long id) {

        verificaExistenciaPartido(id);

        Partido partido = modelMapper.map(partidoFormDto , Partido.class);
        partido.setId(id);
        partidoRespository.save(partido);
        return modelMapper.map(partido , PartidoDto.class);

    }

    @Override
    public void deletaPartido(Long id) {
        verificaExistenciaPartido(id);

        List<Associado> associados = associadoRepository.findByPartidoId(id);

        associados.forEach(associado -> {
            associado.setPartido(null);
        });

        partidoRespository.deleteById(id);
    }


    private Partido verificaExistenciaPartido(Long id) {
        return partidoRespository.findById(id).orElseThrow(() -> new PartidoNotFoundException(id));
    }

}
