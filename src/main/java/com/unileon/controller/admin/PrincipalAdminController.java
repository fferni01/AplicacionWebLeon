/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller.admin;

import com.unileon.EJB.ActividadesFacadeLocal;
import com.unileon.EJB.CuerponoticiaFacadeLocal;
import com.unileon.EJB.CulturaFacadeLocal;
import com.unileon.EJB.DeportesFacadeLocal;
import com.unileon.EJB.EventosFacadeLocal;
import com.unileon.EJB.HotelesFacadeLocal;
import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.EJB.ParquesFacadeLocal;
import com.unileon.EJB.RutasFacadeLocal;
import com.unileon.modelo.Actividades;
import com.unileon.modelo.Cultura;
import com.unileon.modelo.Deportes;
import com.unileon.modelo.Eventos;
import com.unileon.modelo.Hoteles;
import com.unileon.modelo.Noticia;
import com.unileon.modelo.Parques;
import com.unileon.modelo.Rutas;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.jsoup.Connection;
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
public class PrincipalAdminController implements Serializable {

    private Noticia noticia;
    private Parques parque;
    private Cultura Cultura;
    private Deportes Deportes;
    private Eventos Eventos;
    private Hoteles Hoteles;
    private Rutas Rutas;
    private Actividades Actividad;
    // int contador = 0;
    @EJB
    private NoticiaFacadeLocal NoticiaEJB;
    @EJB
    private CuerponoticiaFacadeLocal CuerpoNoticiaEJB;
    @EJB
    private ParquesFacadeLocal ParqueEJB;
    @EJB
    private CulturaFacadeLocal CulturaEJB;
    @EJB
    private DeportesFacadeLocal DeportesEJB;
    @EJB
    private EventosFacadeLocal EventosEJB;
    @EJB
    private HotelesFacadeLocal HotelesEJB;
    @EJB
    private RutasFacadeLocal RutasEJB;
    @EJB
    private ActividadesFacadeLocal ActividadesEJB;

    @PostConstruct
    public void init() {
        noticia = new Noticia();
        parque = new Parques();
        Cultura = new Cultura();
        Deportes = new Deportes();
        Eventos = new Eventos();
        Hoteles = new Hoteles();
        Rutas = new Rutas();
        Actividad = new Actividades();

        /*Timer timer = new Timer();
        TimerTask task;
        task = new TimerTask() {
            @Override
            public void run() {
                System.out.println(".run()");
               // iniciarWS();
            }
        };
        timer.schedule(task, 10,120000);*/
    }

    public void iniciarWS() {

        //leoNoticiasWS();
        //yumpingWS();
        LeonOcioWS();
        //TerranostrumWS();
        // minubeWS();
        /*if (contador > 0) {
            System.out.println("com.unileon.controller.admin.PrincipalAdminController.CargarWS()");
            sacarUrlsNoticia();
            sacarNoticias();
        }
        contador = 1;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Modificacion realizada con exito"));*/
    }

    private Map<String, String> cookeis;
    boolean CNRegistrada = true;

    ////Leonoticias///
    private void leoNoticiasWS() {
        String url = "https://www.leonoticias.com/leon/";
        ArrayList<String> Urls = new ArrayList<String>();
        Urls = sacarUrlsNoticia(url);
        sacarNoticias(Urls);
    }

