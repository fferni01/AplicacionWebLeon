/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@SessionScoped
public class IniciarSesionController implements Serializable{
    private Usuario usuario;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
     public String verificarUsuario(){
        Usuario nuevo;
       
        nuevo = usuarioEJB.consultarUsuario(usuario);
        if(nuevo == null)
            System.out.println("privado/error.xhtml"); 
        else
            System.out.println("correcto.xhtml"); 
        //return "index";
       if(nuevo == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrecta"));
            //return "index.sw2";
       }        
       else{
           if(EsAdmin(nuevo)){
               return "/Private/Admin/PrincipalAdmin.xhtml?faces-redirect=true";
           }else
            return "/Private/Usuario/PrincipalUsuario.xhtml?faces-redirect=true";
       }
        return null;
        
    }
     
    public String destruirSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("com.unileon.controller.MenuController.destruirSesion()");
        return "../../index.xhtml";
    }

    private boolean EsAdmin(Usuario nuevo) {
      if(nuevo.getTipo()==1){
          return true;
      }else{
          return false;
      }
    }
}
