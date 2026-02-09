package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dwes.gestionformacionempresa.modelo.Practica;
import com.dwes.gestionformacionempresa.repositorio.PracticaRepositorio;

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
    
    // Nombre Empresa -> Cantidad de Alumnos
    @Override
    public Map<String, Long> obtenerEstadisticasEmpresas() {
        return practicaRepositorio.findAll().stream()
            .collect(Collectors.groupingBy(
                p -> p.getEmpresa().getNombre(), 
                Collectors.counting()
            ));
    }

    // Nombre Curso -> Cantidad de Alumnos en prácticas
    @Override
    public Map<String, Long> obtenerEstadisticasCursos() {
        return practicaRepositorio.findAll().stream()
            .collect(Collectors.groupingBy(
                p -> p.getAlumno().getCurso().getNombre(), 
                Collectors.counting()
            ));
    }
}
