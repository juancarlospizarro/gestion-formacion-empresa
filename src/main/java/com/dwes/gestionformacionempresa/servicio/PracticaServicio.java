package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Map;

import com.dwes.gestionformacionempresa.modelo.Practica;

public interface PracticaServicio {

    Practica crearPractica(Practica practica);

    List<Practica> listarTodas();

    void eliminar(Long id);
    
    Map<String, Long> obtenerEstadisticasEmpresas();
    Map<String, Long> obtenerEstadisticasCursos();
}
