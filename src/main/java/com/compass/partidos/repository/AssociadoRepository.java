package com.compass.partidos.repository;

import com.compass.partidos.entity.Associado;
import com.compass.partidos.enums.Cargo;
import com.compass.partidos.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociadoRepository extends JpaRepository<Associado , Long> {

    List<Associado> findAllByCargoPolitico(Cargo cargo);

    List<Associado> findAllByPartido(Partido partido);

    List<Associado> findByPartidoId(Long id);
}
