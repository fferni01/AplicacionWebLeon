/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.EJB.CulturaFacadeLocal;
import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Cultura;
import com.unileon.modelo.Favoritos;

import com.unileon.modelo.Usuario;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class CulturaController implements Serializable{
     private List<Cultura>culturas;
     private Cultura cultura;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
      String comentar;
     @EJB
     private CulturaFacadeLocal culturasEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         culturas=culturasEJB.findAll();
         cultura=new Cultura();
         usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         Fav = new Favoritos();
         comentario=new Comentarios();
         ListaComentarios=comentariosEJB.findAll();
        favoritos= favoritosEJB.findAll();
        Comentarios= new ArrayList<>();
     }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public List<Cultura> getCulturas() {
        return culturas;
    }

    public Cultura getCultura() {
        return cultura;
    }

    public void setCultura(Cultura cultura) {
                this.cultura = cultura;
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

    public void crearListaComentarios(Cultura cultura) {
        comentario= new Comentarios();
        this.cultura=cultura;
        Comentarios.clear();
        System.out.println(cultura.getIdCultura());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getCultura()!=null){
            if (ListaComentarios.get(i).getCultura().getIdCultura()==cultura.getIdCultura()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public byte[] obtenCultura(Cultura cultura){
        return cultura.getImagen();
      // return "/resources/Imagenes/Cultura/"+cultura.getIdCultura()+".jpg";
    }

    public boolean espacio(Cultura cultura){
        
        if(cultura.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setCultura(cultura);
        
        favoritosEJB.create(Fav);
        favoritos= favoritosEJB.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha añadido a Favoritos"));
    }
    public void eliminarFav(){
        Fav= obtenerFav();
        favoritosEJB.remove(Fav);
        favoritos= favoritosEJB.findAll();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado de Favoritos"));
    }
    public boolean crearIniciarSesion(){
        if(usuario==null){
            return true;
        }
        return false;
    }
    public boolean crearNormal(){
        if(usuario!=null){
            return true;
        }
        return false;
    }

    public boolean compruebaFav(Cultura cultura) {
           
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getCultura()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getCultura().getIdCultura()==cultura.getIdCultura()){
                return true;
            }
            }
        }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
             if(favoritos.get(i).getCultura()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getCultura().getIdCultura()==cultura.getIdCultura()){
                return favoritos.get(i);
            }
             }
        }
         return null;
    }
    public void crearComentario(){
        
       if(comentar.length()!=0){
            comentario.setComentario(comentar);
            comentario.setUsuario(usuario);
            comentario.setCultura(cultura);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(cultura);
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         System.out.println("com.unileon.controller.CulturaController.existenComentarios()"+cultura.getIdCultura());
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getCultura()!=null){
            if (ListaComentarios.get(i).getCultura().getIdCultura()==cultura.getIdCultura()) 
             return true;
         }
         }
             return false;
     }
     public boolean CompruebaUsuario(Comentarios a){
         if(a.getUsuario()!=null&&usuario!=null){
        if(usuario.getIdUsuario()==a.getUsuario().getIdUsuario()){
            return true;
        }
         }
        return false;
    }
     
     public void eliminarCom(Comentarios Com){
         comentariosEJB.remove(Com);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(cultura);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(cultura);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha modificado el comentario"));
     }
      public boolean combruebaUs() {
        if(usuario.getTipo()==0){
            return true;
        }else{
            return false;
        }
    }
       public void eliminarContenido(){
         culturasEJB.remove(cultura);
         culturas= culturasEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Cultura"));
     }
}
