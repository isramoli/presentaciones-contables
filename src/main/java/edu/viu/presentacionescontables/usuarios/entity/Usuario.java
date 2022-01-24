package edu.viu.presentacionescontables.usuarios.entity;

import edu.viu.presentacionescontables.municipios.entity.Municipio;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Objeto representación del usuario
 */
@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {

	@Id
	@Column(length = 10, unique = true, nullable = false, updatable = false)
	private String nombre;

	@Column(length = 60)
	private String password;

	@Column
	private String tipoUsuario;

	@OneToMany
	private List<Municipio> municipios;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Municipio> getMunicipios() {
		return municipios;
	}

	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
