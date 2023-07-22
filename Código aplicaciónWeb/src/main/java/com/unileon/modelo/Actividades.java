/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "actividades")
public class Actividades implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActividad;
    @Column(name = "Titulo")
    private String Titulo;
    @Column(name = "Precio")
    private String Precio;
    @Column(name = "Url")
    private String Url;
    @Column(name = "Direccion")
    private String Direccion;
    @Column(name = "Tipo")
    private String Tipo;
    @Lob
    @Column(name = "Imagen")
    private byte[] Imagen;

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
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

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.idActividad;
        hash = 73 * hash + Objects.hashCode(this.Titulo);
        hash = 73 * hash + Objects.hashCode(this.Precio);
        hash = 73 * hash + Objects.hashCode(this.Url);
        hash = 73 * hash + Objects.hashCode(this.Direccion);
        hash = 73 * hash + Objects.hashCode(this.Tipo);
        hash = 73 * hash + Arrays.hashCode(this.Imagen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Actividades other = (Actividades) obj;
        if (this.idActividad != other.idActividad) {
            return false;
        }
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        if (!Objects.equals(this.Precio, other.Precio)) {
            return false;
        }
        if (!Objects.equals(this.Url, other.Url)) {
            return false;
        }
        if (!Objects.equals(this.Direccion, other.Direccion)) {
            return false;
        }
        if (!Objects.equals(this.Tipo, other.Tipo)) {
            return false;
        }
        if (!Arrays.equals(this.Imagen, other.Imagen)) {
            return false;
        }
        return true;
    }

    

    

}
