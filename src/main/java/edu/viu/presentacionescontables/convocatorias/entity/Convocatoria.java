package edu.viu.presentacionescontables.convocatorias.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

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
}
