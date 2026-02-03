package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.dwes.gestion_formacion_empresa.modelo.Profesor;

public interface ProfesorServicio {

    Profesor guardar(Profesor profesor);

    List<Profesor> listarTodos();

    Optional<Profesor> buscarPorEmail(String email);

    void eliminar(Long id);
}
