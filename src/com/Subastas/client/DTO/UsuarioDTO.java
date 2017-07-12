package com.Subastas.client.DTO;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idUsuario;
	private String correo;
	private String nombre;

	public UsuarioDTO() {
	}

        public UsuarioDTO(String idUsuario, String correo, String nombre) {
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
}