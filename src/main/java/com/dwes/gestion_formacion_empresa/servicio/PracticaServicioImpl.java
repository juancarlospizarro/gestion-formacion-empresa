package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dwes.gestion_formacion_empresa.modelo.Practica;
import com.dwes.gestion_formacion_empresa.repositorio.PracticaRepositorio;

@Service
public class PracticaServicioImpl implements PracticaServicio {

    private final PracticaRepositorio practicaRepositorio;

    public PracticaServicioImpl(PracticaRepositorio practicaRepositorio) {
        this.practicaRepositorio = practicaRepositorio;
    }

    @Override
    public Practica crearPractica(Practica practica) {
        if (practicaRepositorio.existsByAlumno(practica.getAlumno())) {
            throw new IllegalStateException("El alumno ya tiene una práctica");
        }
        Practica guardada = practicaRepositorio.save(practica);
        return guardada;
    }

    @Override
    public List<Practica> listarTodas() {
        return practicaRepositorio.findAll();
    }

    @Override
    public void eliminar(Long id) {
    	practicaRepositorio.deleteById(id);
    }
}
