package com.compass.partidos.repository;

import com.compass.partidos.enums.Ideologia;
import com.compass.partidos.entity.Partido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartidoRespository extends JpaRepository<Partido, Long> {

    List<Partido> findAllByIdeologia(Ideologia ideologia);
}
