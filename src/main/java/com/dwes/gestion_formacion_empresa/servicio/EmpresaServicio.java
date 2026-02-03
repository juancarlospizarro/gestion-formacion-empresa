package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;
import java.util.Optional;

import com.dwes.gestion_formacion_empresa.modelo.Empresa;

public interface EmpresaServicio {

    Empresa guardar(Empresa empresa);

    List<Empresa> listarTodas();

    Optional<Empresa> buscarPorId(Long id);

    void eliminar(Long id);
}
