/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

import java.io.Serializable;
import java.util.Objects;
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
@Table(name = "favoritos")
public class Favoritos implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFav;
    @JoinColumn(name = "idUsuario")
    @ManyToOne
    private Usuario Usuario;

    @JoinColumn(name = "idCultura")
    @ManyToOne
    private Cultura Cultura;
    @JoinColumn(name = "idActividad")
    @ManyToOne
    private Actividades Actividad;
    @JoinColumn(name = "idEvento")
    @ManyToOne
    private Eventos Evento;
    @JoinColumn(name = "idNoticia")
    @ManyToOne
    private Noticia Noticia;
    @JoinColumn(name = "idDeportes")
    @ManyToOne
    private Deportes Deporte;
    @JoinColumn(name = "IdParques")
    @ManyToOne
    private Parques Parque;
    @JoinColumn(name = "idHoteles")
    @ManyToOne
    private Hoteles Hotel;
    @JoinColumn(name = "IdRuta")
    @ManyToOne
    private Rutas Ruta;
     @JoinColumn(name = "idRestaurantes")
     @ManyToOne
     private Restaurante Restaurante;

    public int getIdFav() {
        return idFav;
    }

    public void setIdFav(int idFav) {
        this.idFav = idFav;
    }

    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
    }

    public Cultura getCultura() {
        return Cultura;
    }

    public void setCultura(Cultura Cultura) {
        this.Cultura = Cultura;
    }

    public Actividades getActividad() {
        return Actividad;
    }

    public void setActividad(Actividades Actividad) {
        this.Actividad = Actividad;
    }

    public Eventos getEvento() {
        return Evento;
    }

    public void setEvento(Eventos Evento) {
        this.Evento = Evento;
    }

    public Noticia getNoticia() {
        return Noticia;
    }

    public void setNoticia(Noticia Noticia) {
        this.Noticia = Noticia;
    }

    public Deportes getDeporte() {
        return Deporte;
    }

    public void setDeporte(Deportes Deporte) {
        this.Deporte = Deporte;
    }

    public Parques getParque() {
        return Parque;
    }

    public void setParque(Parques Parque) {
        this.Parque = Parque;
    }

    public Hoteles getHotel() {
        return Hotel;
    }

    public void setHotel(Hoteles Hotel) {
        this.Hotel = Hotel;
    }

    public Rutas getRuta() {
        return Ruta;
    }

    public void setRuta(Rutas Ruta) {
        this.Ruta = Ruta;
    }

    public Restaurante getRestaurante() {
        return Restaurante;
    }

    public void setRestaurante(Restaurante Restaurante) {
        this.Restaurante = Restaurante;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idFav;
        hash = 59 * hash + Objects.hashCode(this.Usuario);
        hash = 59 * hash + Objects.hashCode(this.Cultura);
        hash = 59 * hash + Objects.hashCode(this.Actividad);
        hash = 59 * hash + Objects.hashCode(this.Evento);
        hash = 59 * hash + Objects.hashCode(this.Noticia);
        hash = 59 * hash + Objects.hashCode(this.Deporte);
        hash = 59 * hash + Objects.hashCode(this.Parque);
        hash = 59 * hash + Objects.hashCode(this.Hotel);
        hash = 59 * hash + Objects.hashCode(this.Ruta);
        hash = 59 * hash + Objects.hashCode(this.Restaurante);
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
        final Favoritos other = (Favoritos) obj;
        if (this.idFav != other.idFav) {
            return false;
        }
        if (!Objects.equals(this.Usuario, other.Usuario)) {
            return false;
        }
        if (!Objects.equals(this.Cultura, other.Cultura)) {
            return false;
        }
        if (!Objects.equals(this.Actividad, other.Actividad)) {
            return false;
        }
        if (!Objects.equals(this.Evento, other.Evento)) {
            return false;
        }
        if (!Objects.equals(this.Noticia, other.Noticia)) {
            return false;
        }
        if (!Objects.equals(this.Deporte, other.Deporte)) {
            return false;
        }
        if (!Objects.equals(this.Parque, other.Parque)) {
            return false;
        }
        if (!Objects.equals(this.Hotel, other.Hotel)) {
            return false;
        }
        if (!Objects.equals(this.Ruta, other.Ruta)) {
            return false;
        }
        if (!Objects.equals(this.Restaurante, other.Restaurante)) {
            return false;
        }
        return true;
    }

   

   

    
    
}
