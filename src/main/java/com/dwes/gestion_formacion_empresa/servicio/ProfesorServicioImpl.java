package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dwes.gestion_formacion_empresa.modelo.Profesor;
import com.dwes.gestion_formacion_empresa.repositorio.ProfesorRepositorio;

@Service
public class ProfesorServicioImpl implements ProfesorServicio {

    private final ProfesorRepositorio profesorRepositorio;

    public ProfesorServicioImpl(ProfesorRepositorio profesorRepositorio) {
        this.profesorRepositorio = profesorRepositorio;
    }

    @Override
    public Profesor guardar(Profesor profesor) {
        if (profesorRepositorio.existsByEmail(profesor.getEmail())) {
            throw new IllegalStateException("Email ya registrado");
        }
        return profesorRepositorio.save(profesor);
    }

    @Override
    public List<Profesor> listarTodos() {
        return profesorRepositorio.findAll();
    }

    @Override
    public Optional<Profesor> buscarPorEmail(String email) {
        return profesorRepositorio.findByEmail(email);
    }

    @Override
    public void eliminar(Long id) {
    	profesorRepositorio.deleteById(id);
    }
}
