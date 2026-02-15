package com.dwes.gestionformacionempresa.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Empresa;
import com.dwes.gestionformacionempresa.modelo.Practica;

public interface PracticaRepositorio extends JpaRepository<Practica, Long> {

    boolean existsByAlumno(Alumno alumno);

    long countByEmpresa(Empresa empresa);
    
    Optional<Practica> findByAlumno(Alumno alumno);
    
}
