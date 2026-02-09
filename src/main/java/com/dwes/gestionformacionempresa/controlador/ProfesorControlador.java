package com.dwes.gestionformacionempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwes.gestionformacionempresa.modelo.Profesor;
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
}
