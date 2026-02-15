package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Practica;

public interface PracticaServicio {

    Practica crearPractica(Practica practica);
    
    Optional<Practica> findById(Long id);

    List<Practica> listarTodas();

    void eliminar(Long id);
    
    Map<String, Long> obtenerEstadisticasEmpresas();
    Map<String, Long> obtenerEstadisticasCursos();
   
}
