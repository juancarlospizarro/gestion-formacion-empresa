package com.dwes.gestionformacionempresa.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dwes.gestionformacionempresa.modelo.Empresa;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
}
