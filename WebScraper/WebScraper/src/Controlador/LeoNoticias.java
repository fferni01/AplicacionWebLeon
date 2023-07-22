package Controlador;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import modelos.*;



import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import java.io.Serializable;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.JTextArea;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class LeoNoticias {
	BaseDatos oBD;
         JTextArea jTextArea1;
         int contadorNoticias;
	 public LeoNoticias(BaseDatos oBD) {
		 this.oBD=oBD;
                 contadorNoticias=0;
		 String url = "https://www.leonoticias.com/leon/";
	        ArrayList<String> Urls = new ArrayList<String>();
	        Urls = sacarUrlsNoticia(url);
	        sacarNoticias(Urls);
               
	    }

    public int getContadorNoticias() {
        return contadorNoticias;
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
	                            imagenE = doc.getElementsByClass("voc-horizontal voc-img-icon-link").first().getElementsByTag("img").first();
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
	            System.out.println("Excepciï¿½n al obtener el HTML de la página" + ex.getMessage());
	        }
	        return doc;
	    }
	    
	    private void creaNoticia(String Titulo, ArrayList<String> ParteCuerpo, String Fecha, String imagen) throws ParseException {

	        if (Titulo.length() > 0 && imagen.length() > 0) {
	            try {
	            	Noticia noticia = new Noticia();
	            	 List<Noticia> noticias = oBD.getNoticias();
	            	
	                Cuerponoticia c = new Cuerponoticia();

	                boolean NoIgual = true;
	              
	                    noticia.setImagen(descargarImagen(imagen));

	             
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
	                   // NoticiaEJB.create(noticia);
	                    oBD.introduceNoticia(noticia);
                           contadorNoticias++;
	                    ArrayList<Noticia> not =oBD.getNoticias();
	                    for (int i = 0; i < not.size(); i++) {
							if(noticia.getTitulo().equals(not.get(i).getTitulo())) {
								noticia=oBD.getNoticias().get(i);
							}
						}
	                    System.out.println("-----------------------------------------"+noticia.getIdNoticia());
	                    for (int i = 0; i < ParteCuerpo.size(); i++) {
	                    	
	                        c.setNoticia(noticia);
	                        c.setTexto(ParteCuerpo.get(i));
	                        oBD.introduceCN(c);
	                        
	                    }
	                }
	            } catch (Exception e) {
	            }
	        }
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
}
