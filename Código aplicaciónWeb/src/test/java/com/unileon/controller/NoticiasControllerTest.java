/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.modelo.Noticia;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class NoticiasControllerTest {
    
    public NoticiasControllerTest() {
    }

        @Test
    public void testEspacio() {
        NoticiasController a= new NoticiasController();
        Noticia act= new Noticia();
        act.setTitulo("a");
        assertTrue(a.espacio(act));
        act.setTitulo("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertFalse(a.espacio(act));
    }

}
