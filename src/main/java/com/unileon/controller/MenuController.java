/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.RutasFacadeLocal;
import com.unileon.modelo.Rutas;
import com.unileon.modelo.Usuario;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@SessionScoped
public class MenuController implements Serializable {

    Usuario usuario;

    @PostConstruct
    public void init() {

        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");

    }

    public String destruirSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("com.unileon.controller.MenuController.destruirSesion()");
        return "index.xhtml";
    }

    public String entrarPerfil() {
        return "/Private/Usuario/MiPerfil.xhtml?faces-redirect=true";
    }

    public String entrarNoticias() {
        return "/Private/Noticias.xhtml?faces-redirect=true";
    }

    public String entrarEventos() {
        return "/Private/Eventos.xhtml?faces-redirect=true";
    }

    public String entrarActividades() {
        return "/Private/Actividades.xhtml?faces-redirect=true";
    }

    public String entrarRestaurantes() {
        return "/Private/Restaurantes.xhtml?faces-redirect=true";
    }

    public String entrarParques() {
        return "/Private/Parques.xhtml?faces-redirect=true";
    }

    public String entrarRutas() {
        return "/Private/Rutas.xhtml?faces-redirect=true";

    }

    public String entrarCultura() {
        return "/Private/Cultura.xhtml?faces-redirect=true";
    }

    public String entrarAvisos() {
        return "/Private/Avisos.xhtml?faces-redirect=true";
    }

    public String entrarHoteles() {
        return "/Private/Hoteles.xhtml?faces-redirect=true";
    }

    public String verUsuariosAdm() {
        return "/Private/Admin/AdministrarUsuarios.xhtml?faces-redirect=true";
    }

    public String entrarPerfilAdm() {
        return "/Private/Admin/MiPerfil.xhtml?faces-redirect=true";
    }

    public String entrarFav() {
        return "/Private/Usuario/Favoritos.xhtml?faces-redirect=true";
    }

    public boolean combruebaUs() {
        if(usuario.getTipo()==0){
            return true;
        }else{
            return false;
        }
    }
}
