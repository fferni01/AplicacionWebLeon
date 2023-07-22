/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.EJB.HotelesFacadeLocal;
import com.unileon.modelo.Comentarios;

import com.unileon.modelo.Favoritos;
import com.unileon.modelo.Hoteles;
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
public class HotelesController implements Serializable{
    private List<Hoteles>hoteles;
     private Hoteles hotel;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
      String comentar;
     @EJB
     private HotelesFacadeLocal hotelesEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         hoteles=hotelesEJB.findAll();
         hotel=new Hoteles();
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
    public List<Hoteles> getHoteles() {
        return hoteles;
    }

    public Hoteles getHotel() {
        return hotel;
    }

    public void setHotel(Hoteles hotel) {
                this.hotel = hotel;
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

    public void crearListaComentarios(Hoteles hotel) {
        comentario= new Comentarios();
        this.hotel=hotel;
        Comentarios.clear();
        System.out.println(hotel.getIdHoteles());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getHotel()!=null){
            if (ListaComentarios.get(i).getHotel().getIdHoteles()==hotel.getIdHoteles()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public byte[] obtenHotel(Hoteles hotel){
        return hotel.getImagen();
      // return "/resources/Imagenes/Hoteles/"+hotel.getIdHotel()+".jpg";
    }
    public byte[] obtenHotel(){
        return hotel.getImagen();
      // return "/resources/Imagenes/Hoteles/"+hotel.getIdHotel()+".jpg";
    }
     //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.HotelesController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Hoteles hotel : hoteles) {
            try {
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Hoteles\\"+hotel.getIdHoteles()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(hotel.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
                  
        }
        
    }
    public boolean espacio(Hoteles hotel){
        
        if(hotel.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setHotel(hotel);
        
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

    public boolean compruebaFav(Hoteles hotel) {
           
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getHotel()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getHotel().getIdHoteles()==hotel.getIdHoteles()){
                return true;
            }
            }
        }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
             if(favoritos.get(i).getHotel()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getHotel().getIdHoteles()==hotel.getIdHoteles()){
                return favoritos.get(i);
            }
             }
        }
         return null;
    }
    public boolean exitendatos(){
        if(hoteles .isEmpty()){
            return false;
        }
        return true;
    }
    public void crearComentario(){
          if(comentar.length()!=0){
            comentario.setComentario(comentar);
            comentario.setUsuario(usuario);
            comentario.setHotel(hotel);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(hotel);
            comentar="";
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         System.out.println("com.unileon.controller.HotelesController.existenComentarios()"+hotel.getIdHoteles());
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getHotel()!=null){
            if (ListaComentarios.get(i).getHotel().getIdHoteles()==hotel.getIdHoteles()) 
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
         crearListaComentarios(hotel);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(hotel);
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
         hotelesEJB.remove(hotel);
         hoteles= hotelesEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Hotel"));
     }
}
