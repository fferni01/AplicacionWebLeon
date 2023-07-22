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
import java.util.Date;
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
    Usuario us;
    @EJB
    UsuarioFacadeLocal usuariosEJB;
    @PostConstruct
    public void init(){
        usuarios = usuariosEJB.obtenUsuariosN();
        for (Usuario usuario : usuarios) {
            System.out.println(usuario.getNombre());
        }
        
    }

    public Usuario getUs() {
        return us;
    }

    public void setUs(Usuario us) {
        this.us = us;
    }

 public void eliminarUsuario(){
     usuariosEJB.remove(us);
     usuarios=usuariosEJB.obtenUsuariosN();
 }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }
    
     public String obtenFecha(Usuario n){
        
        return fechaProcesada(n.getFechaAcceso());
        
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
    
}
