package com.Subastas.server;

import java.util.ArrayList;
import java.util.List;

import com.Subastas.client.GreetingService;
import com.Subastas.client.DTO.OfertaDTO;
import com.Subastas.client.DTO.SubastaDTO;
import com.Subastas.client.DTO.UsuarioDTO;
import com.Subastas.model.VO.Categoria;
import com.Subastas.model.VO.Oferta;
import com.Subastas.model.VO.Subasta;
import com.Subastas.model.VO.Usuario;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String crearUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario(usuarioDTO.getIdUsuario(), usuarioDTO.getCorreo(), usuarioDTO.getNombre());
        try{
        	DataBase.getInstance().insertarUsuario(usuario);
        }
        catch(Exception ie){
        	return "Lo sentimos, algo salio mal en nuestros servidores. Intentalo de nuevo mas tarde.";
        }
        return "Enhorabuena " + usuario.getNombre() + ", ya pudes ofertar y subastar en miles de productos. Bienvenido!";
    }
    
    public String crearSubasta(SubastaDTO subastaDTO){
    	int valor = 0;
    	try{
    		valor = Integer.parseInt(subastaDTO.getPrecioInicial());
    	}
    	catch(Exception e){return "Lo sentimos, el valor inicial debe ser en numeros enteros (###)...";}
    	
    	if(valor<=0){
    		return "Lo sentimos, el valor inicial debe ser positivo...";
    	}
    	
        List<String> datosUsuario = DataBase.getInstance().getUsuario(subastaDTO.getUsuario().getIdUsuario());
        
        if(!datosUsuario.isEmpty()){
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(subastaDTO.getUsuario().getIdUsuario());
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(subastaDTO.getCategoria().getIdCategoria());
            Subasta subasta = new Subasta(null, subastaDTO.getDescripcion(), valor, categoria, usuario, 0);
            
            try{
            	DataBase.getInstance().insertarSubasta(subasta);
            }
            catch(Exception ie){
            	return "Lo sentimos, algo salio mal en nuestros servidores. Intentalo de nuevo mas tarde.";
            }
            
            return "Se ha creado la subasta: " + subastaDTO.getDescripcion() + ", con un valor inicial de: " + subastaDTO.getPrecioInicial();
        }
        else{
            return "Error, el usuario con identificacion '"+ subastaDTO.getUsuario().getIdUsuario() + "' no existe.";
        }
    }    
    
    public ArrayList<ArrayList<String>> getSubastasOferta(OfertaDTO oferta){
        //String categoria, String descripcionProducto, String valorMaximo
		int valor = Integer.parseInt(oferta.getValorPuja());
		try{
			return DataBase.getInstance().buscarSubastasOferta(oferta.getSubasta().getCategoria().getIdCategoria(), oferta.getSubasta().getDescripcion(), valor);
	    }
	    catch(Exception ie){
	    	throw new Error("Lo sentimos, no hemos encontrado ninguna coincidencia.");
	    }
    }
    
    public String buscarSubasta(OfertaDTO ofertaDTO){
        
    	//String categoria, String descripcionProducto, String valorMaximo, String idUsuario
    	
    	
    	if(ofertaDTO.getUsuario().getIdUsuario().isEmpty()){
    		return "Error, es necesario que especifique su identificacion";
    	}
    	
    	if(ofertaDTO.getValorPuja().isEmpty()){
    		return "Error, es necesario que especifique el valor maximo del producto";
    	}
    	
    	List<String> datosUsuario;
    	try{
    		datosUsuario = DataBase.getInstance().getUsuario(ofertaDTO.getUsuario().getIdUsuario());
        }
        catch(Exception ie){
        	return "Lo sentimos, algo salio mal en nuestros servidores al buscar el usuario. Intentalo de nuevo mas tarde.";
        }
    	if(!datosUsuario.isEmpty()){
            
        	int valor = 0;
        	try{
        		valor = Integer.parseInt(ofertaDTO.getValorPuja());
        	}catch(Exception e){
        		return "Lo sentimos, el valor maximo debe ser en numeros enteros (###)...";
        	}
        	if(valor<=0){
        		return "Lo sentimos, el valor maximo debe ser positivo...";
        	}

        	try{
        		DataBase.getInstance().buscarSubastasOferta(ofertaDTO.getSubasta().getCategoria().getIdCategoria(), ofertaDTO.getSubasta().getDescripcion(), valor);
        		return null;
            }
            catch(Exception ie){
            	return "Lo sentimos, no hemos encontrado ninguna coincidencia.";
            }
        }
        else{
            return "Error, ese usuario no existe";
        }
    	
    }
        
    public String crearOferta(OfertaDTO ofertaDTO){
        
    	//String idUsuario, String idSubasta, String valorPuja
    	
    	if(ofertaDTO.getUsuario().getIdUsuario().isEmpty()){
    		return "Error, es necesario que especifique su identificacion";
    	}
    	
    	if(ofertaDTO.getValorPuja().isEmpty()){
    		return "Error, es necesario que especifique el valor de su oferta";
    	}
    	
    	
    	int valor = 0;
    	try{
    		valor = Integer.parseInt(ofertaDTO.getValorPuja());
    	}
    	catch(Exception e){return "Lo sentimos, el valor inicial debe ser en numeros enteros (###)...";}
    	
    	if(valor<=0){
    		return "Lo sentimos, el valor inicial debe ser positivo...";
    	}
    	
    	
    	List<String> datosUsuario = DataBase.getInstance().getUsuario(ofertaDTO.getUsuario().getIdUsuario());
        if(!datosUsuario.isEmpty()){
            Usuario usuario = new Usuario(datosUsuario.get(0), datosUsuario.get(1), datosUsuario.get(2));
            List<String> datosSubasta = DataBase.getInstance().getSubasta(ofertaDTO.getSubasta().getIdSubasta());
            
            if(!datosSubasta.isEmpty()){
                Subasta subasta = new Subasta(datosSubasta.get(0), datosSubasta.get(3), (int)Integer.parseInt(datosSubasta.get(1)), null, null, Integer.parseInt(datosSubasta.get(5)));
                Oferta oferta = new Oferta(null, valor, subasta, usuario);
                
                if(oferta.getValorPuja()<= subasta.getPrecioInicial()){
                    return "Error, el valor de la puja debe ser mayor al minimo establecido";
                }
                if(oferta.getValorPuja()== subasta.getValorPuja()){
                    return "Lo sentimos, ya se orfeto por ese precio";                    
                }
                if(oferta.getValorPuja()< subasta.getValorPuja()){
                    return "Lo sentimos, ya se hizo una mejor oferta";                    
                }
                
                DataBase.getInstance().insertarOferta(oferta);
                return "Se ha creado una oferta para la subasta: " + ofertaDTO.getSubasta().getIdSubasta() + ", con un valor de: " + valor +".";
            }
            else{
                return "Error, esa subasta no existe";
            }
        }
        else{
            return "Error, ese usuario no existe";   
        }
    }

	@Override
	public ArrayList<ArrayList<String>> getCategorias() {
		try{
			return DataBase.getInstance().getCategorias();
        }
        catch(Exception ie){
        	throw new Error( "Lo sentimos, algo salio mal en nuestros servidores. Intentalo de nuevo mas tarde.");
        }
	}

}
