/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.ComentariosFacadeLocal;
import com.unileon.EJB.FavoritosFacadeLocal;
import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Favoritos;
import com.unileon.modelo.Noticia;
import com.unileon.modelo.Usuario;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@RequestScoped
public class NoticiasController implements Serializable{
   private Noticia noticia;
     private List<Noticia>listaNoticia;
     private Favoritos Fav;
     private Usuario usuario;
     private Comentarios comentario;
     List<Favoritos>favoritos;
     List<Comentarios>ListaComentarios;
     List<Comentarios>Comentarios;
     String comentar;
     @EJB
     private NoticiaFacadeLocal NoticiaEJB;
     @EJB
     private FavoritosFacadeLocal favoritosEJB;
     @EJB
     private ComentariosFacadeLocal comentariosEJB;
     
   
     @PostConstruct     
     public void init(){
       
       
         listaNoticia=NoticiaEJB.findAll();
         noticia=new Noticia();
         usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
         Fav = new Favoritos();
         comentario=new Comentarios();
         ListaComentarios=comentariosEJB.findAll();
        favoritos= favoritosEJB.findAll();
        Comentarios= new ArrayList<>();
       
     }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public List<Noticia> getListaNoticia() {
        return listaNoticia;
    }

    public void setListaNoticia(List<Noticia> listaNoticia) {
        this.listaNoticia = listaNoticia;
    }

    

    public Favoritos getFav() {
        return Fav;
    }

    public void setFav(Favoritos Fav) {
        this.Fav = Fav;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Comentarios getComentario() {
        return comentario;
    }

    public void setComentario(Comentarios comentario) {
        this.comentario = comentario;
    }

    public List<Favoritos> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favoritos> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Comentarios> getComentarios() {
        return Comentarios;
    }

    public void setComentarios(List<Comentarios> Comentarios) {
        this.Comentarios = Comentarios;
    }

    public String getComentar() {
        return comentar;
    }

    public void setComentar(String comentar) {
        this.comentar = comentar;
    }

    public NoticiaFacadeLocal getNoticiaEJB() {
        return NoticiaEJB;
    }

    public void setNoticiaEJB(NoticiaFacadeLocal NoticiaEJB) {
        this.NoticiaEJB = NoticiaEJB;
    }

    

    public void crearListaComentarios(Noticia Noticia) {
        comentario= new Comentarios();
        this.noticia=Noticia;
        Comentarios.clear();
        System.out.println(Noticia.getIdNoticia());
        for (int i = 0; i < ListaComentarios.size(); i++) {
            if(ListaComentarios.get(i).getNoticia()!=null){
            if (ListaComentarios.get(i).getNoticia().getIdNoticia()==Noticia.getIdNoticia()) {
                Comentarios.add(ListaComentarios.get(i));
            }
            }
        }
        System.out.println(Comentarios.size());
    }

    public List<Comentarios> getListaComentarios() {
        return ListaComentarios;
    }

    public void setListaComentarios(List<Comentarios> ListaComentarios) {
        this.ListaComentarios = ListaComentarios;
    }

    
    public byte[] obtenNoticia(Noticia Noticia) {
         //InputStream is = new ByteArrayInputStream(noticia.getImagen());
      /*    try {
          HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
      
           response.getOutputStream().write(Noticia.getImagen());
           StreamedContent content = DefaultStreamedContent.builder().contentType("image/png");
           
           response.getOutputStream().close();
          } catch (IOException ex) {
           Logger.getLogger(NoticiasController.class.getName()).log(Level.SEVERE, null, ex);
       }*/
       return Noticia.getImagen();

    }
    public byte[] obtenNoticia(){

       //return "/resources/Imagenes/Noticias/"+noticia.getIdNoticia()+".jpg";
       return noticia.getImagen();
    }
  /*   //descargar
    public void onPageLoad() {
        System.out.println("com.unileon.controller.NoticiaController.onPageLoad()aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        for (Noticia Noticia : listaNoticia) {
            try {
                
                File newFile= new File("C:\\Users\\Usuario\\Documents\\NetBeansProjects\\TFG\\src\\main\\webapp\\resources\\Imagenes\\Noticias\\"+Noticia.getIdNoticia()+".jpg");
            BufferedImage imag=ImageIO.read(new ByteArrayInputStream(Noticia.getImagen()));
            ImageIO.write(imag, "jpg", newFile);
                
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            a=false;
                  
        }
        
    }*/
    public boolean espacio(Noticia Noticia){
        
        if(Noticia.getTitulo().length()<38)
         return true;
        
        return false;
        
    }

    public void aniadirFav(Noticia noticia){
        System.out.println(usuario.getNombre());
        Fav.setUsuario(usuario);
        Fav.setNoticia(noticia);
        
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

    public boolean compruebaFav(Noticia Noticia) {
           if(!favoritos.isEmpty()){
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getNoticia()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getNoticia().getIdNoticia()==Noticia.getIdNoticia()){
                return true;
            }
            }
        }
           }
         return false;
    }

    private Favoritos obtenerFav() {
        for (int i = 0; i < favoritos.size(); i++) {
            if(favoritos.get(i).getNoticia()!=null){
            if(favoritos.get(i).getUsuario().getIdUsuario()==usuario.getIdUsuario() && favoritos.get(i).getNoticia().getIdNoticia()==noticia.getIdNoticia()){
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
            comentario.setNoticia(noticia);
            comentariosEJB.create(comentario);
            ListaComentarios=comentariosEJB.findAll();
            crearListaComentarios(noticia);
            comentar="";
            
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info", "El comentario esta vacio"));
        }
    }
   
     public boolean existenComentarios(){
         
         if(!ListaComentarios.isEmpty()&&noticia.getIdNoticia()!=0){
         for (int i = 0; i < ListaComentarios.size(); i++) {
             if(ListaComentarios.get(i).getNoticia()!=null){
            if (ListaComentarios.get(i).getNoticia().getIdNoticia()==noticia.getIdNoticia()) 
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
         crearListaComentarios(noticia);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha eliminado el comentario"));
     }
     
     public void modificarCom(){
         comentariosEJB.edit(comentario);
         ListaComentarios=comentariosEJB.findAll();
         crearListaComentarios(noticia);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha modificado el comentario"));
     }
    public boolean exitendatos(){
        if(listaNoticia.isEmpty()){
            return false;
        }
        return true;
    }
    public String obtenFecha(Noticia n){
        
        return fechaProcesada(n.getFecha());
        
    }
    public String obtenFecha(){
        
             return fechaProcesada(noticia.getFecha());
        
       
        
    }
    public String fechaProcesada(Date fecha) {
        if(fecha != null){
        String fechaP= fecha.toString();
        String[] a= fechaP.split(" ");
        String nueva= a[2]+" de "+ a[1]+" de "+a[a.length-1];
        System.out.println(nueva);
        return nueva;
        }
        return "a";
    }
     public boolean combruebaUs() {
        if(usuario.getTipo()==0){
            return true;
        }else{
            return false;
        }
    }
     
     public void eliminarNoticia(Noticia n){
         NoticiaEJB.remove(n);
         listaNoticia= NoticiaEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Noticia"));
     }
}
