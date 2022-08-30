/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.admin;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class AdministrarUsuariosController implements Serializable{
    
    private List<Usuario> usuarios;
    
    @EJB
    UsuarioFacadeLocal usuariosEJB;
    @PostConstruct
    public void init(){
        usuarios = usuariosEJB.obtenUsuariosN();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }
        
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
}
