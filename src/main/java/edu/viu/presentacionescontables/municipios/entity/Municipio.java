package edu.viu.presentacionescontables.municipios.entity;

import edu.viu.presentacionescontables.pesentaciones.entity.Presentacion;
import edu.viu.presentacionescontables.usuarios.entity.Usuario;

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
import java.io.Serializable;
import java.util.List;

/**
 * Objeto representación de los municipios
 */
@Entity
@Table(name = "municipios")
public class Municipio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(length = 30, unique = true, nullable = false, updatable = false)
    private String nombre;
    @Column
    private int categoria;

    @ManyToOne
    private Usuario cuentadante;

    @OneToMany
    private List<Presentacion> presentaciones;

    public Municipio() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public Usuario getCuentadante() {
        return cuentadante;
    }

    public void setCuentadante(Usuario cuentadante) {
        this.cuentadante = cuentadante;
    }

    public List<Presentacion> getPresentaciones() {
        return presentaciones;
    }

    public void setPresentaciones(List<Presentacion> presentaciones) {
        this.presentaciones = presentaciones;
    }
}
