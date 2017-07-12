package com.Subastas.client.DTO;

import java.io.Serializable;
import javax.persistence.*;


public class OfertaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String idOferta;

	private String valorPuja;
	private SubastaDTO subasta;
	private UsuarioDTO usuario;

	public OfertaDTO() {
	}

        public OfertaDTO(String idOferta, String valorPuja, SubastaDTO subasta, UsuarioDTO usuario) {
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

	public String getValorPuja() {
		return this.valorPuja;
	}

	public void setValorPuja(String valorPuja) {
		this.valorPuja = valorPuja;
	}

	public SubastaDTO getSubasta() {
		return this.subasta;
	}

	public void setSubasta(SubastaDTO subasta) {
		this.subasta = subasta;
	}

	public UsuarioDTO getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

}