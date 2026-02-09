package com.dwes.gestionformacionempresa.servicio;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dwes.gestionformacionempresa.modelo.Profesor;
import com.dwes.gestionformacionempresa.repositorio.ProfesorRepositorio;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Buscamos al profesor por email según los requisitos 
        Profesor profesor = profesorRepositorio.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Profesor no encontrado: " + email));

        // Mapeamos el tipo de profesor a un rol de Spring Security (ROLE_DIRECTIVA o ROLE_PROFESOR)
        String rol = "ROLE_" + profesor.getTipo().name();

        return new User(
                profesor.getEmail(),
                profesor.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(rol))
        );
    }
}
