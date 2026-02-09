package com.dwes.gestionformacionempresa.controlador;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dwes.gestionformacionempresa.servicio.PracticaServicio;

@Controller
@RequestMapping("/estadisticas")
public class EstadisticasControlador {

	@Autowired
	private PracticaServicio practicaServicio;

	@GetMapping
    public String index(Model model) {
		// Datos de Empresas
	    Map<String, Long> statsEmpresas = practicaServicio.obtenerEstadisticasEmpresas();
	    model.addAttribute("nombresEmpresas", new ArrayList<>(statsEmpresas.keySet()));
	    model.addAttribute("conteoEmpresas", new ArrayList<>(statsEmpresas.values()));

	    // Datos de Cursos
	    Map<String, Long> statsCursos = practicaServicio.obtenerEstadisticasCursos();
	    model.addAttribute("nombresCursos", new ArrayList<>(statsCursos.keySet()));
	    model.addAttribute("conteoCursos", new ArrayList<>(statsCursos.values()));
        return "estadisticas";
    }
}
