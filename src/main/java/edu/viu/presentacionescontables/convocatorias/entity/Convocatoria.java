package edu.viu.presentacionescontables.convocatorias.entity;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Objeto representación de las convocatorias
 */
@Entity
@Table(name = "convocatoria")
public class Convocatoria implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(length = 100, unique = true, nullable = false, updatable = false)
	private String nombre;

	@Column(length = 2000)
	private String descripcion;

	@Column
	private LocalDateTime fechaApertura;

	@Column
	private LocalDateTime fechaCierre;

	@Column
	private boolean abierta;

	@Column
	private int documentacionRespaldatoriaRequerida;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "convocatoria")
	private List<Presentacion> presentacion;

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

	public LocalDateTime getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(LocalDateTime fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public LocalDateTime getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(LocalDateTime fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public boolean isAbierta() {
		return abierta;
	}

	public void setAbierta(boolean abierta) {
		this.abierta = abierta;
	}

	public int getDocumentacionRespaldatoriaRequerida() {
		return documentacionRespaldatoriaRequerida;
	}

	public void setDocumentacionRespaldatoriaRequerida(int documentacionRespaldatoriaRequerida) {
		this.documentacionRespaldatoriaRequerida = documentacionRespaldatoriaRequerida;
	}

	public List<Presentacion> getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(List<Presentacion> presentacion) {
		this.presentacion = presentacion;
	}
}
