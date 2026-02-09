package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Optional;

import com.dwes.gestionformacionempresa.modelo.Profesor;

public interface ProfesorServicio {

    Profesor guardar(Profesor profesor);

    List<Profesor> listarTodos();

    Optional<Profesor> buscarPorEmail(String email);

    void eliminar(Long id);
}
