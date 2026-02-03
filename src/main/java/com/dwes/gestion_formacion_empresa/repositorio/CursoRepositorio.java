package com.dwes.gestion_formacion_empresa.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestion_formacion_empresa.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
