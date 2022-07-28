/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tiempo")
public class Tiempo implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTiempo;
    @Column(name = "TemperaturaMax")
    private String Titulo;
    @Column(name = "Url")
    private String Url;
    @Column(name = "TemperaturaMin")
    private Double Distancia;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date Fecha;
    @Column(name = "viento")
    private String Viento;
    @Column(name = "TipoViento")
    private String TipoViento;
    @Lob
    @Column(name = "Imagen")
    private byte[] Imagen;

    public int getIdTiempo() {
        return idTiempo;
    }

    public void setIdTiempo(int idTiempo) {
        this.idTiempo = idTiempo;
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

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getViento() {
        return Viento;
    }

    public void setViento(String Viento) {
        this.Viento = Viento;
    }

    public String getTipoViento() {
        return TipoViento;
    }

    public void setTipoViento(String TipoViento) {
        this.TipoViento = TipoViento;
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
        hash = 23 * hash + this.idTiempo;
        hash = 23 * hash + Objects.hashCode(this.Titulo);
        hash = 23 * hash + Objects.hashCode(this.Url);
        hash = 23 * hash + Objects.hashCode(this.Distancia);
        hash = 23 * hash + Objects.hashCode(this.Fecha);
        hash = 23 * hash + Objects.hashCode(this.Viento);
        hash = 23 * hash + Objects.hashCode(this.TipoViento);
        hash = 23 * hash + Arrays.hashCode(this.Imagen);
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
        final Tiempo other = (Tiempo) obj;
        if (this.idTiempo != other.idTiempo) {
            return false;
        }
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        if (!Objects.equals(this.Url, other.Url)) {
            return false;
        }
        if (!Objects.equals(this.Viento, other.Viento)) {
            return false;
        }
        if (!Objects.equals(this.TipoViento, other.TipoViento)) {
            return false;
        }
        if (!Objects.equals(this.Distancia, other.Distancia)) {
            return false;
        }
        if (!Objects.equals(this.Fecha, other.Fecha)) {
            return false;
        }
        if (!Arrays.equals(this.Imagen, other.Imagen)) {
            return false;
        }
        return true;
    }

    
}
