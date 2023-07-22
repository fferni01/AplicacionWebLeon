package Controlador;



//import java.sql.Date;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import modelos.*;
import java.util.Calendar;

public class BaseDatos {

	private ConectarMySql oCocnectar;
	private ArrayList<Noticia> Noticias = new ArrayList<>();
	private ArrayList<Actividades> Actividades= new ArrayList<>();
	private ArrayList<Cuerponoticia> Cuerposnoticias = new ArrayList<>();
	private ArrayList<Cultura> Culturas = new ArrayList<>();
	private ArrayList<Deportes> Deportes = new ArrayList<>();
	private ArrayList<Eventos> Eventos = new ArrayList<>();
	private ArrayList<Hoteles> Hoteles = new ArrayList<>();
	private ArrayList<InformacionWebScraper> InformacionWebScraper = new ArrayList<>();
	private ArrayList<Parques> Parques = new ArrayList<>();
	private ArrayList<Rutas> Rutas = new ArrayList<>();
	private ArrayList<Restaurante> Restaurantes= new ArrayList<>();
	
	public BaseDatos() {
		oCocnectar = new ConectarMySql();

		iniciarBD();
		iniciarTodo();


	}
	public void iniciarTodo() {
		iniciarNoticia();
		iniciarCuerpoNoticia();
		iniciarActividades();
		iniciarCultura();
		iniciarDeportes();
		iniciarEventos();
		iniciarHoteles();
		iniciarIWS();
		iniciarParques();
		iniciarRestaurantes();
		iniciarRutas();
	}
	private void iniciarRutas() {
		// TODO Auto-generated method stub
		oCocnectar.CargarRutas();
		Rutas=oCocnectar.getRutas();
	}
	private void iniciarRestaurantes() {
		// TODO Auto-generated method stub
		oCocnectar.CargarRestaurantes();
		Restaurantes=oCocnectar.getRestaurantes();
	}
	private void iniciarParques() {
		// TODO Auto-generated method stub
		oCocnectar.CargarParques();
		Parques=oCocnectar.getParques();
	}
	private void iniciarIWS() {
		// TODO Auto-generated method stub
		oCocnectar.CargarIWS();
		InformacionWebScraper=oCocnectar.getIWS();
	}
	private void iniciarHoteles() {
		// TODO Auto-generated method stub
		oCocnectar.CargarHoteles();
		Hoteles=oCocnectar.getHoteles();
	}
	private void iniciarEventos() {
		// TODO Auto-generated method stub
		oCocnectar.CargarEventos();
		Eventos=oCocnectar.getEventos();
	}
	private void iniciarDeportes() {
		// TODO Auto-generated method stub
		oCocnectar.CargarDeportes();
		Deportes=oCocnectar.getDeportes();
	}
	private void iniciarCultura() {
		// TODO Auto-generated method stub
		oCocnectar.CargarCultura();
		Culturas=oCocnectar.getCultura();
	}
	private void iniciarActividades() {
		// TODO Auto-generated method stub
		oCocnectar.CargarActividades();
		Actividades=oCocnectar.getActividades();
	}
	private void iniciarCuerpoNoticia() {
		// TODO Auto-generated method stub
		oCocnectar.CargarCuerponoticia();
		Cuerposnoticias=oCocnectar.getCuerposNoticia();
		
	}
	public void CerrarConexionBD() {
		oCocnectar.closeConnection();
	}
	
	private void iniciarNoticia() {
		// TODO Auto-generated method stub
		oCocnectar.CargarNoticia();
		Noticias=oCocnectar.getNoticias();
		/*for(int i=0;i<Noticias.size();i++) {
			System.out.println(Noticias.get(i).getTitulo());
		}*/
	}
	
	private void iniciarBD() {
		// TODO Auto-generated method stub
		oCocnectar.openConnection();
	}
	public ConectarMySql getoCocnectar() {
		return oCocnectar;
	}
	

