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
@Table(name = "eventos")
public class Eventos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvento;
    @Column(name = "Nombre")
    private String Nombre;
    @Column(name = "Url")
    private double Url;
    @Column(name = "Precio")
    private String Precio;
    @Column(name = "Direccion")
    private String Direccion;
    @Column(name = "Tipo")
    private String Tipo;

    @Lob
    @Column(name = "Imagen")
    private byte[] Imagen;
    @Column(name = "Hora")
    @Temporal(TemporalType.TIME)
    private Date Hora;
    @Column(name = "Fecha")
    @Temporal(TemporalType.DATE)
    private Date Fecha;

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getUrl() {
        return Url;
    }

    public void setUrl(double Url) {
        this.Url = Url;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] Imagen) {
        this.Imagen = Imagen;
    }

    public Date getHora() {
        return Hora;
    }

    public void setHora(Date Hora) {
        this.Hora = Hora;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.idEvento;
        hash = 67 * hash + Objects.hashCode(this.Nombre);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.Url) ^ (Double.doubleToLongBits(this.Url) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.Precio);
        hash = 67 * hash + Objects.hashCode(this.Direccion);
        hash = 67 * hash + Objects.hashCode(this.Tipo);
        hash = 67 * hash + Arrays.hashCode(this.Imagen);
        hash = 67 * hash + Objects.hashCode(this.Hora);
        hash = 67 * hash + Objects.hashCode(this.Fecha);
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
        final Eventos other = (Eventos) obj;
        if (this.idEvento != other.idEvento) {
            return false;
        }
        if (Double.doubleToLongBits(this.Url) != Double.doubleToLongBits(other.Url)) {
            return false;
        }
        if (!Objects.equals(this.Nombre, other.Nombre)) {
            return false;
        }
        if (!Objects.equals(this.Precio, other.Precio)) {
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
        if (!Objects.equals(this.Hora, other.Hora)) {
            return false;
        }
        if (!Objects.equals(this.Fecha, other.Fecha)) {
            return false;
        }
        return true;
    }
    
    
}
