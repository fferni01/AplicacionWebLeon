package Controlador;

import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import modelos.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Minube {
	BaseDatos oBD;
        int contadorParq;
	public Minube(BaseDatos oBD) {
		this.oBD=oBD;
                contadorParq=0;
		String url = "https://www.minube.com/tag/jardines-leon-c1250";
        ArrayList<String> Urls = new ArrayList<String>();
        Urls = sacarParquesMinube(url);
        sacarInfoMinu(Urls);
    }

    public int getContadorParq() {
        return contadorParq;
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
                Parques parque=new Parques();
                parque.setTitulo(nombre);
                parque.setDireccion(direccion);
                parque.setUrl(url);
                

                if (comprueba(parque)) {
                    parque.setImagen(descargarImagen(imagen));
                    oBD.introduceParque(parque);
                    contadorParq++;
                }
            }
        } catch (Exception e) {
        }
    }
	 private boolean comprueba(Parques parque) {
		for (int i = 0; i < oBD.getParques().size(); i++) {
			if(oBD.getParques().get(i).getTitulo().contains(parque.getTitulo())) {
				return false;
			}
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
