package com.dwes.gestionformacionempresa.servicio;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.dwes.gestionformacionempresa.modelo.Alumno;
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
        // 1. Buscamos si ya existe una práctica para ese alumno
        // Nota: El repositorio debe devolver un Optional<Practica>
        Optional<Practica> practicaExistente = practicaRepositorio.findByAlumno(practica.getAlumno());

        // 2. Si existe una práctica Y no es la que estamos editando (los IDs son diferentes)...
        if (practicaExistente.isPresent() && !practicaExistente.get().getId().equals(practica.getId())) {
            throw new IllegalStateException("El alumno ya tiene una práctica asignada");
        }

        // 3. Si llegamos aquí, o es una práctica nueva o es la misma que estamos editando
        return practicaRepositorio.save(practica);
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

    @Override
    public Optional<Practica> findById(Long id) {
        return practicaRepositorio.findById(id); 
    }
}
