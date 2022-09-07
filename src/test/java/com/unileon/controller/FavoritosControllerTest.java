/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Actividades;
import com.unileon.modelo.Favoritos;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class FavoritosControllerTest {
    
    public FavoritosControllerTest() {
    }

        @Test
    public void testEspacio() {
        FavoritosController a= new FavoritosController();
        Favoritos fev = new Favoritos();
        
        Actividades act= new Actividades();
        act.setTitulo("a");
        fev.setActividad(act);
        assertTrue(a.espacio(fev));
        act.setTitulo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
         fev.setActividad(act);
        assertFalse(a.espacio(fev));
    }

    
}
