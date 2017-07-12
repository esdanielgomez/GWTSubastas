package com.Subastas.client;

import java.util.ArrayList;

import com.Subastas.client.DTO.OfertaDTO;
import com.Subastas.client.DTO.SubastaDTO;
import com.Subastas.client.DTO.UsuarioDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	public String crearUsuario(UsuarioDTO usuario);
    public String crearSubasta(SubastaDTO subasta);
    ArrayList<ArrayList<String>> getSubastasOferta(OfertaDTO oferta);
    public String buscarSubasta(OfertaDTO oferta);
    public String crearOferta(OfertaDTO oferta);
    public ArrayList<ArrayList<String>> getCategorias();
    
}