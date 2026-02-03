package com.dwes.gestion_formacion_empresa.configuracion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dwes.gestion_formacion_empresa.modelo.Alumno;
import com.dwes.gestion_formacion_empresa.modelo.Curso;
import com.dwes.gestion_formacion_empresa.modelo.Empresa;
import com.dwes.gestion_formacion_empresa.modelo.Practica;
import com.dwes.gestion_formacion_empresa.modelo.Profesor;
import com.dwes.gestion_formacion_empresa.modelo.TipoProfesor;
import com.dwes.gestion_formacion_empresa.repositorio.AlumnoRepositorio;
import com.dwes.gestion_formacion_empresa.repositorio.CursoRepositorio;
import com.dwes.gestion_formacion_empresa.repositorio.EmpresaRepositorio;
import com.dwes.gestion_formacion_empresa.repositorio.PracticaRepositorio;
import com.dwes.gestion_formacion_empresa.repositorio.ProfesorRepositorio;
import com.github.javafaker.Faker;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class IniciarDatos implements CommandLineRunner {
	
    @Autowired
    private AlumnoRepositorio alumnoRepositorio;
    
    @Autowired
    private CursoRepositorio cursoRepositorio;

    @Autowired
    private EmpresaRepositorio empresaRepositorio;
    
    @Autowired
    private PracticaRepositorio practicaRepositorio;
    
    @Autowired
    private ProfesorRepositorio profesorRepositorio;
    
    /*@Autowired
    private EntityManager em;*/

    @Override
    public void run(String... args) {
    	
    	/*
    	em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        em.createNativeQuery("TRUNCATE TABLE practicas").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE alumnos").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE empresas").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE profesores").executeUpdate();
        em.createNativeQuery("TRUNCATE TABLE cursos").executeUpdate();

        em.createNativeQuery("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();
    	
    	*/
    	
        if (cursoRepositorio.count() > 0) {
            return; // Ya hay datos
        }

        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        // ===== CURSOS =====
        Curso daw = new Curso("1º DAW", new ArrayList<Alumno>());
        cursoRepositorio.save(daw);

        // ===== PROFESORES =====
        String nombreDirectiva = faker.name().firstName();
        String apellidosDirectiva = faker.name().lastName();
        String emailDirectiva = nombreDirectiva.toLowerCase() + apellidosDirectiva.toLowerCase() + "@centro.es";
        String contraseñaDirectiva = nombreDirectiva + "1234";
        TipoProfesor tipoDirectiva = TipoProfesor.DIRECTIVA;
        Profesor directiva = new Profesor(nombreDirectiva, apellidosDirectiva, emailDirectiva, contraseñaDirectiva, tipoDirectiva);
        profesorRepositorio.save(directiva);

        String nombre = faker.name().firstName();
        String apellidos = faker.name().lastName();
        String email = nombre.toLowerCase() + apellidos.toLowerCase() + "@centro.es";
        String contraseña = nombre + "1234";
        TipoProfesor tipo = TipoProfesor.NORMAL;
        Profesor normal = new Profesor(nombre, apellidos, email, contraseña, tipo);
        profesorRepositorio.save(normal);

        // ===== ALUMNOS =====
        for (int i = 0; i < 20; i++) {
        	String nombreAlumno = faker.name().firstName();
        	String apellidosAlumno = faker.name().lastName();
            String emailAlumno = nombreAlumno.toLowerCase() + apellidosAlumno.toLowerCase() + "@mail.com";
            LocalDate fechaNacimientoAlumno = LocalDate.now().minusYears(18 + random.nextInt(5));
            Alumno alumno = new Alumno(nombreAlumno, apellidosAlumno, emailAlumno, fechaNacimientoAlumno, daw);
            alumnoRepositorio.save(alumno);
        }

        // ===== EMPRESAS =====
        for (int i = 0; i < 5; i++) {
            String nombreEmpresa = faker.company().name();
            String descripcion = faker.company().catchPhrase();
            String nombreTutorEmpresa = faker.name().fullName();
            String emailTutorEmpresa = nombreTutorEmpresa.toLowerCase().replace(" ", "").trim() + "@empresa.com";
            Empresa empresa = new Empresa(nombreEmpresa, descripcion, nombreTutorEmpresa, emailTutorEmpresa, new ArrayList<Practica>());
            empresaRepositorio.save(empresa);
        }

        // ===== PRACTICAS =====
        List<Alumno> alumnos = alumnoRepositorio.findAll();
        List<Empresa> empresas = empresaRepositorio.findAll();

        for (int i = 0; i < 10; i++) {
            Alumno alumnoPracticas = alumnos.get(i);
            Empresa empresaPracticas = empresas.get(random.nextInt(empresas.size()));
            LocalDate fechaInicio = LocalDate.now().minusDays(10);
            LocalDate fechaFin = LocalDate.now().plusMonths(3);
            String comentarios = "Buen desempeño inicial";
            Practica practica = new Practica(alumnoPracticas, empresaPracticas, fechaInicio, fechaFin, comentarios);
            practicaRepositorio.save(practica);
        }
    }
}
