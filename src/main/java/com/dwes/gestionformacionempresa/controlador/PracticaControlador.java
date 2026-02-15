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

import com.dwes.gestionformacionempresa.modelo.Empresa;
import com.dwes.gestionformacionempresa.modelo.Practica;
import com.dwes.gestionformacionempresa.servicio.AlumnoServicio;
import com.dwes.gestionformacionempresa.servicio.EmpresaServicio;
import com.dwes.gestionformacionempresa.servicio.PracticaServicio;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/practicas")
public class PracticaControlador {

	@Autowired
    private PracticaServicio practicaServicio;
	
	@Autowired
    private EmpresaServicio empresaServicio;
	
	@Autowired
    private AlumnoServicio alumnoServicio;

    @GetMapping
    public String listarEmpresas(Model modelo) {
        List<Practica> practicas = practicaServicio.listarTodas();
        
        modelo.addAttribute("listaPracticas", practicas);
        
        return "practicas/lista";
    }
    
    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("practica", new Practica());
        modelo.addAttribute("listaAlumnos", alumnoServicio.listarAlumnosSinPractica());
        modelo.addAttribute("listaEmpresas", empresaServicio.listarTodas());
        return "practicas/formulario";
    }
	
	@PostMapping("/guardar")
    public String guardarPractica(@Valid @ModelAttribute("practica") Practica practica, BindingResult result, Model modelo, RedirectAttributes flash) {
		// 1. Si hay errores de validación (como el de las fechas o campos vacíos)
	    if (result.hasErrors()) {
	        modelo.addAttribute("listaAlumnos", alumnoServicio.listarTodos());
	        modelo.addAttribute("listaEmpresas", empresaServicio.listarTodas());
	        modelo.addAttribute("titulo", practica.getId() == null ? "Nueva Práctica" : "Editar Práctica");
	        return "practicas/formulario";
	    }
	    
	    try {
	        practicaServicio.crearPractica(practica);
	        flash.addFlashAttribute("success", "Práctica guardada correctamente");
	        return "redirect:/practicas"; // <--- CAMBIO CLAVE: Redirigir tras éxito
	        
	    } catch (IllegalStateException e) {
	        // Error de negocio: El alumno ya tiene práctica
	        result.rejectValue("alumno", "error.alumno", e.getMessage());
	        modelo.addAttribute("listaAlumnos", alumnoServicio.listarTodos());
	        modelo.addAttribute("listaEmpresas", empresaServicio.listarTodas());
	        return "practicas/formulario";
	    }
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
        return practicaServicio.findById(id).map(practica -> {
            modelo.addAttribute("practica", practica);
            modelo.addAttribute("listaAlumnos", alumnoServicio.listarTodos());
            modelo.addAttribute("listaEmpresas", empresaServicio.listarTodas());
            return "practicas/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "La empresa no existe");
            return "redirect:/practicas";
        });
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminarPractica(@PathVariable Long id, RedirectAttributes flash) {
        try {
            practicaServicio.eliminar(id);
            flash.addFlashAttribute("success", "Práctica eliminada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar la práctica");
        }
        return "redirect:/practicas";
    }
}
