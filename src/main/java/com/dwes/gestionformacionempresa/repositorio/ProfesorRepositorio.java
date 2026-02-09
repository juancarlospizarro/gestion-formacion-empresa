package com.dwes.gestionformacionempresa.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestionformacionempresa.modelo.Profesor;

public interface ProfesorRepositorio extends JpaRepository<Profesor, Long> {

    Optional<Profesor> findByEmail(String email);

    boolean existsByEmail(String email);
}
