package com.Subastas.client;

import java.util.ArrayList;

import com.Subastas.client.DTO.OfertaDTO;
import com.Subastas.client.DTO.SubastaDTO;
import com.Subastas.client.DTO.UsuarioDTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

	
	/*void crearOferta(String idUsuario, String idSubasta, String valorPuja, AsyncCallback<String> callback);
	void crearUsuario(String idUsuario, String correo, String nombre, AsyncCallback<String> callback);
	void crearSubasta(String idUsuario, String descripcionProducto, String valorInicial, String categoria, int valorPuja,
			AsyncCallback<String> callback);
	void getSubastasOferta(String categoria, String descripcionProducto, String valorMaximo,
			AsyncCallback<ArrayList<ArrayList<String>>> callback);
	void getCategorias(AsyncCallback<ArrayList<ArrayList<String>>> callback);
	void buscarSubasta(String categoria, String descripcionProducto, String valorMaximo, String idUsuario,
			AsyncCallback<String> callback);*/
	
	void buscarSubasta(OfertaDTO oferta, AsyncCallback<String> callback);
	void crearOferta(OfertaDTO oferta, AsyncCallback<String> callback);
	void crearSubasta(SubastaDTO subasta, AsyncCallback<String> callback);
	void crearUsuario(UsuarioDTO usuario, AsyncCallback<String> callback);
	void getCategorias(AsyncCallback<ArrayList<ArrayList<String>>> callback);
	void getSubastasOferta(OfertaDTO oferta, AsyncCallback<ArrayList<ArrayList<String>>> callback);
}
