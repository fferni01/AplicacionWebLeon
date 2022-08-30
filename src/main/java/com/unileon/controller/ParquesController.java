/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.EJB.ParquesFacadeLocal;
import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Favoritos;
import com.unileon.modelo.Parques;
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
public class ParquesController implements Serializable{
    private List<Parques>parques;
     private Parques parque;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
     String comentar;
     @EJB
     private ParquesFacadeLocal ParquesEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         parques=ParquesEJB.findAll();
         parque=new Parques();
         usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         Fav = new Favoritos();
         comentario=new Comentarios();
         ListaComentarios=comentariosEJB.findAll();
        favoritos= favoritosEJB.findAll();
        Comentarios= new ArrayList<>();
     }

    public List<Parques> getParques() {
        return parques;
    }

    public Parques getParque() {
        return parque;
    }

    public void setParque(Parques Parques) {
                this.parque = Parques;
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

    public void crearListaComentarios(Parques Parque) {
        comentario= new Comentarios();
        this.parque=Parque;
        Comentarios.clear();
        System.out.println(Parque.getIdParque());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getParque()!=null){
            if (ListaComentarios.get(i).getParque().getIdParque()==Parque.getIdParque()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public String obtenParque(Parques Parque){
        
       return "/resources/Imagenes/Parques/"+Parque.getIdParque()+".jpg";
    }
     //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.ParquesController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Parques Parque : parques) {
            try {
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Parques\\"+Parque.getIdParque()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(Parque.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
                  
        }
        
    }
    public boolean espacio(Parques Parque){
        
        if(Parque.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setParque(parque);
        
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

    public boolean compruebaFav(Parques Parque) {
           if(!favoritos.isEmpty()){
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getParque()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getParque().getIdParque()==Parque.getIdParque()){
                return true;
            }
            }
        }
           }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getParque()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getParque().getIdParque()==parque.getIdParque()){
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
            comentario.setParque(parque);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(parque);
            comentar="";
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         
         if(!ListaComentarios.isEmpty()&&parque.getIdParque()!=0){
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getParque()!=null){
            if (ListaComentarios.get(i).getParque().getIdParque()==parque.getIdParque()) 
             return true;
         }
         }
         }
             return false;
     }
     public boolean CompruebaUsuario(Comentarios a){
        if(usuario==a.getUsuario()){
            return true;
        }
        return false;
    }
     
     public void eliminarCom(Comentarios Com){
         comentariosEJB.remove(Com);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(parque);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(parque);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha modificado el comentario"));
     }
    public boolean exitendatos(){
        if(parques.isEmpty()){
            return false;
        }
        return true;
    }
}
