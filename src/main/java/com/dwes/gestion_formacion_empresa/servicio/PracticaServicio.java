package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;

import com.dwes.gestion_formacion_empresa.modelo.Practica;

public interface PracticaServicio {

    Practica crearPractica(Practica practica);

    List<Practica> listarTodas();

    void eliminar(Long id);
}
