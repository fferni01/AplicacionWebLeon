/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo.Favoritos;

import com.unileon.modelo.Actividades;
import com.unileon.modelo.Cultura;
import com.unileon.modelo.Deportes;
import com.unileon.modelo.Eventos;
import com.unileon.modelo.Hoteles;
import com.unileon.modelo.Noticia;
import com.unileon.modelo.Parques;
import com.unileon.modelo.Rutas;
import com.unileon.modelo.Usuario;
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
@Table(name = "favoritosactividad")
public class FavoritosActividad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavoritos;
    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario Usuario;

    @JoinColumn(name = "idActividad")
    @ManyToOne
    private Actividades Actividad;

    public int getIdFavoritos() {
        return idFavoritos;
    }

    public void setIdFavoritos(int idFavoritos) {
        this.idFavoritos = idFavoritos;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Actividades getActividad() {
        return Actividad;
    }

    public void setActividad(Actividades Actividad) {
        this.Actividad = Actividad;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.idFavoritos;
        hash = 17 * hash + Objects.hashCode(this.Usuario);
        hash = 17 * hash + Objects.hashCode(this.Actividad);
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
        final FavoritosActividad other = (FavoritosActividad) obj;
        if (this.idFavoritos != other.idFavoritos) {
            return false;
        }
        if (!Objects.equals(this.Usuario, other.Usuario)) {
            return false;
        }
        if (!Objects.equals(this.Actividad, other.Actividad)) {
            return false;
        }
        return true;
    }

   
}
