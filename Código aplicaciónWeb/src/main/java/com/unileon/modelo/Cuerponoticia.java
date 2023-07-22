/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "cuerponoticias")
public class Cuerponoticia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCuerpoNoticia;
    @Column(name = "texto")
    private String texto;
    @JoinColumn(name = "idNoticia")
    @ManyToOne
    private Noticia Noticia;

    public int getIdCuerpoNoticia() {
        return idCuerpoNoticia;
    }

    public void setIdCuerpoNoticia(int idCuerpoNoticia) {
        this.idCuerpoNoticia = idCuerpoNoticia;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Noticia getNoticia() {
        return Noticia;
    }

    public void setNoticia(Noticia Noticia) {
        this.Noticia = Noticia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + this.idCuerpoNoticia;
        hash = 89 * hash + Objects.hashCode(this.texto);
        hash = 89 * hash + Objects.hashCode(this.Noticia);
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
        final Cuerponoticia other = (Cuerponoticia) obj;
        if (this.idCuerpoNoticia != other.idCuerpoNoticia) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        if (!Objects.equals(this.Noticia, other.Noticia)) {
            return false;
        }
        return true;
    }

}
