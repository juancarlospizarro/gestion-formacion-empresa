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
import com.dwes.gestionformacionempresa.modelo.Empresa;
import com.dwes.gestionformacionempresa.servicio.EmpresaServicio;

@Controller
@RequestMapping("/empresas")
public class EmpresaControlador {

    @Autowired
    private EmpresaServicio empresaServicio;

    @GetMapping
    public String listarEmpresas(Model modelo) {
        List<Empresa> empresas = empresaServicio.listarTodas();
        
        modelo.addAttribute("listaEmpresas", empresas);
        
        return "empresas/lista";
    }
    
    @GetMapping("/nueva")
    public String mostrarFormularioNuevo(Model modelo) {
        modelo.addAttribute("empresa", new Empresa());
        return "empresas/formulario";
    }
	
	@PostMapping("/guardar")
    public String guardarEmpresa(@ModelAttribute Empresa empresa, BindingResult result, RedirectAttributes flash) {
        try {
        	if (result.hasErrors()) {
                // Si hay errores de conversión (ej. la fecha o el curso), volveremos al formulario
                return "empresas/formulario"; 
            }
            empresaServicio.guardar(empresa);
            flash.addFlashAttribute("success", "Empresa guardada con éxito");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "Error al guardar: " + e.getMessage());
        }
        return "redirect:/empresas";
    }
	
	@GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model modelo, RedirectAttributes flash) {
        return empresaServicio.buscarPorId(id).map(empresa -> {
            modelo.addAttribute("empresa", empresa);
            return "empresas/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "La empresa no existe");
            return "redirect:/empresas";
        });
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminarAlumno(@PathVariable Long id, RedirectAttributes flash) {
        try {
            empresaServicio.eliminar(id);
            flash.addFlashAttribute("success", "Empresa eliminada correctamente");
        } catch (Exception e) {
            flash.addFlashAttribute("error", "No se puede eliminar la empresa");
        }
        return "redirect:/empresas";
    }
}
