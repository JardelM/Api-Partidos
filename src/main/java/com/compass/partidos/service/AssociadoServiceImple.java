package com.compass.partidos.service;

import com.compass.partidos.dto.AssociadoDto;
import com.compass.partidos.dto.AssociadoFormDto;
import com.compass.partidos.dto.VinculoFormDto;
import com.compass.partidos.entity.Associado;
import com.compass.partidos.enums.Cargo;
import com.compass.partidos.entity.Partido;
import com.compass.partidos.exceptions.AssociadoNaoVinculadoException;
import com.compass.partidos.exceptions.AssociadoNotFoundException;
import com.compass.partidos.exceptions.PartidoNotFoundException;
import com.compass.partidos.repository.AssociadoRepository;
import com.compass.partidos.repository.PartidoRespository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImple implements AssociadoService {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private PartidoRespository partidoRespository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AssociadoDto> buscaAssociados(Cargo cargo, String filtro) {

        List<Associado> associados;

        if (cargo == null)
            associados = associadoRepository.findAll();
        else
            associados = associadoRepository.findAllByCargoPolitico(cargo);

        if (filtro != null)
            associados.sort(Comparator.comparing(Associado::getNome, Comparator.naturalOrder()));

        return associados.stream().map(associado -> modelMapper.map(associado, AssociadoDto.class)).collect(Collectors.toList());
    }

    @Override
    public AssociadoDto vinculaAssociado(VinculoFormDto vinculoFormDto) {

        Optional<Partido> partido = partidoRespository.findById(vinculoFormDto.getIdPartido());
        verificaExistenciaAssociado(vinculoFormDto.getIdAssociado());

        if (partido.isPresent()) {
            Optional<Associado> associado = associadoRepository.findById(vinculoFormDto.getIdAssociado());

            Associado associadoAVincular = modelMapper.map(associado.get(), Associado.class);
            Partido partidoASetar = modelMapper.map(partido.get(), Partido.class);

            associadoAVincular.setPartido(partidoASetar);
            associadoRepository.save(associadoAVincular);

            return modelMapper.map(associadoAVincular, AssociadoDto.class);
        }

        throw new PartidoNotFoundException(vinculoFormDto.getIdPartido());
    }

    @Override
    public AssociadoDto getAssociado(Long id) {

        verificaExistenciaAssociado(id);
        Optional<Associado> associado = associadoRepository.findById(id);

        return modelMapper.map(associado.get(), AssociadoDto.class);
    }

    @Override
    public AssociadoDto criaAssociado(AssociadoFormDto associadoFormDto) {

        Associado associado = modelMapper.map(associadoFormDto, Associado.class);
        associadoRepository.save(associado);
        return modelMapper.map(associado, AssociadoDto.class);
    }

    @Override
    public AssociadoDto atualizaAssociado(Long id, AssociadoFormDto associadoFormDto) {

        verificaExistenciaAssociado(id);
        Associado associado = modelMapper.map(associadoFormDto, Associado.class);
        associado.setId(id);
        associadoRepository.save(associado);

        return modelMapper.map(associado, AssociadoDto.class);
    }

    @Override
    public void deletaAssociado(Long id) {

        verificaExistenciaAssociado(id);
        associadoRepository.deleteById(id);
    }

    @Override
    public void desvinculaAssociado(Long id1, Long id2) {

        verificaExistenciaAssociado(id1);

        Optional<Associado> associado = associadoRepository.findById(id1);

        Associado associadoEncontrado = modelMapper.map(associado.get(), Associado.class);

        verificaVinculoDeAssociado(associadoEncontrado);


        if (associadoEncontrado.getPartido().getId() == id2) {
            associadoEncontrado.setPartido(null);
            associadoRepository.save(associadoEncontrado);
        } else
            throw new AssociadoNaoVinculadoException();
    }


    private void verificaVinculoDeAssociado(Associado associadoEncontrado) {
        if (associadoEncontrado.getPartido() == null)
            throw new AssociadoNaoVinculadoException();

    }

    private void verificaExistenciaAssociado(Long idAssociado) {
        associadoRepository.findById(idAssociado).orElseThrow(() -> new AssociadoNotFoundException(idAssociado));
    }
}
