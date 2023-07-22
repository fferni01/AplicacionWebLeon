/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Favoritos;
import com.unileon.modelo.Parques;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class FavoritosController implements Serializable {

    private Favoritos Fav;
    private Usuario usuario;
    private Comentarios comentario;
    List<Favoritos> favoritos;
    List<Favoritos> favoritosUsuario;
    List<Comentarios> ListaComentarios;
    List<Comentarios> Comentarios;
    String comentar;

    @EJB
    FavoritosFacadeLocal favoritosEJB;
    @EJB
    private ComentariosFacadeLocal comentariosEJB;

    @PostConstruct
    public void init() {
        favoritos = favoritosEJB.findAll();
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        Fav = new Favoritos();
        comentario = new Comentarios();
        ListaComentarios = comentariosEJB.findAll();
        Comentarios = new ArrayList<>();
        favoritosUsuario = new ArrayList<>();
        obtenFavUsu();
    }

    public Comentarios getComentario() {
        return comentario;
    }

    public void setComentario(Comentarios comentario) {
        this.comentario = comentario;
    }

    public List<Comentarios> getListaComentarios() {
        return ListaComentarios;
    }

    public List<Comentarios> getComentarios() {
        return Comentarios;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public List<Favoritos> getFavoritosUsuario() {
        return favoritosUsuario;
    }

    public void setFavoritosUsuario(List<Favoritos> favoritosUsuario) {
        this.favoritosUsuario = favoritosUsuario;
    }

    public Favoritos getFav() {
        return Fav;
    }

    public void setFav(Favoritos Fav) {
        this.Fav = Fav;
    }

    public void crearListaComentarios(Favoritos fav) {
        comentario = new Comentarios();
        this.Fav = fav;
        Comentarios.clear();
        if (fav.getActividad() != null) {

            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getActividad() != null) {
                    if (ListaComentarios.get(i).getActividad().getIdActividad() == fav.getActividad().getIdActividad()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }

        }
        if (fav.getCultura() != null) {
            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getCultura() != null) {
                    if (ListaComentarios.get(i).getCultura().getIdCultura() == fav.getCultura().getIdCultura()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }
        if (fav.getDeporte() != null) {
            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getDeporte() != null) {
                    if (ListaComentarios.get(i).getDeporte().getIdDeportes() == fav.getDeporte().getIdDeportes()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }
        if (fav.getEvento() != null) {
            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getEvento() != null) {
                    if (ListaComentarios.get(i).getEvento().getIdEvento() == fav.getEvento().getIdEvento()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }
        if (fav.getHotel() != null) {
            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getHotel() != null) {
                    if (ListaComentarios.get(i).getHotel().getIdHoteles() == fav.getHotel().getIdHoteles()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }
        if (fav.getNoticia() != null) {

            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getNoticia() != null) {
                    if (ListaComentarios.get(i).getNoticia().getIdNoticia() == fav.getNoticia().getIdNoticia()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }
        if (fav.getParque() != null) {

            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getParque() != null) {
                    if (ListaComentarios.get(i).getParque().getIdParque() == fav.getParque().getIdParque()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }

        }
        if (fav.getRestaurante() != null) {

            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getRestaurante()
                        != null) {
                    if (ListaComentarios.get(i).getRestaurante().getIdRestaurante() == fav.getRestaurante().getIdRestaurante()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }
        if (fav.getRuta() != null) {

            for (int i = 0; i < ListaComentarios.size(); i++) {
                if (ListaComentarios.get(i).getRuta()
                        != null) {
                    if (ListaComentarios.get(i).getRuta().getIdRuta() == fav.getRuta().getIdRuta()) {
                        Comentarios.add(ListaComentarios.get(i));
                    }
                }
            }
        }

    }

    public byte[] obtenFav(Favoritos fav) {

        //cambiar rutas
        if (fav.getActividad() != null) {
            return fav.getActividad().getImagen();
            //   return "/resources/Imagenes/Actividades/" + fav.getActividad().getIdActividad() + ".jpg";
        }
        if (fav.getCultura() != null) {
            return fav.getCultura().getImagen();

        }
        if (fav.getDeporte() != null) {
            return fav.getDeporte().getImagen();
        }
        if (fav.getEvento() != null) {
            return fav.getEvento().getImagen();
        }
        if (fav.getHotel() != null) {
            return fav.getHotel().getImagen();
        }
        if (fav.getNoticia() != null) {
            return fav.getNoticia().getImagen();
        }
        if (fav.getParque() != null) {
            return fav.getParque().getImagen();
        }
        if (fav.getRestaurante() != null) {
            return fav.getRestaurante().getImagen();
        }
        if (fav.getRuta() != null) {
            return fav.getRuta().getImagen();
        }
        return null;

    }

    public byte[] obtenFav() {

         if (Fav.getActividad() != null) {
            return Fav.getActividad().getImagen();
            //   return "/resources/Imagenes/Actividades/" + Fav.getActividad().getIdActividad() + ".jpg";
        }
        if (Fav.getCultura() != null) {
            return Fav.getCultura().getImagen();

        }
        if (Fav.getDeporte() != null) {
            return Fav.getDeporte().getImagen();
        }
        if (Fav.getEvento() != null) {
            return Fav.getEvento().getImagen();
        }
        if (Fav.getHotel() != null) {
            return Fav.getHotel().getImagen();
        }
        if (Fav.getNoticia() != null) {
            return Fav.getNoticia().getImagen();
        }
        if (Fav.getParque() != null) {
            return Fav.getParque().getImagen();
        }
        if (Fav.getRestaurante() != null) {
            return Fav.getRestaurante().getImagen();
        }
        if (Fav.getRuta() != null) {
            return Fav.getRuta().getImagen();
        }
        return null;

    }

    public boolean espacio(Favoritos fav) {

        if (fav.getActividad() != null) {
            if (fav.getActividad().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getCultura() != null) {
            if (fav.getCultura().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getDeporte() != null) {
            if (fav.getDeporte().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getEvento() != null) {
            if (fav.getEvento().getNombre().length() < 38) {
                return true;
            }
        }
        if (fav.getHotel() != null) {
            if (fav.getHotel().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getNoticia() != null) {
            if (fav.getNoticia().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getParque() != null) {
            if (fav.getParque().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getRestaurante() != null) {
            if (fav.getRestaurante().getTitulo().length() < 38) {
                return true;
            }
        }
        if (fav.getRuta() != null) {
            if (fav.getRuta().getTitulo().length() < 38) {
                return true;
            }
        }

        return false;

    }

    public String obtenNombre(Favoritos fav) {
        //cambiar rutas

        if (fav.getActividad() != null) {
            return fav.getActividad().getTitulo();
        }
        if (fav.getCultura() != null) {
            return fav.getCultura().getTitulo();
        }
        if (fav.getDeporte() != null) {
            return fav.getDeporte().getTitulo();
        }
        if (fav.getEvento() != null) {
            return fav.getEvento().getNombre();
        }
        if (fav.getHotel() != null) {
            return fav.getHotel().getTitulo();
        }
        if (fav.getNoticia() != null) {
            return fav.getNoticia().getTitulo();
        }
        if (fav.getParque() != null) {
            return fav.getParque().getTitulo();
        }
        if (fav.getRestaurante() != null) {
            return fav.getRestaurante().getTitulo();
        }
        if (fav.getRuta() != null) {
            return fav.getRuta().getTitulo();
        }

        return null;
    }

    public String obtenNombre() {
        //cambiar rutas
        if (Fav != null) {
            if (Fav.getActividad() != null) {
                return Fav.getActividad().getTitulo();
            }
            if (Fav.getCultura() != null) {
                return Fav.getCultura().getTitulo();
            }
            if (Fav.getDeporte() != null) {
                return Fav.getDeporte().getTitulo();
            }
            if (Fav.getEvento() != null) {
                return Fav.getEvento().getNombre();
            }
            if (Fav.getHotel() != null) {
                return Fav.getHotel().getTitulo();
            }
            if (Fav.getNoticia() != null) {
                return Fav.getNoticia().getTitulo();
            }
            if (Fav.getParque() != null) {
                return Fav.getParque().getTitulo();
            }
            if (Fav.getRestaurante() != null) {
                return Fav.getRestaurante().getTitulo();
            }
            if (Fav.getRuta() != null) {
                return Fav.getRuta().getTitulo();
            }
        }
        return null;
    }

    public String obtenDireccion(Favoritos fav) {
        //cambiar rutas
        if (fav.getActividad() != null) {
            return fav.getActividad().getDireccion();
        }
        if (fav.getCultura() != null) {
            return fav.getCultura().getDireccion();
        }
        if (fav.getDeporte() != null) {
            return fav.getDeporte().getDireccion();
        }
        if (fav.getEvento() != null) {
            return fav.getEvento().getDireccion();
        }
        if (fav.getHotel() != null) {
            return fav.getHotel().getDireccion();
        }
        if (fav.getNoticia() != null) {
            return fav.getNoticia().getFecha().toString();
        }
        if (fav.getParque() != null) {
            return fav.getParque().getDireccion();
        }
        if (fav.getRestaurante() != null) {
            return fav.getRestaurante().getDireccion();
        }
        if (fav.getRuta() != null) {
            return fav.getRuta().getDireccion();
        }
        return null;
    }

    public String obtenDireccion() {
        //cambiar rutas
        if (Fav.getActividad() != null) {
            return Fav.getActividad().getDireccion();
        }
        if (Fav.getCultura() != null) {
            return Fav.getCultura().getDireccion();
        }
        if (Fav.getDeporte() != null) {
            return Fav.getDeporte().getDireccion();
        }
        if (Fav.getEvento() != null) {
            return Fav.getEvento().getDireccion();
        }
        if (Fav.getHotel() != null) {
            return Fav.getHotel().getDireccion();
        }
        if (Fav.getNoticia() != null) {
            return Fav.getNoticia().getFecha().toString();
        }
        if (Fav.getParque() != null) {
            return Fav.getParque().getDireccion();
        }
        if (Fav.getRestaurante() != null) {
            return Fav.getRestaurante().getDireccion();
        }
        if (Fav.getRuta() != null) {
            return Fav.getRuta().getDireccion();
        }
        return null;
    }

    public String obtenUrl(Favoritos fav) {
        //cambiar rutas
        if (fav.getActividad() != null) {
            return fav.getActividad().getUrl();
        }
        if (fav.getCultura() != null) {
            return fav.getCultura().getUrl();
        }
        if (fav.getDeporte() != null) {
            return fav.getDeporte().getUrl();
        }
        if (fav.getEvento() != null) {
            return fav.getEvento().getUrl();
        }
        if (fav.getHotel() != null) {
            return fav.getHotel().getUrl();
        }
        if (fav.getNoticia() != null) {
            return "";
        }
        if (fav.getParque() != null) {
            return fav.getParque().getUrl();
        }
        if (fav.getRestaurante() != null) {
            return fav.getRestaurante().getUrl();
        }
        if (fav.getRuta() != null) {
            return fav.getRuta().getUrl();
        }
        return null;
    }

    public String obtenUrl() {
        //cambiar rutas
        if (Fav.getActividad() != null) {
            return Fav.getActividad().getUrl();
        }
        if (Fav.getCultura() != null) {
            return Fav.getCultura().getUrl();
        }
        if (Fav.getDeporte() != null) {
            return Fav.getDeporte().getUrl();
        }
        if (Fav.getEvento() != null) {
            return Fav.getEvento().getUrl();
        }
        if (Fav.getHotel() != null) {
            return Fav.getHotel().getUrl();
        }
        if (Fav.getNoticia() != null) {
            return "";
        }
        if (Fav.getParque() != null) {
            return Fav.getParque().getUrl();
        }
        if (Fav.getRestaurante() != null) {
            return Fav.getRestaurante().getUrl();
        }
        if (Fav.getRuta() != null) {
            return Fav.getRuta().getUrl();
        }
        return null;
    }

    public void eliminarFav() {
        favoritosEJB.remove(Fav);
        favoritos = favoritosEJB.findAll();
        obtenFavUsu();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado de Favoritos"));
    }

    public void crearComentario() {

        if (comentar.length() != 0) {
            if (Fav.getActividad() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setActividad(Fav.getActividad());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getCultura() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setCultura(Fav.getCultura());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getDeporte() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setDeporte(Fav.getDeporte());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getEvento() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setEvento(Fav.getEvento());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getHotel() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setHotel(Fav.getHotel());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getNoticia() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setNoticia(Fav.getNoticia());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getParque() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setParque(Fav.getParque());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getRestaurante() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setRestaurante(Fav.getRestaurante());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }
            if (Fav.getRuta() != null) {
                comentario.setComentario(comentar);
                comentario.setUsuario(usuario);
                comentario.setRuta(Fav.getRuta());
                comentariosEJB.create(comentario);
                ListaComentarios = comentariosEJB.findAll();
                crearListaComentarios(Fav);
                comentar = "";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }

    public boolean existenComentarios() {
        if (Fav.getActividad() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getActividad().getIdActividad() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getActividad() != null) {
                        if (ListaComentarios.get(i).getActividad().getIdActividad() == Fav.getActividad().getIdActividad()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getCultura() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getCultura().getIdCultura() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getCultura() != null) {
                        if (ListaComentarios.get(i).getCultura().getIdCultura() == Fav.getCultura().getIdCultura()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getDeporte() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getDeporte().getIdDeportes() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getDeporte() != null) {
                        if (ListaComentarios.get(i).getDeporte().getIdDeportes() == Fav.getDeporte().getIdDeportes()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getEvento() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getEvento().getIdEvento() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getEvento() != null) {
                        if (ListaComentarios.get(i).getEvento().getIdEvento() == Fav.getEvento().getIdEvento()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getHotel() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getHotel().getIdHoteles() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getHotel() != null) {
                        if (ListaComentarios.get(i).getHotel().getIdHoteles() == Fav.getHotel().getIdHoteles()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getNoticia() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getNoticia().getIdNoticia() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getNoticia() != null) {
                        if (ListaComentarios.get(i).getNoticia().getIdNoticia() == Fav.getNoticia().getIdNoticia()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getParque() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getParque().getIdParque() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getParque() != null) {
                        if (ListaComentarios.get(i).getParque().getIdParque() == Fav.getParque().getIdParque()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getRestaurante() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getRestaurante().getIdRestaurante() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getRestaurante() != null) {
                        if (ListaComentarios.get(i).getRestaurante().getIdRestaurante() == Fav.getRestaurante().getIdRestaurante()) {
                            return true;
                        }
                    }
                }
            }
        }
        if (Fav.getRuta() != null) {
            if (!ListaComentarios.isEmpty() && Fav.getRuta().getIdRuta() != 0) {
                for (int i = 0; i < ListaComentarios.size(); i++) {
                    if (ListaComentarios.get(i).getRuta() != null) {
                        if (ListaComentarios.get(i).getRuta().getIdRuta() == Fav.getRuta().getIdRuta()) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean CompruebaUsuario(Comentarios a) {
        if (usuario.getIdUsuario() == a.getUsuario().getIdUsuario()) {
            return true;
        }
        return false;
    }

    public void eliminarCom(Comentarios Com) {
        comentariosEJB.remove(Com);
        ListaComentarios = comentariosEJB.findAll();
        crearListaComentarios(Fav);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
    }

    public void modificarCom() {
        comentariosEJB.edit(comentario);
        ListaComentarios = comentariosEJB.findAll();
        crearListaComentarios(Fav);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha modificado el comentario"));
    }

    public boolean exitendatos() {
        if (favoritos.isEmpty()) {
            return false;
        }
        return true;
    }

    private void obtenFavUsu() {
        favoritosUsuario.clear();
        if (favoritos.size() > 0) {
            for (int i = 0; i < favoritos.size(); i++) {
                if (usuario.getIdUsuario() == favoritos.get(i).getUsuario().getIdUsuario()) {
                    favoritosUsuario.add(favoritos.get(i));
                }
            }
        }
    }
}
