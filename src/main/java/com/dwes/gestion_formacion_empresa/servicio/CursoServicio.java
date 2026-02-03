package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.dwes.gestion_formacion_empresa.modelo.Curso;

public interface CursoServicio {

    Curso guardar(Curso curso);

    List<Curso> listarTodos();

    Optional<Curso> buscarPorId(Long id);

    void eliminar(Long id);
}
