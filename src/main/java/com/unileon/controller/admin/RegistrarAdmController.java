/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.admin;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
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
public class RegistrarAdmController implements Serializable{
     private Usuario usuario;
     private String nombre;
     private String apellidos;
     private String email;
     private String pass;
     @EJB
     UsuarioFacadeLocal usuariosEJB;
     
     @PostConstruct
     public void init() {
        usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
     
     public String modAdm(){
         
        if(pass.length()>=8){
        if (containsUpperCaseLetter(pass)&&containsLowerCaseLetter(pass)&&containsDigit(pass)) {
            System.out.println(nombre);
            usuario.setNombre(nombre);
            usuario.setApellido(apellidos);
            usuario.setEmail(email);
            usuario.setFechaAcceso(new Date());
                    usuariosEJB.edit(usuario);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El Administrador se ha registrado correctamente"));
                    return "/Private/Usuario/PrincipalUsuario.xhtml?faces-redirect=true";
                
      
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe contener minimo una mayuscula,una minuscula y un numero"));
        }   
        
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe contener minimo 8 letras"));
             
        }
        return null;
     }
     

     private boolean containsUpperCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsLowerCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean containsDigit(String s) {
        boolean containsDigit = false;
        if (s != null && !s.isEmpty()) {
            for (char c : s.toCharArray()) {
                if (containsDigit = Character.isDigit(c)) {
                    break;
                }
            }
        }
        return containsDigit;
    }
}
