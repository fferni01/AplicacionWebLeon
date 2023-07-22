package Controlador;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import modelos.Cultura;
import modelos.Deportes;
import modelos.Eventos;
import modelos.Hoteles;
import modelos.Parques;
import modelos.Restaurante;
import modelos.Rutas;

public class LeonOcio {

    BaseDatos oBD;
    int contadorDepor;
    int contadorRutas;
    int contadorHotel;
    int contadorrestau;
    int contadorCultura;
    int contadorEvenros;
    int contadorParques;
    private Map<String, String> cookeis;
    boolean CNRegistrada = true;

    public LeonOcio(BaseDatos oBD, String seleccionado) {
        // TODO Auto-generated constructor stub
        this.oBD = oBD;
        contadorDepor = 0;
        contadorRutas = 0;
        contadorHotel = 0;
        contadorrestau = 0;
        contadorCultura = 0;
        contadorEvenros = 0;
        contadorParques = 0;

        String Hoy = "https://leonocio.es/";
        String Deportes = "https://leonocio.es/lugares-deportivos/";
        String Rutas = "https://leonocio.es/rutas-en-leon/";
        String alojamiento = "https://leonocio.es/alojamientos-en-leon/";
        String restaurantes = "https://leonocio.es/restaurantes-en-leon/";
        String Culturaypatrimonio = "https://leonocio.es/cultura-en-leon/";
        String Parques = "https://leonocio.es/parques/";

        //eventos
        String Conciertos = "https://leonocio.es/conciertos-en-leon/?etype=upcoming";
        String Fiestas = "https://leonocio.es/fiestas-en-leon/?etype=upcoming";
        String Ferias = "https://leonocio.es/ferias-en-leon/?etype=upcoming";
        String Teatro = "https://leonocio.es/teatro-en-leon/?etype=upcoming";

        String ProximosEventos = "https://leonocio.es/eventos-en-leon/?etype=upcoming";

        sacarCookei(Hoy);
        if(seleccionado.equals("Deportes")||seleccionado.equals("Todo")) {
        	  sacarInfoDeportes(Deportes);
        }
        if(seleccionado.equals("Rutas")||seleccionado.equals("Todo")) {
        	 sacarInfoRutas(Rutas);
      }
        if(seleccionado.equals("Parques")||seleccionado.equals("Todo")) {
        	sacarInfoParques(Parques);
     }
        if(seleccionado.equals("Hoteles")||seleccionado.equals("Todo")) {
        	sacarInfoAlojamiento(alojamiento);
        }
        if(seleccionado.equals("Restaurantes")||seleccionado.equals("Todo")) {
        	sacarInfoRestaurantes(restaurantes);
        }
        if(seleccionado.equals("Cultura")||seleccionado.equals("Todo")) {
        	sacarInfoCulturaypatrimonio(Culturaypatrimonio);
        }
        

        //eventos
        if(seleccionado.equals("Eventos")||seleccionado.equals("Todo")) {
        	sacarInfoEvento(Conciertos, "Conciertos");
        	sacarInfoEvento(Fiestas, "Fiestas");
        	sacarInfoEvento(Ferias, "Ferias");
        	sacarInfoEvento(Teatro, "Teatro");
        	sacarInfoEvento(Conciertos, "Conciertos");
        	sacarInfoEvento(ProximosEventos, "");
        }
        
    }

    public int getContadorDepor() {
        return contadorDepor;
    }

    public int getContadorRutas() {
        return contadorRutas;
    }

    public int getContadorHotel() {
        return contadorHotel;
    }

    public int getContadorrestau() {
        return contadorrestau;
    }

    public int getContadorCultura() {
        return contadorCultura;
    }

    public int getContadorEvenros() {
        return contadorEvenros;
    }

    public int getContadorParques() {
        return contadorParques;
    }

