/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.admin;

import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.WebScraping.NoticiasWS;
import com.unileon.modelo.Noticia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class PrincipalAdminController implements Serializable{
    
    private static Noticia noticia;

    
    @EJB
    private NoticiaFacadeLocal noticiaEJB;
    
    
    @PostConstruct
    public void init(){
        noticia = new Noticia();
    
    }
    public void cargarWS(){
        System.out.println("com.unileon.controller.admin.PrincipalAdminController.CargarWS()");
        NoticiasWS n = new NoticiasWS();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modificacion realizada con exito"));
    }
   /* public byte[] mostrarfoto(){
        List<Noticia> noticias = noticiaEJB.findAll();
        for (int i = 0; i < noticias.size(); i++) {
            if(noticias.get(i).getIdNoticia()==3){
                return noticias.get(i).getImagen();
            }
        }
        return null;
    }
     public void descargarImagen() throws IOException {
    
           noticia.setTitulo("bs");
           noticia.setCuerpo("sfdsafd");

           
           
           try {
				// Url con la foto
				URL url = new URL(
						"https://lineadecodigo.com/wp-content/uploads/2014/02/java.png");

				// establecemos conexion
				URLConnection urlCon = url.openConnection();
                                urlCon.addRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
				// Sacamos por pantalla el tipo de fichero
				System.out.println(urlCon.getContentType());

				// Se obtiene el inputStream de la foto web y se abre el fichero
				// local.
				InputStream is = urlCon.getInputStream();
				FileOutputStream fos = new FileOutputStream("C:/Users/Usuario/Documents/NetBeansProjects/TFG/src/main/webapp/resources/Imagenes/foto.jpg");

				// Lectura de la foto de la web y escritura en fichero local
				byte[] array = new byte[1000]; // buffer temporal de lectura.
				int leido = is.read(array);
				while (leido > 0) {
					fos.write(array, 0, leido);
					leido = is.read(array);
				}

				// cierre de conexion y fichero.
				is.close();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
          
            
            
           File f = new File("C:/Users/Usuario/Documents/NetBeansProjects/TFG/src/main/webapp/resources/Imagenes/foto.jpg");
            InputStream is;
            is = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            
            int readers = is.read(buffer);
            noticia.setImagen(buffer);
                 noticiaEJB.create(noticia);
            
            
           System.out.println("completo");
        
    }*/
     
     
     
    
}
