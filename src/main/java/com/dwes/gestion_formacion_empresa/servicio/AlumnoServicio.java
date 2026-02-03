package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.dwes.gestion_formacion_empresa.modelo.Alumno;
import com.dwes.gestion_formacion_empresa.modelo.Curso;

public interface AlumnoServicio {

    Alumno guardar(Alumno alumno);

    List<Alumno> listarTodos();

    List<Alumno> listarPorCurso(Curso curso);

    Optional<Alumno> buscarPorId(Long id);

    void eliminar(Long id);
}
