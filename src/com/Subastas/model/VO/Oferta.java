package com.Subastas.model.VO;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the oferta database table.
 * 
 */
@Entity
@NamedQuery(name="Oferta.findAll", query="SELECT o FROM Oferta o")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idOferta;

	private int valorPuja;

	//bi-directional many-to-one association to Subasta
	@ManyToOne
	@JoinColumn(name="idSubasta")
	private Subasta subasta;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	public Oferta() {
	}

        public Oferta(String idOferta, int valorPuja, Subasta subasta, Usuario usuario) {
            this.idOferta = idOferta;
            this.valorPuja = valorPuja;
            this.subasta = subasta;
            this.usuario = usuario;
        }

        
        
	public String getIdOferta() {
		return this.idOferta;
	}

	public void setIdOferta(String idOferta) {
		this.idOferta = idOferta;
	}

	public int getValorPuja() {
		return this.valorPuja;
	}

	public void setValorPuja(int valorPuja) {
		this.valorPuja = valorPuja;
	}

	public Subasta getSubasta() {
		return this.subasta;
	}

	public void setSubasta(Subasta subasta) {
		this.subasta = subasta;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}