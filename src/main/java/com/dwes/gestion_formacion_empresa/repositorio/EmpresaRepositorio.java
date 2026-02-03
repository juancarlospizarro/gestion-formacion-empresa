package com.dwes.gestion_formacion_empresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestion_formacion_empresa.modelo.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
}
