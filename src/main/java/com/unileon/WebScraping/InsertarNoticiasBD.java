/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.WebScraping;

import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Noticia;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class InsertarNoticiasBD implements Serializable{
    private Noticia noticia;
    
    @EJB
    private NoticiaFacadeLocal NoticiaEJB;

    @EJB
     private UsuarioFacadeLocal usuarioEJB;
    
    public InsertarNoticiasBD() {
       noticia = new Noticia();
       insertar();
    }
    


    public void insertar() {
        //try{
            noticia.setTitulo("a");
            noticia.setCuerpo("hola");
           System.out.println(NoticiaEJB);
           /* File f = new File("C:/Users/Usuario/Documents/NetBeansProjects/TFG/src/main/webapp/resources/Imagenes/i.jpg");
            InputStream is;
            is = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            
            int readers = is.read(buffer);
            
            //noticia.setImagen(buffer);*/
            

        //    NoticiaEJB.create(noticia);
            
            
        /*    System.out.println("completo");
        } catch (FileNotFoundException ex) {
            System.out.println("");
        } catch (IOException ex) {
            Logger.getLogger(InsertarNoticiasBD.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
