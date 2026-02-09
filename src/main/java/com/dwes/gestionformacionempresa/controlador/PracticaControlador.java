package com.dwes.gestionformacionempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwes.gestionformacionempresa.modelo.Practica;
import com.dwes.gestionformacionempresa.servicio.PracticaServicio;

@Controller
@RequestMapping("/practicas")
public class PracticaControlador {

	@Autowired
    private PracticaServicio practicaServicio;

    @GetMapping
    public String listarEmpresas(Model modelo) {
        List<Practica> practicas = practicaServicio.listarTodas();
        
        modelo.addAttribute("listaPracticas", practicas);
        
        return "practicas/lista";
    }
}
