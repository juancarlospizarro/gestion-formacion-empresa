package com.dwes.gestion_formacion_empresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestion_formacion_empresa.modelo.Alumno;
import com.dwes.gestion_formacion_empresa.modelo.Empresa;
import com.dwes.gestion_formacion_empresa.modelo.Practica;

public interface PracticaRepositorio extends JpaRepository<Practica, Long> {

    boolean existsByAlumno(Alumno alumno);

    long countByEmpresa(Empresa empresa);
    
}
