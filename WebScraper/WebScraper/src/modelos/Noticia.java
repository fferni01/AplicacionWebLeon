/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;


import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;


/**
 *
 * @author Usuario
 */

public class Noticia {

    private int idNoticia;

    private String Titulo;


    private Date Fecha;

    private InputStream imagen;
   
    public int getIdNoticia() {
        return idNoticia;
    }

    public void setIdNoticia(int idNoticia) {
        this.idNoticia = idNoticia;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }
    public InputStream getImagen() {
		return imagen;
	}

	public void setImagen(InputStream imagen) {
		this.imagen = imagen;
	}

	public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

  
  
    
    
}
