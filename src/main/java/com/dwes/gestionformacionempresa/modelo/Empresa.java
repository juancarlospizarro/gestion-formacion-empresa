package com.dwes.gestionformacionempresa.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private String tutorNombre;
    private String tutorEmail;

    @OneToMany(mappedBy="empresa")
    private List<Practica> practicas = new ArrayList<>();

    public Empresa() {
        // constructor vacío requerido por JPA
    }
    
	public Empresa(String nombre, String descripcion, String tutorNombre, String tutorEmail, List<Practica> practicas) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tutorNombre = tutorNombre;
		this.tutorEmail = tutorEmail;
		this.practicas = practicas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getTutorNombre() {
		return tutorNombre;
	}

	public void setTutorNombre(String tutorNombre) {
		this.tutorNombre = tutorNombre;
	}

	public String getTutorEmail() {
		return tutorEmail;
	}

	public void setTutorEmail(String tutorEmail) {
		this.tutorEmail = tutorEmail;
	}

	public List<Practica> getPracticas() {
		return practicas;
	}

	public void setPracticas(List<Practica> practicas) {
		this.practicas = practicas;
	}

	@Override
	public String toString() {
		return "Empresa [nombre=" + nombre + ", descripcion=" + descripcion + ", tutorNombre=" + tutorNombre
				+ ", tutorEmail=" + tutorEmail + "]";
	}
    
}
