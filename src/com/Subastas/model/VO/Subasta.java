package com.Subastas.model.VO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the subasta database table.
 * 
 */
@Entity
@NamedQuery(name="Subasta.findAll", query="SELECT s FROM Subasta s")
public class Subasta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idSubasta;

	private String descripcion;

	private int precioInicial;
        
        private int valorPuja;
        

	//bi-directional many-to-one association to Oferta
	@OneToMany(mappedBy="subasta")
	private List<Oferta> ofertas;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	@JoinColumn(name="idCategoria")
	private Categoria categoria;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="idUsuario")
	private Usuario usuario;

	public Subasta() {
	}

    public Subasta(String idSubasta, String descripcion, int precioInicial, Categoria categoria, Usuario usuario, int valorPuja) {
        this.idSubasta = idSubasta;
        this.descripcion = descripcion;
        this.precioInicial = precioInicial;
        this.categoria = categoria;
        this.usuario = usuario;
        this.valorPuja = valorPuja;
    }
        
        
        
	public String getIdSubasta() {
		return this.idSubasta;
	}

	public void setIdSubasta(String idSubasta) {
		this.idSubasta = idSubasta;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecioInicial() {
		return this.precioInicial;
	}

	public void setPrecioInicial(int precioInicial) {
		this.precioInicial = precioInicial;
	}

	public List<Oferta> getOfertas() {
		return this.ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public Oferta addOferta(Oferta oferta) {
		getOfertas().add(oferta);
		oferta.setSubasta(this);

		return oferta;
	}

	public Oferta removeOferta(Oferta oferta) {
		getOfertas().remove(oferta);
		oferta.setSubasta(null);

		return oferta;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

    public int getValorPuja() {
        return valorPuja;
    }

    public void setValorPuja(int valorPuja) {
        this.valorPuja = valorPuja;
    }
        
        

}