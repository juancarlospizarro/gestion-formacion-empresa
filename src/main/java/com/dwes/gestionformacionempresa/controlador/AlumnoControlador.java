package com.dwes.gestionformacionempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.servicio.AlumnoServicio;
import com.dwes.gestionformacionempresa.servicio.CursoServicio;

@Controller
@RequestMapping("/alumnos")
public class AlumnoControlador {

	@Autowired
    private AlumnoServicio alumnoServicio;
	
	@Autowired
    private CursoServicio cursoServicio;


	@GetMapping
	public String listarAlumnos(@RequestParam(name = "cursoId", required = false) Long cursoId, Model modelo) {
	    if (cursoId != null) {
	        modelo.addAttribute("listaAlumnos", alumnoServicio.listarPorCurso(cursoId));
	        modelo.addAttribute("cursoSeleccionado", cursoId);
	    } else {
	        modelo.addAttribute("listaAlumnos", alumnoServicio.listarTodos());
	    }
	    // Añadimos la lista de cursos para el filtro desplegable
	    modelo.addAttribute("listaCursos", cursoServicio.listarTodos());
	    return "alumnos/lista";
	}
}
