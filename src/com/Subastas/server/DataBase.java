/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Subastas.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.Subastas.model.VO.Oferta;
import com.Subastas.model.VO.Subasta;
import com.Subastas.model.VO.Usuario;

/**
 *
 * @author Daniel
 */
public class DataBase {
    
    
    private Connection conexion;
    
    private static DataBase instance = null;
    
    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase("", "root");
        }
        return instance;
    }

    private DataBase(String password, String usuario) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.put("user", usuario);
            //properties.put("password", password);
            
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/Subastas", properties);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void insertarUsuario(Usuario usuario){
        if(!getUsuario(usuario.getIdUsuario()).isEmpty()){    
            throw new Error("Error, ese usuario ya existe");
        }
        else{
            String sentencia = "INSERT INTO usuario (idUsuario, correo, nombre) "
                + "VALUES ('" + usuario.getIdUsuario() + "','" + usuario.getCorreo() + "','" + usuario.getNombre() + "');"; 
            this.Insertar(sentencia);
        }
    }
    
    public void insertarOferta(Oferta oferta){
        oferta.setIdOferta(String.valueOf(this.getNumero("oferta")));
        
        String sentencia = "INSERT INTO oferta (idOferta, idUsuario, idSubasta, valorPuja)"
                + "VALUES ('" + oferta.getIdOferta() + "','" + oferta.getUsuario().getIdUsuario() + "','" + oferta.getSubasta().getIdSubasta() + 
                "'," + String.valueOf(oferta.getValorPuja()) + ");"; 
            
        this.Insertar(sentencia);
        
        String modificacion = "UPDATE subasta SET valorPuja = '" + oferta.getValorPuja() + "' WHERE idSubasta = '" + oferta.getSubasta().getIdSubasta() + "';";
        this.Insertar(modificacion);
            
    }
    
    public void insertarSubasta(Subasta subasta){
        
        subasta.setIdSubasta(String.valueOf(this.getNumero("subasta")));
        
        //"INSERT INTO `subasta`(`idSubasta`, `precioInicial`, `descripcion`, `idUsuario`, `idCategoria`, `valorPuja`) VALUES ('2',100,'Celular','01','01Tecn',0)"
        
        String sentencia = "INSERT INTO subasta (idSubasta, precioInicial, descripcion, idUsuario, idCategoria, valorPuja)" 
                + "VALUES ('" + subasta.getIdSubasta() + "'," + subasta.getPrecioInicial() + ",'" 
                + subasta.getDescripcion() + "','" + subasta.getUsuario().getIdUsuario() + "','" 
                + subasta.getCategoria().getIdCategoria() + "'," + subasta.getValorPuja() + ")"; 
            
        this.Insertar(sentencia);
            
    }
    
    private int getNumero(String nombreTabla){
    
        Statement stmt = null;
        int total = 0;
        ArrayList<String> columna = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM " + nombreTabla+";");
            while (resultado.next()) {
                total++;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cerrarConexion(stmt);
        }
        
        return total+1;
    }
    
    public ArrayList<String> getUsuario(String id){
    
        Statement stmt = null;
        ArrayList<String> columna = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM " +"usuario" +" WHERE " + "idUsuario" + "='" + id +"';");
            while (resultado.next()) {
                for (int i = 0; i < resultado.getMetaData().getColumnCount(); i++) columna.add(resultado.getString(i + 1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cerrarConexion(stmt);
        }
        return columna;
    }
    
    public ArrayList<String> getSubasta(String id){
    
        Statement stmt = null;
        ArrayList<String> columna = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT idSubasta, precioInicial, descripcion, u.correo, idCategoria, valorPuja FROM subasta AS s JOIN usuario as u ON u.idUsuario=s.idUsuario WHERE idSubasta='" + id +"';");
            while (resultado.next()) {
                for (int i = 0; i < resultado.getMetaData().getColumnCount(); i++) columna.add(resultado.getString(i + 1));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cerrarConexion(stmt);
        }
        return columna;
    }
    
    public ArrayList<ArrayList<String>> buscarSubastasOferta(String categoria, String descripcionProducto, int valorMaximo){
        Statement stmt = null;
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            String consulta = "SELECT * FROM subasta WHERE idCategoria='"+categoria+"' and valorPuja<="+valorMaximo+" and precioInicial<="+valorMaximo+" or descripcion='"+descripcionProducto+"';";
            ResultSet resultado = stmt.executeQuery(consulta);
            while (resultado.next()) {
                datos.add(getSubasta(resultado.getString(1)));
            }
            return datos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            cerrarConexion(stmt);
        }
    }
    
     private void Insertar(String sentencia) {
        Statement stmt = null;
        try {
            stmt = conexion.createStatement();
            stmt.execute(sentencia);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            cerrarConexion(stmt);
        }
    }
     
     private void cerrarConexion(Statement stmt){
         try {
            if (stmt != null)
                stmt.close();
        } catch (SQLException ex) {
             System.out.println(ex.getMessage());
        }
     }

	public ArrayList<ArrayList<String>> getCategorias() {
		Statement stmt = null;
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        try {
            stmt = conexion.createStatement();
            String consulta = "SELECT * FROM categoria";
            ResultSet resultado = stmt.executeQuery(consulta);
            while (resultado.next()) {
            	ArrayList<String> columna = new ArrayList<String>();
            	columna.add(resultado.getString(1));
            	columna.add(resultado.getString(2));
            	datos.add(columna);
            }
            return datos;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } finally {
            cerrarConexion(stmt);
        }
	}

}
