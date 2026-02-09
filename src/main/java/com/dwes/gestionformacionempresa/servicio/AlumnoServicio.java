package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Optional;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Curso;

public interface AlumnoServicio {

    Alumno guardar(Alumno alumno);

    List<Alumno> listarTodos();

    List<Alumno> listarPorCurso(Long cursoId);

    Optional<Alumno> buscarPorId(Long id);

    void eliminar(Long id);
}
