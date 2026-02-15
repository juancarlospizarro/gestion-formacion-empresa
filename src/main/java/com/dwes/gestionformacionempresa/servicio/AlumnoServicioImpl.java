package com.dwes.gestionformacionempresa.servicio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Curso;
import com.dwes.gestionformacionempresa.repositorio.AlumnoRepositorio;
import com.dwes.gestionformacionempresa.repositorio.CursoRepositorio;
import com.dwes.gestionformacionempresa.repositorio.PracticaRepositorio;

import jakarta.transaction.Transactional;

@Service
public class AlumnoServicioImpl implements AlumnoServicio {

    private final AlumnoRepositorio alumnoRepositorio;
    private final PracticaRepositorio practicaRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public AlumnoServicioImpl(AlumnoRepositorio alumnoRepositorio, PracticaRepositorio practicaRepositorio, CursoRepositorio cursoRepositorio) {
        this.alumnoRepositorio = alumnoRepositorio;
        this.practicaRepositorio = practicaRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

    @Override
    public Alumno guardar(Alumno alumno) {
        Optional<Alumno> alumnoExistente = alumnoRepositorio.findByEmail(alumno.getEmail());

        if (alumnoExistente.isPresent() && !alumnoExistente.get().getId().equals(alumno.getId())) {
            throw new IllegalStateException("El email ya está registrado por otro alumno");
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
    
    @Override
    public List<Alumno> listarAlumnosSinPractica() {
        return alumnoRepositorio.findByPracticasIsEmpty();
    }
    
    @Override
    @Transactional
    public int importarDesdeCSV(MultipartFile archivo, Long cursoId) throws Exception {
        Curso curso = cursoRepositorio.findById(cursoId).orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        int contador = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(archivo.getInputStream(), StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(","); 
                
                if (datos.length >= 5) {
                    Alumno a = new Alumno();
                    a.setNombre(datos[0].trim());
                    
                    a.setApellidos(datos[1].trim() + " " + datos[2].trim()); 
                    
                    a.setEmail(datos[3].trim()); 
                    
                    a.setFechaNacimiento(LocalDate.parse(datos[4].trim())); 
                    
                    a.setCurso(curso);

                    if (!alumnoRepositorio.existsByEmail(a.getEmail())) {
                        alumnoRepositorio.save(a);
                        contador++;
                    }
                }
            }
        }
        return contador;
    }
}
