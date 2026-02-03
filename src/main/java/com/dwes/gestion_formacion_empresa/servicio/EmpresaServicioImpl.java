package com.dwes.gestion_formacion_empresa.servicio;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.dwes.gestion_formacion_empresa.modelo.Empresa;
import com.dwes.gestion_formacion_empresa.repositorio.EmpresaRepositorio;

@Service
public class EmpresaServicioImpl implements EmpresaServicio {

    private final EmpresaRepositorio empresaRepositorio;

    public EmpresaServicioImpl(EmpresaRepositorio empresaRepositorio) {
        this.empresaRepositorio = empresaRepositorio;
    }

    @Override
    public Empresa guardar(Empresa empresa) {
        return empresaRepositorio.save(empresa);
    }

    @Override
    public List<Empresa> listarTodas() {
        return empresaRepositorio.findAll();
    }

    @Override
    public Optional<Empresa> buscarPorId(Long id) {
        return empresaRepositorio.findById(id);
    }

    @Override
    public void eliminar(Long id) {
    	empresaRepositorio.deleteById(id);
    }
}
