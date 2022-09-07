/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.controller.admin.RegistrarAdmController;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Usuario
 */
public class ResgistratseControllerTest {
    
    public ResgistratseControllerTest() {
    }
    


    @Test
    public void testContainsUpperCaseLetter() {
        ResgistratseController a = new ResgistratseController();
        assertTrue(a.containsUpperCaseLetter("a2A"));
        assertFalse(a.containsUpperCaseLetter("aaa"));
    }

    @Test
    public void testContainsLowerCaseLetter() {
                ResgistratseController a = new ResgistratseController();
        assertTrue(a.containsLowerCaseLetter("a2A"));
        assertFalse(a.containsLowerCaseLetter("AAA"));
    }

    @Test
    public void testContainsDigit() {
                ResgistratseController a = new ResgistratseController();
        assertTrue(a.containsDigit("a2A"));
        assertFalse(a.containsDigit("aaa"));
    }
    
}
