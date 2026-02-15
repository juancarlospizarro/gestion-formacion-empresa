package com.dwes.gestionformacionempresa.servicio;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Curso;

import jakarta.mail.Multipart;

public interface AlumnoServicio {

    Alumno guardar(Alumno alumno);

    List<Alumno> listarTodos();

    List<Alumno> listarPorCurso(Long cursoId);

    Optional<Alumno> buscarPorId(Long id);

    void eliminar(Long id);
    
    List<Alumno> listarAlumnosSinPractica();
    
    int importarDesdeCSV(MultipartFile archivoCSV, Long cursoId) throws Exception;
}
