/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ActividadesFacadeLocal;
import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.modelo.Actividades;
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
public class ActividadesController implements Serializable{
    private List<Actividades>actividades;
     private Actividades actividad;
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
     private ActividadesFacadeLocal ActividadesEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     @PostConstruct
     
     public void init(){
         tipos=new ArrayList<>();
         actividades=ActividadesEJB.findAll();
         actividad=new Actividades();
         usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         Fav = new Favoritos();
         comentario=new Comentarios();
         ListaComentarios=comentariosEJB.findAll();
        favoritos= favoritosEJB.findAll();
        Comentarios= new ArrayList<>();
         obtenTipoActividad();
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
           
            actividades.clear();
            System.out.println(selectedOption);
            actividades=ActividadesEJB.obtentipo(selectedOption);
        
    }

    public List<Actividades> getActividades() {
        return actividades;
    }

    public Actividades getActividad() {
        return actividad;
    }

    public void setActividad(Actividades Actividades) {
                this.actividad = Actividades;
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

    public void crearListaComentarios(Actividades Actividad) {
        comentario= new Comentarios();
        this.actividad=Actividad;
        Comentarios.clear();
        System.out.println(Actividad.getIdActividad());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getActividad()!=null){
            if (ListaComentarios.get(i).getActividad().getIdActividad()==Actividad.getIdActividad()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    
    public String obtenActividad(Actividades Actividad){
        
       return "/resources/Imagenes/Actividades/"+Actividad.getIdActividad()+".jpg";
    }
     //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.ActividadesController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Actividades Actividad : actividades) {
            try {
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Actividades\\"+Actividad.getIdActividad()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(Actividad.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            
                  
        }
        
    }
    public boolean espacio(Actividades Actividad){
        
        if(Actividad.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setActividad(actividad);
        
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

    public boolean compruebaFav(Actividades Actividad) {
           if(!favoritos.isEmpty()){
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getActividad()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getActividad().getIdActividad()==Actividad.getIdActividad()){
                return true;
            }
            }
        }
           }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getActividad()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getActividad().getIdActividad()==actividad.getIdActividad()){
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
            comentario.setActividad(actividad);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(actividad);
            comentar="";
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         
         if(!ListaComentarios.isEmpty()&&actividad.getIdActividad()!=0){
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getActividad()!=null){
            if (ListaComentarios.get(i).getActividad().getIdActividad()==actividad.getIdActividad()) 
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
         crearListaComentarios(actividad);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(actividad);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha modificado el comentario"));
     }
    public boolean exitendatos(){
        if(actividades.isEmpty()){
            return false;
        }
        return true;
    }

    private void obtenTipoActividad() {
         
          for (Actividades act : actividades) {
              if(!tipos.contains(act.getTipo())){
                  tipos.add(act.getTipo());
              }
            }
          }
    
}
