package com.Subastas.model.VO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idUsuario;

	private String correo;

	private String nombre;

	//bi-directional many-to-one association to Oferta
	@OneToMany(mappedBy="usuario")
	private List<Oferta> ofertas;

	//bi-directional many-to-one association to Subasta
	@OneToMany(mappedBy="usuario")
	private List<Subasta> subastas;

	public Usuario() {
	}

        public Usuario(String idUsuario, String correo, String nombre) {
            this.idUsuario = idUsuario;
            this.correo = correo;
            this.nombre = nombre;
        }
        
        

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Oferta> getOfertas() {
		return this.ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Oferta addOferta(Oferta oferta) {
		getOfertas().add(oferta);
		oferta.setUsuario(this);

		return oferta;
	}

	public Oferta removeOferta(Oferta oferta) {
		getOfertas().remove(oferta);
		oferta.setUsuario(null);

		return oferta;
	}

	public List<Subasta> getSubastas() {
		return this.subastas;
	}

	public void setSubastas(List<Subasta> subastas) {
		this.subastas = subastas;
	}

	public Subasta addSubasta(Subasta subasta) {
		getSubastas().add(subasta);
		subasta.setUsuario(this);

		return subasta;
	}

	public Subasta removeSubasta(Subasta subasta) {
		getSubastas().remove(subasta);
		subasta.setUsuario(null);

		return subasta;
	}

}