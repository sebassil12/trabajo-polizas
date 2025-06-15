package com.laboratorio.SemanaDos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.laboratorio.SemanaDos.model.Resultado;



@Repository
public interface ResultadoRepositorio extends JpaRepository<Resultado, Integer> {
}