	public void ModificarBD(String tabla,String columna, String valor, String condicion) {
		// TODO Auto-generated method stub
		oCocnectar.actualizar(tabla, columna, valor,condicion);
		iniciarTodo();

	}
	
	public void introduceNoticia(Noticia n) {
		oCocnectar.IntroducirNoticia(n);
		iniciarNoticia();
	}
	public void introduceCN(Cuerponoticia n) {
		oCocnectar.IntroducirCuerponoticia(n); 
		iniciarCuerpoNoticia();
	}
	public void introduceActividad(Actividades n) {
		oCocnectar.IntroducirActividad(n);
		iniciarActividades();
	}
	public void introduceCultura(Cultura n) {
		oCocnectar.IntroducirCultura(n);
		iniciarCultura();
	}
	public void introduceDeporte(Deportes n) {
		oCocnectar.IntroducirDeporte(n);
		iniciarDeportes();
	}
	public void introduceEvento(Eventos n) {
		oCocnectar.IntroducirEvento(n);
		iniciarEventos();
	}
	public void introduceHotel(Hoteles n) {
		oCocnectar.IntroducirHotel(n);
		iniciarNoticia();
	}
	public void introduceParque(Parques n) {
		oCocnectar.IntroducirParque(n);
		iniciarNoticia();
	}
	public void introduceRestaurante(Restaurante n) {
		oCocnectar.IntroducirRestaurante(n);
		iniciarNoticia();
	}
	public void introduceRuta(Rutas n) {
		oCocnectar.IntroducirRuta(n);
		iniciarNoticia();
	}
	public void introduceRutaDis(Rutas n) {
		oCocnectar.IntroducirRutaDistancia(n);
		iniciarNoticia();
	}
	public ArrayList<Noticia> getNoticias() {
		return Noticias;
	}
	public void setNoticias(ArrayList<Noticia> noticias) {
		Noticias = noticias;
	}
	public ArrayList<Actividades> getActividades() {
		return Actividades;
	}
	public void setActividades(ArrayList<Actividades> actividades) {
		Actividades = actividades;
	}
	public ArrayList<Cuerponoticia> getCuerposnoticias() {
		return Cuerposnoticias;
	}
	public void setCuerposnoticias(ArrayList<Cuerponoticia> cuerposnoticias) {
		Cuerposnoticias = cuerposnoticias;
	}
	public ArrayList<Cultura> getCulturas() {
		return Culturas;
	}
	public void setCulturas(ArrayList<Cultura> culturas) {
		Culturas = culturas;
	}
	public ArrayList<Deportes> getDeportes() {
		return Deportes;
	}
	public void setDeportes(ArrayList<Deportes> deportes) {
		Deportes = deportes;
	}
	public ArrayList<Eventos> getEventos() {
		return Eventos;
	}
	public void setEventos(ArrayList<Eventos> eventos) {
		Eventos = eventos;
	}
	public ArrayList<Hoteles> getHoteles() {
		return Hoteles;
	}
	public void setHoteles(ArrayList<Hoteles> hoteles) {
		Hoteles = hoteles;
	}
	public ArrayList<InformacionWebScraper> getInformacionWebScraper() {
		return InformacionWebScraper;
	}
	public void setInformacionWebScraper(ArrayList<InformacionWebScraper> informacionWebScraper) {
		InformacionWebScraper = informacionWebScraper;
	}
	public ArrayList<Parques> getParques() {
		return Parques;
	}
	public void setParques(ArrayList<Parques> parques) {
		Parques = parques;
	}
	public ArrayList<Rutas> getRutas() {
		return Rutas;
	}
	public void setRutas(ArrayList<Rutas> rutas) {
		Rutas = rutas;
	}
	public ArrayList<Restaurante> getRestaurantes() {
		return Restaurantes;
	}
	public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
		Restaurantes = restaurantes;
	}
	
	
	
	

	
	
	
	}