    private ArrayList<String> sacarUrlsNoticia(String url) {
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
                        if (!Titulo.isEmpty() && !ParteCuerpo.isEmpty()) {

                            System.out.println(Titulo);
                            if (!imagen.isEmpty()) {
                                System.out.println(imagen);
                            }
                            System.out.println(Fecha);
                            System.out.println("---------------------------------------------------");
                        }
                        System.out.println("crea");
                        creaNoticia(Titulo, ParteCuerpo, Fecha, imagen);
                    }
                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(Urls.get(i)));
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
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

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
            System.out.println(response.toString());
            return response;

        } catch (Exception e) {
            System.out.println(e);
        }

        /* try {
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
        }*/
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

    /////////////////////
    ///Leon ocio/////////
    /////////////////////
    private int getStatusConnectionCodeLO(String url) {

        Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
            if (CNRegistrada) {
                CNRegistrada = false;
                cookeis = response.cookies();
            }
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    private Document getHtmlDocumentLO(String url) {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).cookies(cookeis).get();

            // System.out.println(doc.co);
        } catch (IOException ex) {
            System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
        }
        return doc;
    }

    private void LeonOcioWS() {

        String Hoy = "https://leonocio.es/";
        String Deportes = "https://leonocio.es/lugares-deportivos/";
        String Rutas = "https://leonocio.es/aire-libre-en-leon/";
        String alojamiento = "https://leonocio.es/alojamientos-en-leon/";
        String restaurantes = "https://leonocio.es/restaurantes-en-leon/";
        String Culturaypatrimonio = "https://leonocio.es/cultura-en-leon/";
       
        //eventos
        String Conciertos = "https://leonocio.es/conciertos-en-leon/?etype=upcoming";
        String Fiestas = "https://leonocio.es/fiestas-en-leon/?etype=upcoming";
        String Ferias ="https://leonocio.es/ferias-en-leon/?etype=upcoming";
        String Teatro = "https://leonocio.es/teatro-en-leon/?etype=upcoming";
        String proximasActividades = "https://leonocio.es/actividades-en-leon/?etype=upcoming";
        
         String ProximosEventos = "https://leonocio.es/eventos-en-leon/?etype=upcoming";

        sacarCookei(Hoy);
        sacarInfoDeportes(Deportes);
        sacarInfoRutas(Rutas);
        sacarInfoAlojamiento(alojamiento);
        //sacarInfoRestaurantes(restaurantes);
        sacarInfoCulturaypatrimonio(Culturaypatrimonio);
        
        //eventos
        sacarInfoConciertos(Conciertos);
        sacarInfoFiestas(Fiestas);
        sacarProximosEventos(ProximosEventos);
    }

    private void sacarProximosEventos(String Url) {
        try {

            if (getStatusConnectionCodeLO(Url) == 200) {

                // Obtengo el HTML de la web en un objeto Document
                Document document = getHtmlDocumentLO(Url);
                Element datos = document.getElementById("loop");

                Elements contenido = datos.getElementsByClass("post-content");
                // System.out.println(contenido);
                for (Element imagen : contenido) {

                    Elements a = imagen.getElementsByClass("post_img");
                    Elements img = imagen.getElementsByTag("img");
                    System.out.println(img.attr("abs:src"));
                    System.out.println(a.attr("abs:href"));
                    Elements titulo = imagen.getElementsByTag("h2");
                    System.out.println(titulo.text());
                    Elements fechas = imagen.getElementsByClass("timing");
                    System.out.println(fechas.text());
                    Elements Direccion = imagen.getElementsByClass("address");
                    System.out.println(Direccion.text());
                    System.out.println();
                }
                // System.out.println(document.getElementsByTag("td"));

            } else {
                System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sacarCookei(String url) {
        if (getStatusConnectionCodeLO(url) == 200) {

            // Obtengo el HTML de la web en un objeto Document
            Document document = getHtmlDocumentLO(url);
            // System.out.println(document.getElementsByTag("td"));

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(url));
        }
    }

    private void sacarInfoDeportes(String Url) {
        String Titulo = null;
        String direccion = null;
        String imagen = null;
        String url = null;
        try {
            while (Url != null) {
                if (getStatusConnectionCodeLO(Url) == 200) {

                    // Obtengo el HTML de la web en un objeto Document
                    Document document = getHtmlDocumentLO(Url);
                    Element datos = document.getElementById("loop");

                    Elements contenido = datos.getElementsByClass("post-content");
                    // System.out.println(contenido);
                    for (Element image : contenido) {

                        Elements a = image.getElementsByClass("post_img");
                        Elements img = image.getElementsByTag("img");
                        imagen = img.attr("abs:src");
                        url = a.attr("abs:href");
                        //System.out.println(img.attr("abs:src"));
                        //System.out.println(a.attr("abs:href"));
                        Elements titulo = image.getElementsByTag("h2");
                        Titulo = titulo.text();
                        System.out.println();
                        System.out.println(titulo.text());
                        Elements Direccion = image.getElementsByClass("address");
                        //System.out.println(Direccion.text());
                        direccion = Direccion.text();

                        Deportes.setTitulo(Titulo);
                        Deportes.setDireccion(direccion);
                        Deportes.setUrl(url);
                        // System.out.println(image);

                        Deportes.setImagen(descargarImagen(imagen));
                        if (DeportesEJB.noExiste(Deportes)) {
                            DeportesEJB.create(Deportes);
                        }

                    }

                    Url = ComprobarSiguientePagina(document, Url);
                    // System.out.println(document.getElementsByTag("td"));

                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
                }
            }
            this.contador = 1;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);;
        }
    }

    private void sacarInfoRutas(String Url) {
        String Titulo = null;
        String direccion = null;
        String imagen = null;
        String url = null;
        try {

            while (Url != null) {
                if (getStatusConnectionCodeLO(Url) == 200) {

                    // Obtengo el HTML de la web en un objeto Document
                    Document document = getHtmlDocumentLO(Url);
                    Element datos = document.getElementById("loop");

                    Elements contenido = datos.getElementsByClass("post-content");
                    // System.out.println(contenido);
                    for (Element image : contenido) {

                        /* Elements a = image.getElementsByClass("post_img");
                        Elements img = image.getElementsByTag("img");
                        System.out.println(img.attr("abs:src"));
                        System.out.println(a.attr("abs:href"));
                        Elements titulo = image.getElementsByTag("h2");
                        System.out.println(titulo.text());
                        Elements Direccion = image.getElementsByClass("address");
                        System.out.println(Direccion.text());
                        System.out.println();*/
                        Elements a = image.getElementsByClass("post_img");
                        Elements img = image.getElementsByTag("img");
                        imagen = img.attr("abs:src");
                        url = a.attr("abs:href");
                        Elements titulo = image.getElementsByTag("h2");
                        Titulo = titulo.text();
                        Elements Direccion = image.getElementsByClass("address");
                        direccion = Direccion.text();

                        Rutas.setTitulo(Titulo);
                        Rutas.setDireccion(direccion);
                        Rutas.setUrl(url);
                        Rutas.setImagensrc(imagen);
                        Rutas.setImagen(descargarImagen(imagen));
                        if (RutasEJB.noExiste(Rutas)) {
                            RutasEJB.create(Rutas);
                        }
                    }

                    Url = ComprobarSiguientePagina(document, Url);
                    // System.out.println(document.getElementsByTag("td"));

                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
                }
            }
            contador = 1;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);;
        }
    }

    private void sacarInfoAlojamiento(String Url) {
        String Titulo = null;
        String direccion = null;
        String imagen = null;
        String url = null;
        try {

            while (Url != null) {
                if (getStatusConnectionCodeLO(Url) == 200) {

                    // Obtengo el HTML de la web en un objeto Document
                    Document document = getHtmlDocumentLO(Url);
                    Element datos = document.getElementById("loop");

                    Elements contenido = datos.getElementsByClass("post-content");
                    // System.out.println(contenido);
                    for (Element image : contenido) {

                        Elements a = image.getElementsByClass("post_img");
                        Elements img = image.getElementsByTag("img");
                        imagen = img.attr("abs:src");
                        url = a.attr("abs:href");
                        Elements titulo = image.getElementsByTag("h2");
                        Titulo = titulo.text();
                        Elements Direccion = image.getElementsByClass("address");
                        direccion = Direccion.text();

                        Hoteles.setTitulo(Titulo);
                        Hoteles.setDireccion(direccion);
                        Hoteles.setUrl(url);
                        Hoteles.setImagen(descargarImagen(imagen));
                        if (HotelesEJB.noExiste(Hoteles)) {
                            HotelesEJB.create(Hoteles);
                        }
                    }

                    Url = ComprobarSiguientePagina(document, Url);
                    // System.out.println(document.getElementsByTag("td"));

                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
                }
            }
            contador = 1;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);;
        }
    }

    private void sacarInfoRestaurantes(String Url) {
        String Titulo = null;
        String direccion = null;
        String imagen = null;
        String url = null;
        try {

            while (Url != null) {
                if (getStatusConnectionCodeLO(Url) == 200) {

                    // Obtengo el HTML de la web en un objeto Document
                    Document document = getHtmlDocumentLO(Url);
                    Element datos = document.getElementById("loop");

                    Elements contenido = datos.getElementsByClass("post-content");
                    // System.out.println(contenido);
                    for (Element image : contenido) {

                          Elements a = image.getElementsByClass("post_img");
                        Elements img = image.getElementsByTag("img");
                        imagen = img.attr("abs:src");
                        url = a.attr("abs:href");
                        Elements titulo = image.getElementsByTag("h2");
                        Titulo = titulo.text();
                        Elements Direccion = image.getElementsByClass("address");
                        direccion = Direccion.text();

                       /* Restaurantes.setTitulo(Titulo);
                        Restaurantes.setDireccion(direccion);
                        Restaurantes.setUrl(url);
                        Restaurantes.setImagen(descargarImagen(image));
                        if (RestaurantesEJB.noExiste(Restaurantes)) {
                            RestaurantesEJB.create(Restaurantes);
                        }*/
                    }

                    Url = ComprobarSiguientePagina(document, Url);
                    // System.out.println(document.getElementsByTag("td"));

                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
                }
            }
            contador = 1;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);;
        }
    }

    private void sacarInfoCulturaypatrimonio(String Url) {
        String Titulo = null;
        String direccion = null;
        String imagen = null;
        String url = null;
        try {

            while (Url != null) {
                if (getStatusConnectionCodeLO(Url) == 200) {

                    // Obtengo el HTML de la web en un objeto Document
                    Document document = getHtmlDocumentLO(Url);
                    Element datos = document.getElementById("loop");

                    Elements contenido = datos.getElementsByClass("post-content");
                    // System.out.println(contenido);
                    for (Element image : contenido) {

                         Elements a = image.getElementsByClass("post_img");
                        Elements img = image.getElementsByTag("img");
                        imagen = img.attr("abs:src");
                        url = a.attr("abs:href");
                        Elements titulo = image.getElementsByTag("h2");
                        Titulo = titulo.text();
                        Elements Direccion = image.getElementsByClass("address");
                        direccion = Direccion.text();

                        Cultura.setTitulo(Titulo);
                        Cultura.setDireccion(direccion);
                        Cultura.setUrl(url);
                        Cultura.setImagen(descargarImagen(imagen));
                        if (CulturaEJB.noExiste(Cultura)) {
                            CulturaEJB.create(Cultura);
                        }

                    }

                    Url = ComprobarSiguientePagina(document, Url);
                    // System.out.println(document.getElementsByTag("td"));

                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
                }
            }

            contador = 1;
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e);;
        }
    }

    private void sacarInfoConciertos(String Url) {
         String Titulo = null;
        String direccion = null;
        String imagen = null;
        String url = null;
        String fecha = null;
        String hora = null;
        try {

            if (getStatusConnectionCodeLO(Url) == 200) {

                // Obtengo el HTML de la web en un objeto Document
                Document document = getHtmlDocumentLO(Url);
                Element datos = document.getElementById("loop");

                Elements contenido = datos.getElementsByClass("post-content");
                // System.out.println(contenido);
                for (Element image : contenido) {

                    Elements a = image.getElementsByClass("post_img");
                    Elements img = image.getElementsByTag("img");
                    System.out.println(img.attr("abs:src"));
                    System.out.println(a.attr("abs:href"));
                    Elements titulo = image.getElementsByTag("h2");
                    System.out.println(titulo.text());
                    Elements fechas = image.getElementsByClass("timing");
                    System.out.println(fechas.text());
                    Elements Direccion = image.getElementsByClass("address");
                    System.out.println(Direccion.text());
                    System.out.println();
                }
                // System.out.println(document.getElementsByTag("td"));

            } else {
                System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void sacarInfoFiestas(String Url) {
        try {

            if (getStatusConnectionCodeLO(Url) == 200) {

                // Obtengo el HTML de la web en un objeto Document
                Document document = getHtmlDocumentLO(Url);
                Element datos = document.getElementById("loop");

                Elements contenido = datos.getElementsByClass("post-content");
                // System.out.println(contenido);
                for (Element imagen : contenido) {

                    Elements a = imagen.getElementsByClass("post_img");
                    Elements img = imagen.getElementsByTag("img");
                    System.out.println(img.attr("abs:src"));
                    System.out.println(a.attr("abs:href"));
                    Elements titulo = imagen.getElementsByTag("h2");
                    System.out.println(titulo.text());
                    Elements fechas = imagen.getElementsByClass("timing");
                    System.out.println(fechas.text());
                    Elements Direccion = imagen.getElementsByClass("address");
                    System.out.println(Direccion.text());
                    System.out.println();
                }
                // System.out.println(document.getElementsByTag("td"));

            } else {
                System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    int contador = 1;

    private String ComprobarSiguientePagina(Document document, String url) {
        if (contador == 1) {
            url = url + "page/1/";
        }
        // TODO Auto-generated method stub
        Elements paginacion = document.getElementsByClass("pagination");
        if (paginacion.first().getElementsByClass("nextpostslink").size() != 0) {
            String concatenar = String.valueOf(contador);
            url = url.substring(0, url.length() - concatenar.length() - 1);
            contador++;
            concatenar = String.valueOf(contador);
            url = url + concatenar + "/";
            System.out.println(url);
            return url;
        }
        // System.out.println(url);
        return null;
    }

    ////////////////
    /// YUMPING ////
    ////////////////
    private void yumpingWS() {
        String url = "https://www.yumping.com/aventuras-tematicas/leon";
        sacarUrlsYmp(url);
        //sacarInfoYmp(Urls, Categoria);
    }

    private void sacarUrlsYmp(String url) {
        ArrayList<String> Urls = new ArrayList<String>();
        ArrayList<String> Categoria = new ArrayList<String>();
        if (getStatusConnectionCode(url) == 200) {

            // Obtengo el HTML de la web en un objeto Document
            Document document = getHtmlDocument(url);
            sacarActividadCero(url);
            Elements content = document.body().getElementsByClass("pure-u w100");

            for (Element a : content) {
                Elements tag = a.getElementsByTag("a");
                //	System.out.println(tag.text());
                Categoria.add(tag.text());
                //System.out.println(tag.attr("abs:href"));
                Urls.add(tag.attr("abs:href"));
            }
            System.out.println(Categoria);
            System.out.println(Urls);

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
        }

        sacarInfoYmp(Urls, Categoria);

    }

    private void sacarInfoYmp(ArrayList<String> Urls, ArrayList<String> Categoria) {
        String nombre = null;
        String direccion = null;
        String imagen = null;
        String Precio = null;
        for (int i = 0; i < Urls.size(); i++) {
            if (getStatusConnectionCode(Urls.get(i)) == 200) {

                Document doc = getHtmlDocument(Urls.get(i));
                System.out.println("------------------" + doc.title() + "------------------" + "\n");
                Elements content = doc.getElementsByClass("vendors-list");
                Elements separarciones = content.first().getElementsByTag("li");
                //System.out.println(separarciones.first());
                for (Element s : separarciones) {
                    System.out.println("titulo : " + s.select("a").first().text());
                    nombre = s.select("a").first().text();

                    System.out.println("Imagen : " + s.select("img").attr("abs:src"));
                    imagen = s.select("img").attr("abs:src");
                    System.out.println("Ubicacion : " + s.getElementsByClass("vendors-list-sector color-grey").text());
                    direccion = s.getElementsByClass("vendors-list-sector color-grey").text();
                    System.out.println("Precio : " + s.getElementsByClass("lh17 fs28").text());
                    Precio = s.getElementsByClass("lh17 fs28").text();
                    //System.out.println("ver mas : "+s.getElementsByClass("pure-g").first().getElementsByTag("a").f);
                    Element elementosClase = s.getElementsByClass("pure-g").first();

                    Elements elemetosTag = elementosClase.getElementsByTag("a");
                    String Url = elemetosTag.attr("abs:href");
                    System.out.println("ver mas :" + Url);

                    System.out.println();
                    String[] separacion = nombre.split(" ");
                    String juntar = "";
                    for (int j = 1; j < separacion.length; j++) {
                        juntar = juntar + " " + separacion[j];
                    }

                    Actividad.setTitulo(juntar);
                    Actividad.setDireccion(direccion);
                    Actividad.setPrecio(Precio);
                    Actividad.setTipo(Categoria.get(i));
                    Actividad.setUrl(Url);
                    Actividad.setImagen(descargarImagen(imagen));
                    if (ActividadesEJB.noExiste(Actividad)) {
                        ActividadesEJB.create(Actividad);
                    }

                }
            }
        }
    }

    private void sacarActividadCero(String url2) {
        String nombre = null;
        String direccion = null;
        String imagen = null;
        String Precio = null;
        String Tipo = "Aventura Tematica";
        if (getStatusConnectionCode(url2) == 200) {

            Document doc = getHtmlDocument(url2);
            System.out.println("------------------" + doc.title() + "------------------" + "\n");
            Elements content = doc.getElementsByClass("vendors-list");
            Elements separarciones = content.first().getElementsByTag("li");
            //System.out.println(separarciones.first());
            for (Element s : separarciones) {
                System.out.println("titulo : " + s.select("a").first().text());
                nombre = s.select("a").first().text();

                System.out.println("Imagen : " + s.select("img").attr("abs:src"));
                imagen = s.select("img").attr("abs:src");
                System.out.println("Ubicacion : " + s.getElementsByClass("vendors-list-sector color-grey").text());
                direccion = s.getElementsByClass("vendors-list-sector color-grey").text();
                System.out.println("Precio : " + s.getElementsByClass("lh17 fs28").text());
                Precio = s.getElementsByClass("lh17 fs28").text();
                //System.out.println("ver mas : "+s.getElementsByClass("pure-g").first().getElementsByTag("a").f);
                Element elementosClase = s.getElementsByClass("pure-g").first();

                Elements elemetosTag = elementosClase.getElementsByTag("a");
                String Url = elemetosTag.attr("abs:href");
                System.out.println("ver mas :" + Url);

                System.out.println();
                String[] separacion = nombre.split(" ");
                String juntar = "";
                for (int i = 1; i < separacion.length; i++) {
                    juntar = juntar + " " + separacion[i];
                }

                Actividad.setTitulo(juntar);
                Actividad.setDireccion(direccion);
                Actividad.setPrecio(Precio);
                Actividad.setTipo(Tipo);
                Actividad.setUrl(Url);
                Actividad.setImagen(descargarImagen(imagen));
                if (ActividadesEJB.noExiste(Actividad)) {
                    ActividadesEJB.create(Actividad);
                }

            }
        }

    }

    /////////////////////
    /// Terranostrum ////
    ////////////////////
    private void TerranostrumWS() {
        String url = "https://www.terranostrum.es/senderismo/leon/1";
        ArrayList<String> Urls = new ArrayList<String>();
        int contador = 1;
        Urls = sacarUrlTerra(url);
        sacarActividadTerra(Urls, contador);
    }

    private ArrayList<String> sacarUrlTerra(String url) {
        ArrayList<String> Urls = new ArrayList<String>();
        if (getStatusConnectionCode(url) == 200) {

            // Obtengo el HTML de la web en un objeto Document
            Document document = getHtmlDocument(url);

            //System.out.println(document);
            Elements content = document.getElementsByClass("buttonsComarcas");
            for (Element element : content) {
                Elements as = element.getElementsByTag("a");
                for (Element a : as) {
                    // System.out.println(a.attr("abs:href"));
                    Urls.add(a.attr("abs:href"));
                }
                System.out.println(Urls);
                //}
            }
            //System.out.println(content.size());

        } else {
            System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
        }
        return Urls;
    }

    private void sacarActividadTerra(ArrayList<String> Urls, int contador) {
        String Url;
        for (int i = 0; i < 1 /*Urls.size()*/; i++) {
            Url = Urls.get(i);
            boolean fin = true;;

            while (fin) {
                if (getStatusConnectionCode(Url) == 200) {
                    Document document = getHtmlDocument(Url);

                    Element content = document.getElementById("categorycontent");
                    //System.out.println(content);
                    Elements as = content.getElementsByTag("a");
                    for (Element a : as) {
                        System.out.println("Url: " + a.attr("abs:href"));
                        System.out.println("Imagen" + a.select("img").attr("abs:src"));
                        System.out.println(a.select("h3").text());
                        Elements duracionydistancia = a.select("span");
                        System.out.println("duracion: " + duracionydistancia.first().text());
                        System.out.println("distancia: " + duracionydistancia.last().text());
                    }

                    //System.out.println(titulo.size());
                    int cantidad = as.size();
                    if (cantidad < 12 || as.size() == 0) {
                        fin = false;
                    } else {

                        String concatenar = String.valueOf(contador);
                        Url = Url.substring(0, Url.length() - concatenar.length());
                        contador++;
                        concatenar = String.valueOf(contador);
                        Url = Url + concatenar;

                    }

                } else {
                    System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(Url));
                }

            }
            contador = 1;
        }
    }

    private void minubeWS() {
        String url = "https://www.minube.com/tag/jardines-leon-c1250";
        ArrayList<String> Urls = new ArrayList<String>();
        Urls = sacarParquesMinube(url);
        sacarInfoMinu(Urls);
    }

    private void sacarInfoMinu(ArrayList<String> Urls) {
        String nombre = null;
        String direccion = null;
        String imagen = null;
        try {
            for (String url : Urls) {
                if (getStatusConnectionCode(url) == 200) {
                    Document document = getHtmlDocument(url);

                    nombre = document.getElementById("poiHeader").getElementsByTag("h1").text();
                    Element subheader = document.getElementById("poiSubheader");

                    //System.out.println("image: "+subheader.getElementsByClass("picture").attr("abs:data-src"));
                    imagen = subheader.getElementsByClass("picture").attr("abs:data-src");
                    if (subheader.getElementsByClass("info-item address fillbox").size() == 1) {
                        //System.out.println("direccion: "+ subheader.getElementsByClass("info-item address fillbox").first().getElementsByTag("a").text());
                        direccion = subheader.getElementsByClass("info-item address fillbox").first().getElementsByTag("a").text();
                    }
                }
                parque.setTitulo(nombre);
                parque.setDireccion(direccion);
                parque.setUrl(url);
                parque.setImagen(descargarImagen(imagen));

                if (ParqueEJB.comprueba(parque)) {
                    ParqueEJB.create(parque);
                }
            }
        } catch (Exception e) {
        }
    }

    private ArrayList<String> sacarParquesMinube(String url) {
        ArrayList<String> Urls = new ArrayList<String>();
        try {

            if (getStatusConnectionCode(url) == 200) {

                Document document = getHtmlDocument(url);

                Element Informacion = document.getElementById("itemsGrid");
                //	System.out.println(Informacion.getElementsByTag("a"));
                for (Element a : Informacion.getElementsByTag("a")) {
                    //System.out.println(a.text());

                    Urls.add(a.attr("abs:href"));
                }

            } else {
                System.out.println("El Status Code no es OK es: " + getStatusConnectionCode(url));
            }
        } catch (Exception e) {

        }
        return Urls;
    }

}
