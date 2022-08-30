/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

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
import org.primefaces.model.file.UploadedFile;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class ResgistratseController implements Serializable{
    
    private Usuario usuario;
     UploadedFile file;
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario=new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public String altaUsuario() {
        try{
            if(!existeUsuario()){
                usuarioEJB.create(usuario);
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El usuario se ha registrado correctamente"));
                 return "/Public/IniciarSesion.xhtml";
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de Usuario ya existe"));
                return "/Public/Registrarse.xhtml";
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }

    private boolean existeUsuario() {
         List<Usuario> Usuarios =usuarioEJB.findAll();
        
        for (int i = 0; i < Usuarios.size(); i++) {
            if(usuario.getUsuario().equalsIgnoreCase(Usuarios.get(i).getUsuario())){
                return true;
            }
        }
        return false;
    }
    
  
}