    private void sacarInfoParques(String Url) {
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
                        System.out.println(imagen);
                        Elements Direccion = image.getElementsByClass("address");
                        //System.out.println(Direccion.text());
                        direccion = Direccion.text();
                        if (Titulo.length() > 0 && imagen.length() > 0) {
                            Parques Parques = new Parques();
                            Parques.setTitulo(Titulo);
                            Parques.setDireccion(direccion);
                            Parques.setUrl(url);
                            // System.out.println(image);

                            if (noExiste(Parques)) {
                                Parques.setImagen(descargarImagen(imagen));
                                oBD.introduceParque(Parques);
                                contadorParques++;
                            }
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

    private boolean noExiste(Parques parques) {
        for (int i = 0; i < oBD.getParques().size(); i++) {
            if (oBD.getParques().get(i).getTitulo().equals(parques.getTitulo())) {
                return false;
            }
        }
        return true;
    }

    private void sacarInfoEvento(String Url, String string) {
        String direccion = "";
        String Titulo = "";
        String imagen = null;
        String fecha = "";
        String hora = "";
        String url = "";
        String[] separacionfecha;
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
                    url = a.attr("abs:href");
                    imagen = img.attr("abs:src");
                    Elements titulo = image.getElementsByTag("h2");
                    Titulo = titulo.text();
                    System.out.println(Titulo);
                    Elements fechas = image.getElementsByClass("timing");
                    System.out.println(fechas.text());
                    Elements Direccion = image.getElementsByClass("address");
                    System.out.println(Direccion.text());
                    direccion = Direccion.text();
                    System.out.println();
                    separacionfecha = fechas.text().split(" ");
                    boolean s = false;
                    for (int i = 0; i < separacionfecha.length; i++) {
                        if (separacionfecha[i].equals("Hora")) {
                            s = true;
                        }
                        if (s) {
                            hora = hora + separacionfecha[i] + " ";
                        } else {
                            fecha = fecha + separacionfecha[i] + " ";
                        }
                    }
                    System.out.println(fecha);
                    System.out.println(hora);
                    if (Titulo.length() > 0 && imagen.length() > 0) {
                        Eventos Evento = new Eventos();
                        Evento.setNombre(Titulo);

                        Evento.setDireccion(direccion);
                        Evento.setFecha(fecha);
                        Evento.setHora(hora);
                        Evento.setUrl(url);
                        Evento.setTipo(string);
                        fecha = "";
                        // System.out.println(image);

                        if (noExiste(Evento)) {
                            Evento.setImagen(descargarImagen(imagen));
                            oBD.introduceEvento(Evento);
                            contadorEvenros++;

                        }
                    }
                }
                Url = ComprobarSiguientePagina(document, Url);
                // System.out.println(document.getElementsByTag("td"));

            } else {
                System.out.println("El Status Code no es OK es: " + getStatusConnectionCodeLO(Url));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private boolean noExiste(Eventos evento) {
        for (int i = 0; i < oBD.getEventos().size(); i++) {
            if (oBD.getEventos().get(i).getNombre().equals(evento.getNombre())) {
                return false;
            }
        }
        return true;
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
                        System.out.println(imagen);
                        Elements Direccion = image.getElementsByClass("address");
                        //System.out.println(Direccion.text());
                        direccion = Direccion.text();
                        if (Titulo.length() > 0 && imagen.length() > 0) {
                            Deportes Deportes = new Deportes();
                            Deportes.setTitulo(Titulo);
                            Deportes.setDireccion(direccion);
                            Deportes.setUrl(url);
                            // System.out.println(image);

                            if (noExiste(Deportes)) {
                                Deportes.setImagen(descargarImagen(imagen));
                                oBD.introduceDeporte(Deportes);
                                contadorDepor++;

                            }
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

    private boolean noExiste(Deportes deportes) {
        // TODO Auto-generated method stub
        for (int i = 0; i < oBD.getDeportes().size(); i++) {
            if (oBD.getDeportes().get(i).getTitulo().equals(deportes.getTitulo())) {
                return false;
            }
        }
        return true;
    }

    private boolean noExiste(Rutas rutas) {
        // TODO Auto-generated method stub
        for (int i = 0; i < oBD.getRutas().size(); i++) {
            if (oBD.getRutas().get(i).getTitulo().equals(rutas.getTitulo())) {
                return false;
            }
        }
        return true;
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

    private static InputStream descargarImagen(String urlImagen) {
        try {

            URL url = new URL(urlImagen);
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(url.openStream());
            return in;

        } catch (Exception e) {
            System.out.println(e);
        }

        return null;

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

    private int getStatusConnectionCodeLO(String url) {

        Response response = null;

        try {
            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
            if (CNRegistrada) {
                CNRegistrada = false;
                cookeis = response.cookies();
            }
        } catch (IOException ex) {
            System.out.println("Excepci�n al obtener el Status Code: " + ex.getMessage());
        }
        return response.statusCode();
    }

    private Document getHtmlDocumentLO(String url) {

        Document doc = null;
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).cookies(cookeis).get();

            // System.out.println(doc.co);
        } catch (IOException ex) {
            System.out.println("Excepci�n al obtener el HTML de la p�gina" + ex.getMessage());
        }
        return doc;
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
                        if (Titulo.length() > 0 && imagen.length() > 0) {
                            Rutas Rutas = new Rutas();
                            Rutas.setTitulo(Titulo);
                            Rutas.setDireccion(direccion);
                            Rutas.setUrl(url);
                          
                           
                            if (noExiste(Rutas)) {
                                 Rutas.setImagen(descargarImagen(imagen));
                                oBD.introduceRuta(Rutas);
                                contadorRutas++;
                            }
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
                        if (Titulo.length() > 0 && imagen.length() > 0) {
                            Hoteles Hoteles = new Hoteles();
                            Hoteles.setTitulo(Titulo);
                            Hoteles.setDireccion(direccion);
                            Hoteles.setUrl(url);
                          
                            if (noExiste(Hoteles)) {
                                  Hoteles.setImagen(descargarImagen(imagen));
                                oBD.introduceHotel(Hoteles);
                                contadorHotel++;
                            }
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

    private boolean noExiste(Hoteles hoteles) {
        for (int i = 0; i < oBD.getHoteles().size(); i++) {
            if (oBD.getHoteles().get(i).getTitulo().equals(hoteles.getTitulo())) {
                return false;
            }
        }
        return true;
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
                        Restaurante restaurante = new Restaurante();
                        restaurante.setTitulo(Titulo);
                        restaurante.setDireccion(direccion);
                        restaurante.setUrl(url);

                        if (noExiste(restaurante)) {
                            restaurante.setImagen(descargarImagen(imagen));
                            oBD.introduceRestaurante(restaurante);
                            contadorrestau++;
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

    private boolean noExiste(Restaurante restaurante) {

        // TODO Auto-generated method stub
        for (int i = 0; i < oBD.getRestaurantes().size(); i++) {
            if (oBD.getRestaurantes().get(i).getTitulo().equals(restaurante.getTitulo())) {
                return false;
            }
        }
        return true;

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
                        Cultura Cultura = new Cultura();
                        Cultura.setTitulo(Titulo);
                        Cultura.setDireccion(direccion);
                        Cultura.setUrl(url);

                        if (noExiste(Cultura)) {
                            Cultura.setImagen(descargarImagen(imagen));
                            oBD.introduceCultura(Cultura);
                            contadorCultura++;
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

    private boolean noExiste(Cultura cultura) {
        for (int i = 0; i < oBD.getCulturas().size(); i++) {
            if (oBD.getCulturas().get(i).getTitulo().equals(cultura.getTitulo())) {
                return false;
            }
        }
        return true;
    }
}
