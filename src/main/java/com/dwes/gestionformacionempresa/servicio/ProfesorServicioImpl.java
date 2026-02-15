package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dwes.gestionformacionempresa.modelo.Profesor;
import com.dwes.gestionformacionempresa.repositorio.ProfesorRepositorio;

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

	@Override
	public Optional<Profesor> findById(Long id) {
		profesorRepositorio.findById(id);
		return Optional.empty();
	}
}
