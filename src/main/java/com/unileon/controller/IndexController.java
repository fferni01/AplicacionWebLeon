/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;



import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.WebScraping.InsertarNoticiasBD;
import com.unileon.WebScraping.beepClock;
import com.unileon.controller.prueba.NoticiaP;
import com.unileon.modelo.Noticia;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;

import javax.inject.Named;
import org.primefaces.model.ResponsiveOption;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class IndexController implements Serializable{
     private List<ResponsiveOption> responsiveOptions;
     private List<NoticiaP> lista;
     private List<NoticiaP> Museos;
     private List<NoticiaP> Eventos;
     
     @EJB
     private NoticiaFacadeLocal NoticiaEJB;
     
     @PostConstruct
    public void init() {
        /* try {
             //BeepClock();

           //  creaNoticia();
         } catch (IOException ex) {
             Logger.getLogger(IndexController.class.getName()).log(Level.SEVERE, null, ex);
         }*/
     
        rellenaLista();
         System.out.println(lista.get(0).getName());
        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1400px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("1100px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("750px", 1, 1));
    }

     /*void BeepClock() {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new beepClock();
        int initialDelay = 1000000;
        int periodicDelay = 1000000;
        scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,
                TimeUnit.SECONDS
        );
    }*/
    public List<NoticiaP> getLista() {
        return lista;
    }

    public void setLista(List<NoticiaP> lista) {
        this.lista = lista;
    }

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public void setResponsiveOptions(List<ResponsiveOption> responsiveOptions) {
        this.responsiveOptions = responsiveOptions;
    }

    public List<NoticiaP> getMuseos() {
        return Museos;
    }

    public void setMuseos(List<NoticiaP> Museos) {
        this.Museos = Museos;
    }

    public List<NoticiaP> getEventos() {
        return Eventos;
    }

    public void setEventos(List<NoticiaP> Eventos) {
        this.Eventos = Eventos;
    }
    
      public String Ruta() {
        
                return "public/Principal";
          
        
    }

    private void rellenaLista() {
        this.lista = new ArrayList<NoticiaP>();
        lista.add(new NoticiaP("Los obispos de la provincia eclesiástica impulsan desde León los servicios para el catecumenado de adultos ","https://static2.leonoticias.com/www/multimedia/202205/12/media/cortadas/Encuentro%20Obispos%20Provincia%20Eclesi%c3%a1stica_San%20Isidoro_12-5-22-ktpB--228x141@Leonoticias.jpg",""));
        lista.add(new NoticiaP("Rogelio Blanco: «En nuestros pueblos las campanas tocan más a difunto que a bautizo» ","https://static3.leonoticias.com/www/multimedia/202205/12/media/cortadas/WhatsApp%20Image%202022-05-12%20at%2010.44.52%20AM-kGRB--228x141@Leonoticias.jpeg",""));
       lista.add(new NoticiaP("Finaliza el simulacro de incendio forestal que ha reunido a 430 profesionales de seis países en Santa Colomba","https://static3.leonoticias.com/www/multimedia/202205/12/media/cortadas/_03MAX202205562619-ktkF--228x141@Leonoticias.jpg",""));
       lista.add(new NoticiaP("El PP critica la ausencia del alcalde en el Congreso de Participación Ciudadana ","https://static.leonoticias.com/www/multimedia/202205/12/media/cortadas/IMG_2902-kpSB--228x141@Leonoticias.jpg",""));
       lista.add(new NoticiaP("Los dorsales del 'Día de la bici en familia' se recogen viernes y sábado","https://static2.leonoticias.com/www/multimedia/202205/12/media/cortadas/bicis-kabF--228x141@Leonoticias.jpg",""));
       lista.add(new NoticiaP("Le educación da el primer paso en el 'paro por León","https://cf-images.eu-west-1.prod.boltdns.net/v1/static/802565294001/83b2cf3b-5101-4a1b-8ff1-e00caad9b0ef/dfbb0c55-2140-4194-8a6a-66d352ac90c2/224x126/match/image.jpg",""));
       this.Museos= new ArrayList<NoticiaP>();
        Museos.add(new NoticiaP("MUSAC","https://www.diariodeleon.es/asset/thumbnail,992,558,center,center//media/diariodeleon/images/2018/11/12//1291724_1.jpg",""));
        Museos.add(new NoticiaP("Museo de León","https://www.diariodeleon.es/asset/thumbnail,992,558,center,center//media/diariodeleon/images/2019/08/30//2019083002023034007.jpg",""));
        Museos.add(new NoticiaP("Museo Catedralicio de León","https://upload.wikimedia.org/wikipedia/commons/9/93/Museo_catedralicio-diocesano.jpg?20110719214358",""));
          Museos.add(new NoticiaP("Casa Botines","http://www.españaescultura.es/export/sites/cultura/multimedia/galerias/monumentos/Botines-0032-c.jpg_1306973099.jpg",""));
        Museos.add(new NoticiaP("Centro de Interpretación León Romano ","https://www.diariodeleon.es/asset/thumbnail,1280,720,center,center//media/diariodeleon/images/2019/03/03//1318148_1.jpg",""));
        Museos.add(new NoticiaP("Museo de la Colegiata de San Isidoro","https://upload.wikimedia.org/wikipedia/commons/thumb/7/79/20060626-Le%C3%B3n_San_Isidoro.jpg/1200px-20060626-Le%C3%B3n_San_Isidoro.jpg",""));
        this.Eventos= new ArrayList<NoticiaP>();
        Eventos.add(new NoticiaP("Mr. Kilombo", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-t7NUmYozugVOtyrDF27HrsjQIIk_Xg7Xmr2khfOFbg&s=10", ""));
        Eventos.add(new NoticiaP("Huecco", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQTqbnljWzHSULmIzCGUUZ3RsiQn9XwcY9WdJrogCzuSg&s=10", ""));
        Eventos.add(new NoticiaP("Daniel Higiénico", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHLXMwxnlZwu5TSo4W_zO8LveIQn3auwBRSBk2uXX9uM-f7hNi9pz5gQbj7A&s=10", ""));
        Eventos.add(new NoticiaP("CANTAEBRIA - BLACK BOURBON", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQQIlQ4u9pZ0LBNs6lf3puF9yOUoKZBkvW5UkudCHNGmzb1yFsQPVpHQyseFA&s", ""));
        Eventos.add(new NoticiaP("Donde nada es seguro todo es posible (Conferencia)", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR_jfoxU5GlXR4jzDkwuoMGcEBvYSzAn7DJZampkWmZag&s", ""));
        Eventos.add(new NoticiaP("LA INCONCLUSA", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcStjaN9TGAxGPpgUobcI_9WB2VuolZLHE4DptoOrxBuWg&s", ""));
    }

    private void creaNoticia() throws FileNotFoundException, IOException {
      

         Noticia noticia = new Noticia();
           noticia.setTitulo("bs");
          

           File f = new File("C:/Users/Usuario/Documents/NetBeansProjects/TFG/src/main/webapp/resources/Imagenes/i.jpg");
            InputStream is;
            is = new FileInputStream(f);
            byte[] buffer = new byte[(int) f.length()];
            
            int readers = is.read(buffer);
            
            noticia.setImagen(buffer);
            

           NoticiaEJB.create(noticia);
            
            
           System.out.println("completo");
        } 
   
    
}
