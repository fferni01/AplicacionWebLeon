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
public class ResgistratseController implements Serializable {

    private Usuario usuario;

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String altaUsuario() {
        if(usuario.getPassword().length()>=8){
        if (containsUpperCaseLetter(usuario.getPassword())&&containsLowerCaseLetter(usuario.getPassword())&&containsDigit(usuario.getPassword())) {
            try {
                if (!existeUsuario()) {
                    usuarioEJB.create(usuario);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "El usuario se ha registrado correctamente"));
                    return "/index.xhtml";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El nombre de Usuario ya existe"));
                    return "/Public/Registrarse.xhtml";
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe contener minimo una mayuscula,una minuscula y un numero"));
                  
        }
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña debe contener minimo 8 letras"));
             
        }
        return null;
    }

    private boolean existeUsuario() {
        List<Usuario> Usuarios = usuarioEJB.findAll();

        for (int i = 0; i < Usuarios.size(); i++) {
            if (usuario.getUsuario().equalsIgnoreCase(Usuarios.get(i).getUsuario())) {
                return true;
            }
        }
        return false;
    }

    public boolean containsUpperCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean containsLowerCaseLetter(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLowerCase(s.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public boolean containsDigit(String s) {
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
