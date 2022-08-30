/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.WebScraping;

import com.unileon.EJB.CuerponoticiaFacadeLocal;
import com.unileon.EJB.InformacionWebScraperFacadeLocal;
import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.modelo.InformacionWebScraper;
import com.unileon.modelo.Noticia;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Usuario
 */
@Named
@ApplicationScoped
public class WebScraper implements Serializable {

    private Noticia noticia;
    private List<InformacionWebScraper> listaWS;
    private String url;
    boolean primaravez = true;
    Timer timer = new Timer();
       TimerTask task;

    @EJB
    private NoticiaFacadeLocal NoticiaEJB;
    @EJB
    private InformacionWebScraperFacadeLocal WebScraperInfoEJB;
    @EJB
    private CuerponoticiaFacadeLocal CuerpoNoticiaEJB;

    @PostConstruct
    public void init() {
        noticia = new Noticia();
      
        url = "https://www.leonoticias.com/leon/";
      
    }
 public void iniciarTemporizador(){
     
     InformacionWebScraper noticiaWS = encuentraInfoWS("NoticiaWS");
     noticiaWS.setEstado("Y");
    WebScraperInfoEJB.edit(noticiaWS);
    
       timer = new Timer();
    
        task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(".run()");
                iniciarWS();
            }
        };
        timer.schedule(task, 10, 300000);
        
        
            
 }
    public void iniciarWS() {
        //CreaDatosEstadoWS();
/////Definitivamente asi no puede ser , tienes que crear en el administrador 
//unos botones que activen el web scraper y tambien que lo puedan desactivar hacerlo cun un while infinito que compruebe horas y cuando sea nueva hora que ejecute el web scraper   
    
InformacionWebScraper noticiaWS = encuentraInfoWS("NoticiaWS");
        
          //System.out.println("com.unileon.WebScraping.WebScraper.cargarWS()");
        if (noticiaWS.getEstado().equals("Y")) {
            //if(HaPasadoUnaHora(noticiaWS.getFecha())){
                 System.out.println("com.unileon.controller.admin.PrincipalAdminController.CargarWS()");
    ArrayList<String> Urls = sacarUrls();
            sacarNoticias(Urls);
            Date Fecha = new Date();
            noticiaWS.setFecha(Fecha);
            WebScraperInfoEJB.edit(noticiaWS);
            //}
           
        }
        /*else if(HaPasadoUnaHora(noticiaWS.getFecha())){
            sacarUrls();
            sacarNoticias();
            Date Fecha = new Date();
            noticiaWS.setFecha(Fecha);
            WebScraperInfoEJB.edit(noticiaWS);
        }*/
    
    
    }
      public void pararWS() {
          InformacionWebScraper noticiaWS = encuentraInfoWS("NoticiaWS");
          noticiaWS.setEstado("N");
          WebScraperInfoEJB.edit(noticiaWS);
          timer.cancel();
         
      }
    private ArrayList<String> sacarUrls() {
    ArrayList<String> Urls = new ArrayList<String>();
        if (getStatusConnectionCode(url) == 200) {
            try {

                Document document = getHtmlDocument(url);

                Elements Partes = document.getElementsByClass("voc-detail voc-subhome");

                for (Element parte : Partes) {

                    Elements articles = parte.getElementsByTag("h2");
                    for (Element article : articles) {
                        Urls.add(article.getElementsByTag("a").attr("abs:href"));
                    }
                }
            } catch (Exception e) {
                System.out.println(e);

            }

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
        }
        return Urls;

    }

    private void sacarNoticias(ArrayList<String> Urls) {
        Element imagenE;
        String imagen = null;
        String Titulo = null;
        String Fecha = null;
        ArrayList<String> ParteCuerpo = new ArrayList<String>();

        for (int i = 0; i < Urls.size(); i++) {
            try {

                if (getStatusConnectionCode(Urls.get(i)) == 200) {
                    Document doc = getHtmlDocument(Urls.get(i));

                    if (doc.body().className().equals("leonoticias")) {
                        try {
                            imagenE = doc.getElementsByClass("voc-horizontal voc-img-icon-link").first()
                                    .getElementsByTag("img").first();
                            imagen = imagenE.attr("abs:src");
                            if (imagen.isEmpty()) {
                                imagenE = doc.getElementsByClass("voc-detail__cmp ").first().getElementsByTag("img").first();

                                imagen = imagenE.attr("abs:data-original");

                            }
                        } catch (Exception e) {
                            System.out.println("No imagen");
                        }
                        Titulo = doc.getElementsByClass("voc-title").first().text();

                        Elements Cuerpos = doc.getElementsByClass("voc-paragraph");
                        ParteCuerpo = new ArrayList<String>();
                        for (Element Cuepo : Cuerpos) {
                            ParteCuerpo.add(Cuepo.text());
                        }
                        Fecha = doc.getElementsByClass("voc-author-info").first().getElementsByTag("time").attr("datetime").substring(0, 10).replace("-", "/");
                        //System.out.println(doc.getElementsByClass("voc-author-info").first().getElementsByTag("time").attr("datetime").substring(0,10).replace("-",":"));
                        /*if (!Titulo.isEmpty() && !ParteCuerpo.isEmpty()) {

                            System.out.println(Titulo);
                            if (!imagen.isEmpty()) {
                                System.out.println(imagen);
                            }
                            System.out.println(Fecha);
                            System.out.println("---------------------------------------------------");
                        }
                        System.out.println("crea");*/
                        creaNoticia(Titulo, ParteCuerpo, Fecha, imagen);
                    }
                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
                }
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private int getStatusConnectionCode(String url) {

        Connection.Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    private Document getHtmlDocument(String url) {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }

    private byte[] descargarImagen(String urlImagen) {
        try {

            URL url = new URL(urlImagen);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            return response;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    private void creaNoticia(String Titulo, ArrayList<String> ParteCuerpo, String Fecha, String imagen) throws ParseException {
        List<Noticia> noticias = NoticiaEJB.findAll();
        boolean NoIgual = true;
        if (!imagen.isEmpty()) {
            noticia.setImagen(descargarImagen(imagen));

        } else {
            noticia.setImagen(null);
        }
        noticia.setTitulo(Titulo);

        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
        Date dataFormateada = formato.parse(Fecha);
        noticia.setFecha(dataFormateada);
        System.out.println(noticia.getTitulo());
        for (int i = 0; i < noticias.size(); i++) {
            if (noticia.getTitulo().equals(noticias.get(i).getTitulo())) {
                NoIgual = false;
            }
        }
        if (NoIgual) {
            NoticiaEJB.create(noticia);
        }
    }

    private void CreaDatosEstadoWS() {

        //noticias
        //minube
        //LeonOcio
        //terranostrum
        //yumping
        List<InformacionWebScraper> lista = WebScraperInfoEJB.findAll();
        if (lista.size() > 0) {
            if (noExiste(lista, "NoticiaWS")) {
                InformacionWebScraper noticia = new InformacionWebScraper();
                noticia.setNombre("NoticiaWS");
                noticia.setEstado("N");
                Date Fecha = new Date();
                noticia.setFecha(Fecha);
                WebScraperInfoEJB.create(noticia);
            }
            if (noExiste(lista, "minube")) {
                InformacionWebScraper WS = new InformacionWebScraper();
                WS.setNombre("minube");
                WS.setEstado("N");
                Date Fecha = new Date();
                WS.setFecha(Fecha);
                WebScraperInfoEJB.create(WS);
            }
            if (noExiste(lista, "Terranostrum")) {
                InformacionWebScraper WS = new InformacionWebScraper();
                WS.setNombre("Terranostrum");
                WS.setEstado("N");
                Date Fecha = new Date();
                WS.setFecha(Fecha);
                WebScraperInfoEJB.create(WS);
            }

            if (noExiste(lista, "LeonOcio")) {
                InformacionWebScraper WS = new InformacionWebScraper();
                WS.setNombre("LeonOcio");
                WS.setEstado("N");
                Date Fecha = new Date();
                WS.setFecha(Fecha);
                WebScraperInfoEJB.create(WS);
            }
            if (noExiste(lista, "yumping")) {
                InformacionWebScraper WS = new InformacionWebScraper();
                WS.setNombre("yumping");
                WS.setEstado("N");
                Date Fecha = new Date();
                WS.setFecha(Fecha);
                WebScraperInfoEJB.create(WS);
            }

        } else {

            InformacionWebScraper WS = new InformacionWebScraper();
            WS.setNombre("NoticiaWS");
            WS.setEstado("N");
            Date Fecha = new Date();
            WS.setFecha(Fecha);
            WebScraperInfoEJB.create(WS);
            WS.setNombre("minube");
            WS.setNombre("LeonOcio");
            WS.setNombre("yumping");
            WS.setNombre("Terranostrum");
        }

    }

    private boolean noExiste(List<InformacionWebScraper> lista, String texto) {
        for (InformacionWebScraper informacionWebScraper : lista) {
            if (informacionWebScraper.getNombre().equals(texto)) {
                return false;
            }
        }
        return true;
    }

    private InformacionWebScraper encuentraInfoWS(String noticiaWS) {
        List<InformacionWebScraper> lista = WebScraperInfoEJB.findAll();
        for (InformacionWebScraper informacionWebScraper : lista) {
            if (informacionWebScraper.getNombre().equals(noticiaWS)) {
                return informacionWebScraper;
            }
        }
        return null;
    }

    private boolean HaPasadoUnaHora(Date fecha) {
        Date Fechanueva = new Date();
        int diant = fecha.getDay();
        int horaAntigua = fecha.getHours();
        int minutosAntiguos = fecha.getMinutes();
        System.out.println("antigua: " + fecha.getHours());
        System.out.println("nueva: " + Fechanueva.getHours());
        System.out.println("minutosNuevos :" + Fechanueva.getMinutes());
        System.out.println("minutosAntiguos :"+fecha.getMinutes());
        if ((horaAntigua <= Fechanueva.getHours() && minutosAntiguos <= Fechanueva.getMinutes())||diant<Fechanueva.getDay()) {
            System.out.println("antigua: " + fecha.getHours());
            System.out.println("nueva: " + Fechanueva.getHours());
            return true;
        }

        return false;
    }

}
