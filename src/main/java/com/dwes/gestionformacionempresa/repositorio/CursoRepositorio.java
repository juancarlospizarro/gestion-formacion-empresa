package com.dwes.gestionformacionempresa.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestionformacionempresa.modelo.Curso;

public interface CursoRepositorio extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
}
