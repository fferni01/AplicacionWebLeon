package Controlador;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import modelos.Cultura;
import modelos.Rutas;
public class Terranostrum {

	BaseDatos oBD;
        int contadorRutas;
        int contadorCultura;
	public Terranostrum(BaseDatos oBD, String selecion) {
		// TODO Auto-generated constructor stub
		this.oBD=oBD;
                contadorRutas=0;
                contadorCultura=0;
		   String rutas1 = "https://www.terranostrum.es/senderismo/leon/cuatro-valles-cantabrica-leonesa/1";
		   String rutas2 = "https://www.terranostrum.es/senderismo/leon/montanas-del-teleno/1";
		   String rutas3 = "https://www.terranostrum.es/senderismo/leon/montanas-de-riano-picos-de-europa/1";
		   String rutas4 = "https://www.terranostrum.es/senderismo/leon/el-bierzo-y-los-ancares/1";
		   String rutas5 = "https://www.terranostrum.es/senderismo/leon/sureste-leones/1";
		   String rutas6 = "https://www.terranostrum.es/senderismo/leon/paramo-orbigo-esla/1";
		   String rutas7 ="https://www.terranostrum.es/senderismo/leon/leon-y-alfoz/1";
		   String turismo ="https://www.terranostrum.es/turismo/leon/leon-y-alfoz/1";

	        int contador = 1;
	        if(selecion.equals("Rutas")||selecion.equals("Todo")) {
	        sacarActividadTerra(rutas1, contador);
	        sacarActividadTerra(rutas2, contador);
	        sacarActividadTerra(rutas3, contador);
	        sacarActividadTerra(rutas4, contador);
	        sacarActividadTerra(rutas5, contador);
	        sacarActividadTerra(rutas6, contador);

	        sacarActividadTerra(rutas7, contador);
	        }
	        if(selecion.equals("Cultura")||selecion.equals("Todo")) {
	        sacarTurismoTerra(turismo, contador);
	        }
	}

    public int getContadorRutas() {
        return contadorRutas;
    }

    public int getContadorCultura() {
        return contadorCultura;
    }
	
	private void sacarTurismoTerra(String Url, int contador) {
		// TODO Auto-generated method stub
		  boolean fin = true;;

          while (fin) {
              if (getStatusConnectionCode(Url) == 200) {
                  Document document = getHtmlDocument(Url);

                  Element content = document.getElementById("categorycontent");
                  //System.out.println(content);
                  Elements as = content.getElementsByTag("a");
                  for (Element a : as) {

                    

                      Cultura Cultura=new Cultura();
                    
                      Cultura.setUrl(a.attr("abs:href"));
                     
                      Cultura.setDireccion(a.getElementsByClass("content").text());

                      Cultura.setTitulo(a.select("h3").text());
                     
                      if(noexisteC(Cultura.getTitulo())){
                      	 Cultura.setImagen(descargarImagen(a.select("img").attr("abs:src")));
                      	oBD.introduceCultura(Cultura);
                        contadorCultura++;
                      }
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

	private boolean noexisteC(String titulo) {
		for (int i = 0; i < oBD.getCulturas().size(); i++) {
			if(oBD.getCulturas().get(i).getTitulo().contains(titulo)) {
				return false;
			}
		}
		return true;
	}

	private void sacarActividadTerra(String Url, int contador) {
       
            boolean fin = true;;

            while (fin) {
                if (getStatusConnectionCode(Url) == 200) {
                    Document document = getHtmlDocument(Url);

                    Element content = document.getElementById("categorycontent");
                    //System.out.println(content);
                    Elements as = content.getElementsByTag("a");
                    for (Element a : as) {
                       // System.out.println("Url: " + a.attr("abs:href"));
                        //System.out.println("Imagen" + a.select("img").attr("abs:src"));
                       // System.out.println("Titulo: "+a.select("h3").text());
                        Elements duracionydistancia = a.select("span");
                        System.out.println(a.getElementsByClass("content").text());
                      //  System.out.println("duracion: " + duracionydistancia.first().text());
                       // System.out.println("distancia: " + duracionydistancia.last().text());
                        Rutas ruta=new Rutas();
                      
                        ruta.setUrl(a.attr("abs:href"));
                       
                        ruta.setDireccion(a.getElementsByClass("content").text());
                        ruta.setDistancia(duracionydistancia.last().text());
                        ruta.setTitulo(a.select("h3").text());
                       
                        if(noexiste(ruta.getTitulo())){
                        	 ruta.setImagen(descargarImagen(a.select("img").attr("abs:src")));
                        	oBD.introduceRutaDis(ruta);
                                contadorRutas++;
                        }
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

        private boolean noexiste(String titulo) {
			for (int i = 0; i < oBD.getRutas().size(); i++) {
				if(oBD.getRutas().get(i).getTitulo().contains(titulo)) {
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
