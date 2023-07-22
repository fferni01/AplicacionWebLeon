/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


/**
 *
 * @author Usuario
 */

public class Rutas {

    private int idRuta;

    private String Titulo;

    private String Url;

    private String Distancia;

    private String Direccion;

    private String Imagensrc;

    private InputStream Imagen;

    public int getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(int idRuta) {
        this.idRuta = idRuta;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

    public String getDistancia() {
        return Distancia;
    }

    public void setDistancia(String string) {
        this.Distancia = string;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

   

    public InputStream getImagen() {
		return Imagen;
	}

	public void setImagen(InputStream imagen) {
		Imagen = imagen;
	}

	public String getImagensrc() {
        return Imagensrc;
    }

    public void setImagensrc(String Imagensrc) {
        this.Imagensrc = Imagensrc;
    }

    

   
    
  
    
    
}
