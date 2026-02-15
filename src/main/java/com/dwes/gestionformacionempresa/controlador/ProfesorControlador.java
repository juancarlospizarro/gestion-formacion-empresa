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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dwes.gestionformacionempresa.modelo.Alumno;
import com.dwes.gestionformacionempresa.modelo.Profesor;
import com.dwes.gestionformacionempresa.modelo.TipoProfesor;
import com.dwes.gestionformacionempresa.servicio.ProfesorServicio;

@Controller
@RequestMapping("/profesores")
public class ProfesorControlador {

	@Autowired
    private ProfesorServicio profesorServicio;

    @GetMapping
    public String listarEmpresas(Model modelo) {
        List<Profesor> profesores = profesorServicio.listarTodos();
        
        modelo.addAttribute("listaProfesores", profesores);
        
        return "profesores/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("profesor", new Profesor());
        modelo.addAttribute("roles", TipoProfesor.values());
        return "profesores/formulario";
    }
	
	@PostMapping("/guardar")
    public String guardarProfesor(@ModelAttribute Profesor profesor, BindingResult result, RedirectAttributes flash) {
        try {
        	if (result.hasErrors()) {
                // Si hay errores de conversión (ej. la fecha o el curso), volveremos al formulario
                return "profesores/formulario"; 
            }
            profesorServicio.guardar(profesor);
            flash.addFlashAttribute("success", "Profesor guardado con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/profesores";
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
        return profesorServicio.findById(id).map(profesor -> {
            modelo.addAttribute("profesor", profesor);
            return "profesores/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "El profesor no existe");
            return "redirect:/profesores";
        });
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Long id, RedirectAttributes flash) {
        try {
        	profesorServicio.eliminar(id);
            flash.addFlashAttribute("success", "Profesor eliminado correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar el profesor");
        }
        return "redirect:/profesores";
    }
}
