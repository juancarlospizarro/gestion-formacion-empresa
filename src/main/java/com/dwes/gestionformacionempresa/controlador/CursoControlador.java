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

import com.dwes.gestionformacionempresa.modelo.Curso;
import com.dwes.gestionformacionempresa.modelo.Empresa;
import com.dwes.gestionformacionempresa.servicio.CursoServicio;

@Controller
@RequestMapping("/cursos")
public class CursoControlador {

	@Autowired
    private CursoServicio cursoServicio;

    @GetMapping
    public String listarEmpresas(Model modelo) {
        List<Curso> cursos = cursoServicio.listarTodos();
        
        modelo.addAttribute("listaCursos", cursos);
        
        return "cursos/lista";
    }
    
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("curso", new Curso());
        return "cursos/formulario";
    }
	
	@PostMapping("/guardar")
    public String guardarCurso(@ModelAttribute Curso curso, BindingResult result, RedirectAttributes flash) {
        try {
        	if (result.hasErrors()) {
                // Si hay errores de conversión (ej. la fecha o el curso), volveremos al formulario
                return "cursos/formulario"; 
            }
        	cursoServicio.guardar(curso);
            flash.addFlashAttribute("success", "Curso guardado con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/cursos";
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
        return cursoServicio.buscarPorId(id).map(curso -> {
            modelo.addAttribute("curso", curso);
            return "cursos/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "El curso no existe");
            return "redirect:/cursos";
        });
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id, RedirectAttributes flash) {
        try {
        	cursoServicio.eliminar(id);
            flash.addFlashAttribute("success", "Curso eliminado correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar el curso");
        }
        return "redirect:/cursos";
    }
}
