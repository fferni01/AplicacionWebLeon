/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.EJB.RestauranteFacadeLocal;
import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Favoritos;
import com.unileon.modelo.Restaurante;
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
public class RestaurantesController implements Serializable{
    private List<Restaurante>restaurantes;
     private Restaurante restaurante;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
      String comentar;
     @EJB
     private RestauranteFacadeLocal restaurantesEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         restaurantes=restaurantesEJB.findAll();
         restaurante=new Restaurante();
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
    public List<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
                this.restaurante = restaurante;
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

    public void crearListaComentarios(Restaurante restaurante) {
        comentario= new Comentarios();
        this.restaurante=restaurante;
        Comentarios.clear();
        System.out.println(restaurante.getIdRestaurante());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getRestaurante()!=null){
            if (ListaComentarios.get(i).getRestaurante().getIdRestaurante()==restaurante.getIdRestaurante()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public byte[] obtenRestaurante(Restaurante restaurante){
        return restaurante.getImagen();
      // return "/resources/Imagenes/Restaurante/"+restaurante.getIdRestaurante()+".jpg";
    }
    public byte[] obtenRestaurante(){
        return restaurante.getImagen();
      // return "/resources/Imagenes/Restaurante/"+restaurante.getIdRestaurante()+".jpg";
    }
     //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.RestauranteController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Restaurante restaurante : restaurantes) {
            try {
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Restaurante\\"+restaurante.getIdRestaurante()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(restaurante.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
                  
        }
        
    }
    public boolean espacio(Restaurante restaurante){
        
        if(restaurante.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setRestaurante(restaurante);
        
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

    public boolean compruebaFav(Restaurante restaurante) {
           
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getRestaurante()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getRestaurante().getIdRestaurante()==restaurante.getIdRestaurante()){
                return true;
            }
            }
        }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
             if(favoritos.get(i).getRestaurante()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getRestaurante().getIdRestaurante()==restaurante.getIdRestaurante()){
                return favoritos.get(i);
            }
             }
        }
         return null;
    }
    public boolean exitendatos(){
        if(restaurantes .isEmpty()){
            return false;
        }
        return true;
    }
    public void crearComentario(){
          if(comentar.length()!=0){
            comentario.setComentario(comentar);
            comentario.setUsuario(usuario);
            comentario.setRestaurante(restaurante);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(restaurante);
            comentar="";
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         System.out.println("com.unileon.controller.RestauranteController.existenComentarios()"+restaurante.getIdRestaurante());
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getRestaurante()!=null){
            if (ListaComentarios.get(i).getRestaurante().getIdRestaurante()==restaurante.getIdRestaurante()) 
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
         crearListaComentarios(restaurante);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(restaurante);
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
         restaurantesEJB.remove(restaurante);
         restaurantes= restaurantesEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Restaurante"));
     }
}
