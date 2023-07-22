/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Usuario
 */
public class MenuControllerTest {


   
    @Test
    public void testEntrarPerfil() {
        System.out.println("entrarPerfil");
        MenuController instance = new MenuController();
        String expResult = "/Private/Usuario/MiPerfil.xhtml?faces-redirect=true";
        String result = instance.entrarPerfil();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarNoticias() {
     
        MenuController instance = new MenuController();
        String expResult = "/Private/Noticias.xhtml?faces-redirect=true";
        String result = instance.entrarNoticias();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarEventos() {
        System.out.println("entrarEventos");
        MenuController instance = new MenuController();
        String expResult = "/Private/Eventos.xhtml?faces-redirect=true";
        String result = instance.entrarEventos();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarActividades() {
        System.out.println("entrarActividades");
        MenuController instance = new MenuController();
        String expResult = "/Private/Actividades.xhtml?faces-redirect=true";
        String result = instance.entrarActividades();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarRestaurantes() {
        System.out.println("entrarRestaurantes");
        MenuController instance = new MenuController();
        String expResult = "/Private/Restaurantes.xhtml?faces-redirect=true";
        String result = instance.entrarRestaurantes();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarParques() {
        System.out.println("entrarParques");
        MenuController instance = new MenuController();
        String expResult = "/Private/Parques.xhtml?faces-redirect=true";
        String result = instance.entrarParques();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarRutas() {
        System.out.println("entrarRutas");
        MenuController instance = new MenuController();
        String expResult = "/Private/Rutas.xhtml?faces-redirect=true";
        String result = instance.entrarRutas();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarCultura() {
        System.out.println("entrarCultura");
        MenuController instance = new MenuController();
        String expResult = "/Private/Cultura.xhtml?faces-redirect=true";
        String result = instance.entrarCultura();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarAvisos() {
        System.out.println("entrarAvisos");
        MenuController instance = new MenuController();
        String expResult = "/Private/Avisos.xhtml?faces-redirect=true";
        String result = instance.entrarAvisos();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarHoteles() {
        System.out.println("entrarHoteles");
        MenuController instance = new MenuController();
        String expResult = "/Private/Hoteles.xhtml?faces-redirect=true";
        String result = instance.entrarHoteles();
        assertEquals(expResult, result);
    }

    @Test
    public void testVerUsuariosAdm() {
        System.out.println("verUsuariosAdm");
        MenuController instance = new MenuController();
        String expResult = "/Private/Admin/AdministrarUsuarios.xhtml?faces-redirect=true";
        String result = instance.verUsuariosAdm();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarPerfilAdm() {
        System.out.println("entrarPerfilAdm");
        MenuController instance = new MenuController();
        String expResult = "/Private/Admin/MiPerfil.xhtml?faces-redirect=true";
        String result = instance.entrarPerfilAdm();
        assertEquals(expResult, result);
    }

    @Test
    public void testEntrarFav() {
        System.out.println("entrarFav");
        MenuController instance = new MenuController();
        String expResult = "/Private/Usuario/Favoritos.xhtml?faces-redirect=true";
        String result = instance.entrarFav();
        assertEquals(expResult, result);
    }


    


   
    
}
