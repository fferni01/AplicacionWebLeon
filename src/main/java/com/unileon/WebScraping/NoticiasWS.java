/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.WebScraping;

import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.modelo.Noticia;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class NoticiasWS implements Serializable {

    private Noticia noticia;
    private ArrayList<String> Urls;
    private String url;
    @EJB
    private NoticiaFacadeLocal NoticiaEJB;

    public NoticiasWS() {
        noticia = new Noticia();
        Urls = new ArrayList<String>();
        url = "https://www.leonoticias.com/leon/";

        sacarUrls();
        sacarNoticias();

    }

    private void sacarUrls() {

        if (getStatusConnectionCode(url) == 200) {

            Document document = getHtmlDocument(url);

            Elements Partes = document.getElementsByClass("voc-detail voc-subhome");

            for (Element parte : Partes) {

                Elements articles = parte.getElementsByTag("h2");
                for (Element article : articles) {

                    Urls.add(article.getElementsByTag("a").attr("abs:href"));

                }
            }

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
        }
    }

    private void sacarNoticias() {
        Element imagenE;
        String imagen = null;
        String Titulo = null;
        ArrayList<String> ParteCuerpo = new ArrayList<String>();

        //imagen
        for (int i = 0; i < Urls.size(); i++) {

            if (getStatusConnectionCode(Urls.get(i)) == 200) {
                try {

                    Document doc = getHtmlDocument(Urls.get(i));

                    if (doc.body().className().equals("leonoticias")) {
                        imagenE = doc.getElementsByClass("voc-horizontal voc-img-icon-link").first().getElementsByTag("img").first();
                        if (imagenE != null) {
                            imagen = imagenE.attr("abs:src");

                            if (imagen.isEmpty()) {

                                imagenE = doc.getElementsByClass("voc-detail__cmp ").first().getElementsByTag("img").first();

                                if (imagenE != null) {
                                    imagen = imagenE.attr("abs:data-original");
                                }

                            }
                        }
                        //titulo
                        Titulo = doc.getElementsByClass("voc-title").first().text();
                        //Cuerpo
                        Elements Cuerpos = doc.getElementsByClass("voc-paragraph");

                        for (Element Cuepo : Cuerpos) {
                            ParteCuerpo.add(Cuepo.text());
                        }

                    }
                } catch (Exception e) {
                    System.err.println("Error en " + e);

                }
                if (!imagen.isEmpty() && imagen != null && !Titulo.isEmpty() && Titulo != null && !ParteCuerpo.isEmpty()) {
                    insertar(Titulo, imagen, ParteCuerpo);
                    System.out.println(Titulo);
                    
                    System.out.println(imagen);

                    for (int j = 0; j < ParteCuerpo.size(); j++) {
                        System.out.println(ParteCuerpo.get(j));
                    }
                    // insertar(Titulo,imagen,ParteCuerpo);
                    System.out.println("---------------------------------------------------");
                }

            } else {
                System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
            }
        }
    }

    private int getStatusConnectionCode(String url) {

        Response response = null;

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

    private void insertar(String Titulo, String imagen, ArrayList<String> ParteCuerpo) {
        descargarImagen(imagen);
        
    }

    private void descargarImagen(String urlImagen) {
        try {
            URL url = new URL(urlImagen);

            // establecemos conexion
            URLConnection urlCon = url.openConnection();
            urlCon.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
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

    }
}
