package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dwes.gestionformacionempresa.modelo.Curso;
import com.dwes.gestionformacionempresa.repositorio.CursoRepositorio;

@Service
public class CursoServicioImpl implements CursoServicio {

    private final CursoRepositorio cursoRepositorio;

    public CursoServicioImpl(CursoRepositorio cursoRepositorio) {
        this.cursoRepositorio = cursoRepositorio;
    }

    @Override
    public Curso guardar(Curso curso) {
        if (cursoRepositorio.existsByNombre(curso.getNombre())) {
            throw new IllegalStateException("El curso ya existe");
        }
        return cursoRepositorio.save(curso);
    }

    @Override
    public List<Curso> listarTodos() {
        return cursoRepositorio.findAll();
    }

    @Override
    public Optional<Curso> buscarPorId(Long id) {
        return cursoRepositorio.findById(id);
    }

    @Override
    public void eliminar(Long id) {
    	cursoRepositorio.deleteById(id);
    }
}
