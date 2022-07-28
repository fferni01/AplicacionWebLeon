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
@Table(name = "favoritoshoteles")
public class FavoritosHoteles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFavoritos;
    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario Usuario;

    @JoinColumn(name = "idhotel")
    @ManyToOne
    private Hoteles hotel;

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

    public Hoteles getHotel() {
        return hotel;
    }

    public void setHotel(Hoteles hotel) {
        this.hotel = hotel;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + this.idFavoritos;
        hash = 29 * hash + Objects.hashCode(this.Usuario);
        hash = 29 * hash + Objects.hashCode(this.hotel);
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
        final FavoritosHoteles other = (FavoritosHoteles) obj;
        if (this.idFavoritos != other.idFavoritos) {
            return false;
        }
        if (!Objects.equals(this.Usuario, other.Usuario)) {
            return false;
        }
        if (!Objects.equals(this.hotel, other.hotel)) {
            return false;
        }
        return true;
    }

   
}
