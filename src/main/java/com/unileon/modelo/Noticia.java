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
@Table(name = "noticias")
public class Noticia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idNoticia;
    @Column(name = "Titulo")
    private String Titulo;
    @Column(name = "Cuerpo")
    private String Cuerpo;
    
    @Lob
    @Column(name = "Imagen")
    private byte[] imagen;

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

    public String getCuerpo() {
        return Cuerpo;
    }

    public void setCuerpo(String Cuerpo) {
        this.Cuerpo = Cuerpo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.idNoticia;
        hash = 79 * hash + Objects.hashCode(this.Titulo);
        hash = 79 * hash + Objects.hashCode(this.Cuerpo);
        hash = 79 * hash + Arrays.hashCode(this.imagen);
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
        final Noticia other = (Noticia) obj;
        if (this.idNoticia != other.idNoticia) {
            return false;
        }
        if (!Objects.equals(this.Titulo, other.Titulo)) {
            return false;
        }
        if (!Objects.equals(this.Cuerpo, other.Cuerpo)) {
            return false;
        }
        if (!Arrays.equals(this.imagen, other.imagen)) {
            return false;
        }
        return true;
    }
    
}
