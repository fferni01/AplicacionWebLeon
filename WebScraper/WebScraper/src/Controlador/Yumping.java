package Controlador;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.ObjDoubleConsumer;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import modelos.Actividades;

public class Yumping {
	BaseDatos oBD;
        int contadorAct;
	public Yumping(BaseDatos oBD) {
		 String url = "https://www.yumping.com/aventuras-tematicas/leon";
		   this.oBD=oBD;
                   contadorAct =0;
	        sacarUrlsYmp(url);

	}

    public int getContadorAct() {
        return contadorAct;
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
	                    Actividades Actividad = new Actividades();
	                    Actividad.setTitulo(juntar);
	                    Actividad.setDireccion(direccion);
	                    Actividad.setPrecio(Precio);
	                    Actividad.setTipo(Categoria.get(i));
	                    Actividad.setUrl(Url);
	                    Actividad.setImagen(descargarImagen(imagen));
	                    if (noExiste(Actividad)) {
		                    oBD.introduceActividad(Actividad);
                                    contadorAct++;
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
	                Actividades Actividad = new Actividades();
	                Actividad.setTitulo(juntar);
	                Actividad.setDireccion(direccion);
	                Actividad.setPrecio(Precio);
	                Actividad.setTipo(Tipo);
	                Actividad.setUrl(Url);
	                Actividad.setImagen(descargarImagen(imagen));
	               
	                if (noExiste(Actividad)) {
	                    oBD.introduceActividad(Actividad);
                            contadorAct++;
	                }

	            }
	        }

	    }
	private boolean noExiste(Actividades Actividad) {
		ArrayList<Actividades> a = oBD.getActividades();
		for (int i = 0; i <a.size() ; i++) {
				if(Actividad.getTitulo().equals(a.get(i).getTitulo()))
						return false;
			
		}
		return true;
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
	 private int getStatusConnectionCode(String url) {

	    	Connection.Response response = null;

	        try {
	            response = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).ignoreHttpErrors(true).execute();
	        } catch (IOException ex) {
	            System.out.println("Excepci�n al obtener el Status Code: " + ex.getMessage());
	        }
	        return response.statusCode();
	    }

	    private Document getHtmlDocument(String url) {

	        Document doc = null;
	        try {
	            doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
	        } catch (IOException ex) {
	            System.out.println("Excepci�n al obtener el HTML de la p�gina" + ex.getMessage());
	        }
	        return doc;
	    }
}
