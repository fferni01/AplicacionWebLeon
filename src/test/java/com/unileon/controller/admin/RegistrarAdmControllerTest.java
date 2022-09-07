/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.admin;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Usuario
 */
public class RegistrarAdmControllerTest {
    
    public RegistrarAdmControllerTest() {
    }
 @Test
    public void testContainsUpperCaseLetter() {
        RegistrarAdmController a = new RegistrarAdmController();
        assertTrue(a.containsUpperCaseLetter("a2A"));
        assertFalse(a.containsUpperCaseLetter("aaa"));
    }

    @Test
    public void testContainsLowerCaseLetter() {
                RegistrarAdmController a = new RegistrarAdmController();
        assertTrue(a.containsLowerCaseLetter("a2A"));
        assertFalse(a.containsLowerCaseLetter("AAA"));
    }

    @Test
    public void testContainsDigit() {
                RegistrarAdmController a = new RegistrarAdmController();
        assertTrue(a.containsDigit("a2A"));
        assertFalse(a.containsDigit("aaa"));
    }
    
}
