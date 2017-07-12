package com.Subastas.model.VO;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idCategoria;

	private String nombreCategoria;

	//bi-directional many-to-one association to Subasta
	@OneToMany(mappedBy="categoria")
	private List<Subasta> subastas;

	public Categoria() {
	}

        public Categoria(String idCategoria, String nombreCategoria) {
            this.idCategoria = idCategoria;
            this.nombreCategoria = nombreCategoria;
        }
        
        

	public String getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public List<Subasta> getSubastas() {
		return this.subastas;
	}

	public void setSubastas(List<Subasta> subastas) {
		this.subastas = subastas;
	}

	public Subasta addSubasta(Subasta subasta) {
		getSubastas().add(subasta);
		subasta.setCategoria(this);

		return subasta;
	}

	public Subasta removeSubasta(Subasta subasta) {
		getSubastas().remove(subasta);
		subasta.setCategoria(null);

		return subasta;
	}

}