/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.admin;

import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class AdministrarUsuariosControllerTest {
    
    

    @Test
    public void testFechaProcesada() {
        Date d = new Date(116,5,5);
        AdministrarUsuariosController a = new AdministrarUsuariosController();
        assertEquals(a.fechaProcesada(d),"05 de Jun de 2016");
        
        
    }
    
}
