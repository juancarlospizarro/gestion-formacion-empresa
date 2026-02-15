package com.dwes.gestionformacionempresa.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Curso;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {

    List<Alumno> findByCursoId(Long cursoId);

    Optional<Alumno> findByEmail(String email);

    boolean existsByEmail(String email);
    
    List<Alumno> findByPracticasIsEmpty();
}
