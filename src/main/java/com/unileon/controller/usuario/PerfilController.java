/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.usuario;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
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
public class PerfilController implements Serializable{
      private Usuario usuario;
      private String Password;
      @EJB
      UsuarioFacadeLocal UsuarioEJB;
      @PostConstruct
    public void init(){
        usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");  
        
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

     public void modificarContraseniaUsuario(){
        try{
            usuario.setPassword(Password);
            UsuarioEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Contraseña modificada correctamente"));
        }catch(Exception e){
            
        }
    }

    public void modificar(){
        try{

            UsuarioEJB.edit(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Datos personales modificados correctamente"));
        }catch(Exception e){
            
        }
    }
}
