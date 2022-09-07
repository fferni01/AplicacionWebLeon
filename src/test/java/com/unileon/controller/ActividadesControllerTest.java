/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Actividades;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ActividadesControllerTest {
    
    public ActividadesControllerTest() {
    }

   
    @Test
    public void testEspacio() {
        ActividadesController a= new ActividadesController();
        Actividades act= new Actividades();
        act.setTitulo("a");
        assertTrue(a.espacio(act));
        act.setTitulo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertFalse(a.espacio(act));
    }

    
}
