/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.modelo;

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
@Table(name = "comentarios")
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentario;
    @Column(name = "Comentario")
    private String Comentario;
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
    @JoinColumn(name = "idParque")
    @ManyToOne
    private Parques Parque;
    @JoinColumn(name = "idHotel")
    @ManyToOne
    private Hoteles Hotel;
    @JoinColumn(name = "idRuta")
    @ManyToOne
    private Rutas Ruta;
     @JoinColumn(name = "idRestaurante")
     @ManyToOne
     private Restaurante Restaurante;

   

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
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

    public int getIdComentario() {
        return idComentario;
    }

    public void setIdComentario(int idComentario) {
        this.idComentario = idComentario;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.idComentario;
        hash = 53 * hash + Objects.hashCode(this.Comentario);
        hash = 53 * hash + Objects.hashCode(this.Usuario);
        hash = 53 * hash + Objects.hashCode(this.Cultura);
        hash = 53 * hash + Objects.hashCode(this.Actividad);
        hash = 53 * hash + Objects.hashCode(this.Evento);
        hash = 53 * hash + Objects.hashCode(this.Noticia);
        hash = 53 * hash + Objects.hashCode(this.Deporte);
        hash = 53 * hash + Objects.hashCode(this.Parque);
        hash = 53 * hash + Objects.hashCode(this.Hotel);
        hash = 53 * hash + Objects.hashCode(this.Ruta);
        hash = 53 * hash + Objects.hashCode(this.Restaurante);
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
        final Comentarios other = (Comentarios) obj;
        if (this.idComentario != other.idComentario) {
            return false;
        }
        if (!Objects.equals(this.Comentario, other.Comentario)) {
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
