/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Cultura;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class CulturaControllerTest {
    
    public CulturaControllerTest() {
    }

       @Test
    public void testEspacio() {
        CulturaController a= new CulturaController();
        Cultura act= new Cultura();
        act.setTitulo("a");
        assertTrue(a.espacio(act));
        act.setTitulo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertFalse(a.espacio(act));
    }

    
}
