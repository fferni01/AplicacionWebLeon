/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.EventosFacadeLocal;
import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.modelo.Eventos;
import com.unileon.modelo.Comentarios;
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
public class EventosController implements Serializable{
     private List<Eventos>eventos;
     private Eventos evento;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
     String comentar;
     String selectedOption;
     List<String>tipos;
     @EJB
     private EventosFacadeLocal EventosEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         tipos=new ArrayList<>();
         eventos=EventosEJB.findAll();
         evento=new Eventos();
         usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         Fav = new Favoritos();
         comentario=new Comentarios();
         ListaComentarios=comentariosEJB.findAll();
        favoritos= favoritosEJB.findAll();
        Comentarios= new ArrayList<>();
         obtenTipoEvento();
     }

    public List<String> getTipos() {
        return tipos;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }



    public void opcionElegida() {
           
            eventos.clear();
            System.out.println(selectedOption);
            if(selectedOption.equals("Otros eventos")){
                 eventos=EventosEJB.obtentipo("");
            }else if(selectedOption.equals("Ver Todo")){
                 eventos=EventosEJB.findAll();
            }else{
                 eventos=EventosEJB.obtentipo(selectedOption);
            }
          
        
    }

    public List<Eventos> getEventos() {
        return eventos;
    }

    public Eventos getEvento() {
        return evento;
    }

    public void setEvento(Eventos Eventos) {
                this.evento = Eventos;
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

    public void crearListaComentarios(Eventos Evento) {
        comentario= new Comentarios();
        this.evento=Evento;
        Comentarios.clear();
        System.out.println(Evento.getIdEvento());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getEvento()!=null){
            if (ListaComentarios.get(i).getEvento().getIdEvento()==Evento.getIdEvento()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public byte[] obtenEvento(Eventos Evento){
        
      // return "/resources/Imagenes/Eventos/"+Evento.getIdEvento()+".jpg";
       // System.err.println(Evento.getTitulo());
      return Evento.getImagen();
    }
     public byte[] obtenEvento(){
        
      // return "/resources/Imagenes/Eventos/"+Evento.getIdEvento()+".jpg";
      return evento.getImagen();
    }
     //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.EventosController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Eventos Evento : eventos) {
            try {
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Eventos\\"+Evento.getIdEvento()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(Evento.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
                  
        }
        
    }
    public boolean espacio(Eventos Evento){
        
        if(Evento.getNombre().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setEvento(evento);
        
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

    public boolean compruebaFav(Eventos Evento) {
           if(!favoritos.isEmpty()){
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getEvento()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getEvento().getIdEvento()==Evento.getIdEvento()){
                return true;
            }
            }
        }
           }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getEvento()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getEvento().getIdEvento()==evento.getIdEvento()){
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
            comentario.setEvento(evento);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(evento);
            comentar="";
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         
         if(!ListaComentarios.isEmpty()&&evento.getIdEvento()!=0){
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getEvento()!=null){
            if (ListaComentarios.get(i).getEvento().getIdEvento()==evento.getIdEvento()) 
             return true;
         }
         }
         }
             return false;
     }
     public boolean CompruebaUsuario(Comentarios a){
        if(usuario.getIdUsuario()==a.getUsuario().getIdUsuario()){
            return true;
        }
        return false;
    }
     
     public void eliminarCom(Comentarios Com){
         comentariosEJB.remove(Com);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(evento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(evento);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha modificado el comentario"));
     }
    public boolean exitendatos(){
        if(eventos.isEmpty()){
            return false;
        }
        return true;
    }

    private void obtenTipoEvento() {
         tipos.add("Ver Todo");
          for (Eventos act : eventos) {
              if(!tipos.contains(act.getTipo())){
                  if(act.getTipo().equals("")){
                      tipos.add("Otros eventos");
                  }else{
                      tipos.add(act.getTipo());
                  }
                  
              }
            }
          }
     public boolean combruebaUs() {
        if(usuario.getTipo()==0){
            return true;
        }else{
            return false;
        }
    }
     public void eliminarContenido(){
         EventosEJB.remove(evento);
         eventos= EventosEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Eventos"));
     }
}
