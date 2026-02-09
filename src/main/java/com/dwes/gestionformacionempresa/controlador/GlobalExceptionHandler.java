package com.dwes.gestionformacionempresa.controlador;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String manejarErrorGeneral(Exception ex, Model model) {
        model.addAttribute("mensaje", "Se ha producido un error interno: " + ex.getMessage());
        return "error";
    }

    @ExceptionHandler(IllegalStateException.class)
    public String manejarEstadoIlegal(IllegalStateException ex, Model model) {
        model.addAttribute("mensaje", ex.getMessage());
        return "error";
    }
}
