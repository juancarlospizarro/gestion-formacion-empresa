package com.dwes.gestionformacionempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.servicio.AlumnoServicio;
import com.dwes.gestionformacionempresa.servicio.CursoServicio;

import jakarta.validation.Valid;

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
	
	@GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("alumno", new Alumno());
        modelo.addAttribute("listaCursos", cursoServicio.listarTodos());
        modelo.addAttribute("titulo", "Nuevo Alumno");
        return "alumnos/formulario";
    }
	
	@PostMapping("/guardar")
    public String guardarAlumno(@Valid @ModelAttribute Alumno alumno, BindingResult result, RedirectAttributes flash) {
        try {
        	if (result.hasErrors()) {
                // Si hay errores de conversión (ej. la fecha o el curso), volveremos al formulario
                return "alumnos/formulario"; 
            }
            alumnoServicio.guardar(alumno);
            flash.addFlashAttribute("success", "Alumno guardado con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/alumnos";
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
        return alumnoServicio.buscarPorId(id).map(alumno -> {
            modelo.addAttribute("alumno", alumno);
            modelo.addAttribute("listaCursos", cursoServicio.listarTodos());
            modelo.addAttribute("titulo", "Editar Alumno");
            return "alumnos/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "El alumno no existe");
            return "redirect:/alumnos";
        });
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id, RedirectAttributes flash) {
        try {
            alumnoServicio.eliminar(id);
            flash.addFlashAttribute("success", "Alumno eliminado correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar el alumno (puede tener prácticas asociadas)");
        }
        return "redirect:/alumnos";
    }
	
	@GetMapping("/importarcsv")
    public String mostrarImportar(Model modelo) {
        modelo.addAttribute("listaCursos", cursoServicio.listarTodos());
        return "alumnos/importarcsv";
    }
	
	@PostMapping("/importarcsv")
	public String importarCSV(@RequestParam MultipartFile archivo, @RequestParam Long cursoId, RedirectAttributes flash) {
	    if (archivo.isEmpty()) {
	        flash.addFlashAttribute("error", "Por favor, selecciona un archivo.");
	        return "redirect:/alumnos/importarcsv";
	    }

	    try {
	        int importados = alumnoServicio.importarDesdeCSV(archivo, cursoId);
	        flash.addFlashAttribute("success", "Se han importado " + importados + " alumnos correctamente.");
	    } catch (Exception e) {
	        flash.addFlashAttribute("error", "Error en el formato del archivo: " + e.getMessage());
	    }
	    return "redirect:/alumnos";
	}
}
