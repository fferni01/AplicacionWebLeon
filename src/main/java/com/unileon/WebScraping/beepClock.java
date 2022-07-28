/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.WebScraping;

import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.modelo.Noticia;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/**
 *
 * @author Usuario
 */
public class beepClock implements Runnable,Serializable{
    private Noticia noticia;
    
    @EJB
    private NoticiaFacadeLocal NoticiaEJB;
    
    @Override
    public void run() {
        System.out.println("intento");
        noticia = new Noticia();
        
        noticia.setTitulo("a");
        
        System.out.println(noticia.getTitulo());
        try {
            File f = new File("C:/Users/Usuario/Documents/NetBeansProjects/TFG/src/main/webapp/resources/Imagenes/i.jpg");
            InputStream is;
            is = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            try {
                int readers = is.read(buffer);
            } catch (IOException ex) {
                Logger.getLogger(beepClock.class.getName()).log(Level.SEVERE, null, ex);
            }
            noticia.setImagen(buffer);
            
        } catch (FileNotFoundException ex) {
            System.out.println("hola "+ ex);
        }
        try {
            
            NoticiaEJB.create(noticia);
        } catch (Exception e) {
            System.out.println(e);
        }
       
        System.out.println("completo");
        Toolkit.getDefaultToolkit().beep();
    }

   
}