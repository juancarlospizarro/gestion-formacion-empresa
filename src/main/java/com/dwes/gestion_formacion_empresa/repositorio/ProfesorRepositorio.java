package com.dwes.gestion_formacion_empresa.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestion_formacion_empresa.modelo.Profesor;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByEmail(String email);

    boolean existsByEmail(String email);
}
