package com.dwes.gestion_formacion_empresa.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestion_formacion_empresa.modelo.Alumno;
import com.dwes.gestion_formacion_empresa.modelo.Curso;

public interface AlumnoRepositorio extends JpaRepository<Alumno, Long> {

    List<Alumno> findByCurso(Curso curso);

    Optional<Alumno> findByEmail(String email);

    boolean existsByEmail(String email);
}
