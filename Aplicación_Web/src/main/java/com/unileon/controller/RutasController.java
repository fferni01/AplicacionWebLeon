/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.EJB.RutasFacadeLocal;
import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Favoritos;
import com.unileon.modelo.Rutas;
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
public class RutasController implements Serializable{
    
     private List<Rutas>rutas;
     private Rutas ruta;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
     String comentar;
     @EJB
     private RutasFacadeLocal rutasEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         rutas=rutasEJB.findAll();
         ruta=new Rutas();
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
    public List<Rutas> getRutas() {
        return rutas;
    }

    public Rutas getRuta() {
        return ruta;
    }

    public void setRuta(Rutas ruta) {
                this.ruta = ruta;
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

    public void crearListaComentarios(Rutas ruta) {
        comentario= new Comentarios();
        this.ruta=ruta;
        Comentarios.clear();
        System.out.println(ruta.getIdRuta());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getRuta()!=null){
            if (ListaComentarios.get(i).getRuta().getIdRuta()==ruta.getIdRuta()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public byte[] obtenRuta(Rutas ruta){
        return ruta.getImagen();
      // return "/resources/Imagenes/Rutas/"+ruta.getIdRuta()+".jpg";
    }
     //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.RutasController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Rutas ruta : rutas) {
            try {
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Rutas\\"+ruta.getIdRuta()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(ruta.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
                  
        }
        
    }
    public boolean espacio(Rutas ruta){
        
        if(ruta.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setRuta(ruta);
        
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

    public boolean compruebaFav(Rutas ruta) {
           
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getRuta()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getRuta().getIdRuta()==ruta.getIdRuta()){
                return true;
            }
            }
        }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
             if(favoritos.get(i).getRuta()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getRuta().getIdRuta()==ruta.getIdRuta()){
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
            comentario.setRuta(ruta);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(ruta);
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         System.out.println("com.unileon.controller.RutasController.existenComentarios()"+ruta.getIdRuta());
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getRuta()!=null){
            if (ListaComentarios.get(i).getRuta().getIdRuta()==ruta.getIdRuta()) 
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
         crearListaComentarios(ruta);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(ruta);
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
         rutasEJB.remove(ruta);
         rutas= rutasEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Noticia"));
     }
}
 
