package com.Subastas.client.DTO;

import java.io.Serializable;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idCategoria;
	private String nombreCategoria;

	public CategoriaDTO() {
	}

        public CategoriaDTO(String idCategoria, String nombreCategoria) {
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


}