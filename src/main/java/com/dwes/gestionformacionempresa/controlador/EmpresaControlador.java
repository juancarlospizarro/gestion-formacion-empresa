package com.dwes.gestionformacionempresa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
