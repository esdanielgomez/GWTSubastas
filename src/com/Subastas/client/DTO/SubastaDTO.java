package com.Subastas.client.DTO;

import java.io.Serializable;

public class SubastaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idSubasta;
	private String descripcion;
	private String precioInicial;
    private String valorPuja;
        
	private CategoriaDTO categoria;
	private UsuarioDTO usuario;

	public SubastaDTO() {
	}

    public SubastaDTO(String idSubasta, String descripcion, String precioInicial, CategoriaDTO categoria, UsuarioDTO usuario, String valorPuja) {
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

	public String getPrecioInicial() {
		return this.precioInicial;
	}

	public void setPrecioInicial(String precioInicial) {
		this.precioInicial = precioInicial;
	}

	public CategoriaDTO getCategoria() {
		return this.categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

	public UsuarioDTO getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

    public String getValorPuja() {
        return valorPuja;
    }

    public void setValorPuja(String valorPuja) {
        this.valorPuja = valorPuja;
    }
        
        

}