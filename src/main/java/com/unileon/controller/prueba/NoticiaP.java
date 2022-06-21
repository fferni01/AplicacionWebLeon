/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.prueba;

import javax.annotation.PostConstruct;

/**
 *
 * @author Usuario
 */
public class NoticiaP {
    private String name;
    private String description;
    private String imagen;

    public NoticiaP(String nombre, String foto, String aqui) {
       name=nombre;
       imagen=foto;
       description=aqui;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
   
}

