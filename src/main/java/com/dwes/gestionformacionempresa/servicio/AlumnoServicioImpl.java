package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Curso;
import com.dwes.gestionformacionempresa.repositorio.AlumnoRepositorio;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    private final AlumnoRepositorio alumnoRepositorio;

    public AlumnoServicioImpl(AlumnoRepositorio alumnoRepositorio) {
        this.alumnoRepositorio = alumnoRepositorio;
    }

    @Override
    public Alumno guardar(Alumno alumno) {
        if (alumnoRepositorio.existsByEmail(alumno.getEmail())) {
            throw new IllegalStateException("Email de alumno duplicado");
        }
        return alumnoRepositorio.save(alumno);
    }

    @Override
    public List<Alumno> listarTodos() {
        return alumnoRepositorio.findAll();
    }

    @Override
    public List<Alumno> listarPorCurso(Long cursoId) {
        return alumnoRepositorio.findByCursoId(cursoId);
    }

    @Override
    public Optional<Alumno> buscarPorId(Long id) {
        return alumnoRepositorio.findById(id);
    }

    @Override
    public void eliminar(Long id) {
    	alumnoRepositorio.deleteById(id);
    }
}
