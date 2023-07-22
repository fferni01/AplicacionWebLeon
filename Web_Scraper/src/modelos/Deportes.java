/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


/**
 *
 * @author Usuario
 */

public class Deportes {

    private int idDeportes;

    private String Titulo;

    private String Url;

    private String Direccion;

    private InputStream Imagen;

    public int getIdDeportes() {
        return idDeportes;
    }

    public void setIdDeportes(int idDeportes) {
        this.idDeportes = idDeportes;
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

   
    
    
    
}
