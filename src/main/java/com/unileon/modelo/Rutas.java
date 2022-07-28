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
@Table(name = "rutas")
public class Rutas implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRuta;
    @Column(name = "Titulo")
    private String Titulo;
    @Column(name = "Url")
    private String Url;
    @Column(name = "Distancia")
    private Double Distancia;
    @Column(name = "Tiempo")
    private String Tiempo;
    @Lob
    @Column(name = "Imagen")
    private byte[] Imagen;

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

    public Double getDistancia() {
        return Distancia;
    }

    public void setDistancia(Double Distancia) {
        this.Distancia = Distancia;
    }

    public String getTiempo() {
        return Tiempo;
    }

    public void setTiempo(String Tiempo) {
        this.Tiempo = Tiempo;
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
        hash = 89 * hash + this.idRuta;
        hash = 89 * hash + Objects.hashCode(this.Titulo);
        hash = 89 * hash + Objects.hashCode(this.Url);
        hash = 89 * hash + Objects.hashCode(this.Distancia);
        hash = 89 * hash + Objects.hashCode(this.Tiempo);
        hash = 89 * hash + Arrays.hashCode(this.Imagen);
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
        final Rutas other = (Rutas) obj;
        if (this.idRuta != other.idRuta) {
            return false;
        }
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        if (!Objects.equals(this.Url, other.Url)) {
            return false;
        }
        if (!Objects.equals(this.Tiempo, other.Tiempo)) {
            return false;
        }
        if (!Objects.equals(this.Distancia, other.Distancia)) {
            return false;
        }
        if (!Arrays.equals(this.Imagen, other.Imagen)) {
            return false;
        }
        return true;
    }
    
}
