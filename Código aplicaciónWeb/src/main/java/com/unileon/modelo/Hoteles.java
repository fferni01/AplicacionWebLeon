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
@Table(name = "hoteles")
public class Hoteles implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHoteles;
    @Column(name = "Titulo")
    private String Titulo;
    @Column(name = "Url")
    private String Url;
    @Column(name = "Direccion")
    private String Direccion;
    @Lob
    @Column(name = "Imagen")
    private byte[] Imagen;

    public int getIdHoteles() {
        return idHoteles;
    }

    public void setIdHoteles(int idHoteles) {
        this.idHoteles = idHoteles;
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

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.idHoteles;
        hash = 29 * hash + Objects.hashCode(this.Titulo);
        hash = 29 * hash + Objects.hashCode(this.Url);
        hash = 29 * hash + Objects.hashCode(this.Direccion);
        hash = 29 * hash + Arrays.hashCode(this.Imagen);
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
        final Hoteles other = (Hoteles) obj;
        if (this.idHoteles != other.idHoteles) {
            return false;
        }
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        if (!Objects.equals(this.Url, other.Url)) {
            return false;
        }
        if (!Objects.equals(this.Direccion, other.Direccion)) {
            return false;
        }
        if (!Arrays.equals(this.Imagen, other.Imagen)) {
            return false;
        }
        return true;
    }
    
}
