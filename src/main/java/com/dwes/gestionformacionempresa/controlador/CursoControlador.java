package com.dwes.gestionformacionempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwes.gestionformacionempresa.modelo.Curso;
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
}
