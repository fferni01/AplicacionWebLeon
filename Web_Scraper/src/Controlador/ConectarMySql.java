package Controlador;



import java.sql.*;
import java.util.ArrayList;

import modelos.Actividades;
import modelos.Cuerponoticia;
import modelos.Cultura;
import modelos.Deportes;
import modelos.Eventos;
import modelos.Hoteles;
import modelos.InformacionWebScraper;
import modelos.Noticia;
import modelos.Parques;
import modelos.Restaurante;
import modelos.Rutas;


public class ConectarMySql {

	Connection con;
	Statement st;
	ResultSet rs;

	public void openConnection() {
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Leon","root","Mole");
			System.out.println("Connection to the database done: " +con.getClass().getName());
		}catch (Exception e) {
			System.out.println("Error in the connection. Detailed message:\n");
			System.out.println(e.getMessage());
		}
	}

	public void closeConnection() {
		try {
			con.close();
			System.out.println("Connection closed");
		}
		catch (SQLException e) {
			System.out.println("Error when closing connection");
		}
	}
	
	public void CargarNoticia() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM noticias;");
			System.out.println("Tabla noticias Cargada");

		}catch (Exception e) {
			System.out.println("Error al cargar noticias");
		}
	}
	

	

	public boolean IntroducirNoticia(Noticia n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into noticias(Titulo,Imagen,Fecha) values(?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setBlob(2, n.getImagen());
	            ps.setTimestamp(3, new java.sql.Timestamp(n.getFecha().getTime()) );
	            ps.executeUpdate();
			System.out.println("Element inserted");
			return true;
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
		return false;

	}

	
	public boolean actualizar(String tabla, String columna, String valor, String condicion) {
		boolean r = false;
		String u = " UPDATE " + tabla + " SET " + columna + "='" + valor + "' where " + condicion;

		try {
			try (PreparedStatement pstm = this.con.prepareStatement(u)) {
				pstm.execute();
				System.out.println("sisisi");
			}
			r = true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return r;
	}

	public boolean actualizarInt(String tabla, String columna, int val, String condicion) {
		// TODO Auto-generated method stub
		boolean r = false;
		String u = " UPDATE " + tabla + " SET " + columna + "='" + val + "' where " + condicion;

		try {
			try (PreparedStatement pstm = this.con.prepareStatement(u)) {
				pstm.execute();
				System.out.println("sis");
			}
			r = true;
		} catch (SQLException e) {
			System.out.println(e);
		}
		return r;
	}

	public void CargarCuerponoticia() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM cuerponoticias;");
			System.out.println("Tabla cuerponoticias Cargada");

		}catch (Exception e) {
			System.out.println("Error al cargar cuerponoticias");
		}
		
	}
	public void CargarCultura() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM cultura;");
			System.out.println("Tabla noticias cultura");

		}catch (Exception e) {
			System.out.println("Error al cargar cultura");
		}
		
	}
	public void CargarDeportes() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM deportes;");
			System.out.println("Tabla noticias deportes");

		}catch (Exception e) {
			System.out.println("Error al cargar deportes");
		}
		
	}
	public void CargarActividades() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM actividades;");
			System.out.println("Tabla  actividades");

		}catch (Exception e) {
			System.out.println("Error al cargar actividades");
		}
		
	}
	public void CargarEventos() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM eventos;");
			System.out.println("Tabla  eventos");

		}catch (Exception e) {
			System.out.println("Error al cargar eventos");
		}
		
	}
	public void CargarHoteles() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM hoteles;");
			System.out.println("Tabla hoteles");

		}catch (Exception e) {
			System.out.println("Error al cargar hoteles");
		}
		
	}
	public void CargarIWS() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM informacionwebscraper;");
			System.out.println("Tabla informacionwebscraper");

		}catch (Exception e) {
			System.out.println("Error al cargar informacionwebscraper");
		}
		
	}
	public void CargarParques() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM parques;");
			System.out.println("Tabla parques");

		}catch (Exception e) {
			System.out.println("Error al cargar parques");
		}
		
	}
	public void CargarRestaurantes() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM restaurantes;");
			System.out.println("Tabla restaurantes");

		}catch (Exception e) {
			System.out.println("Error al cargar restaurantes");
		}
		
	}
	public void CargarRutas() {
		try {
			st=con.createStatement();
			rs=st.executeQuery("SELECT * FROM rutas;");
			System.out.println("Tabla rutas");

		}catch (Exception e) {
			System.out.println("Error al cargar rutas");
		}
		
	}
	
	public ArrayList<Noticia> getNoticias() {
		ArrayList<Noticia> Noticias = new ArrayList<>();
		Noticia n;
		try {
			while(rs.next()) {
			 n=new Noticia();
			 n.setIdNoticia(rs.getInt("idNoticia"));
			 n.setTitulo(rs.getString("Titulo"));
			 Noticias.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar Noticias");
		}
		return Noticias;
	}
	public ArrayList<Cuerponoticia> getCuerposNoticia() {
		return null;
	}

	public ArrayList<Rutas> getRutas() {
		ArrayList<Rutas> rutas = new ArrayList<>();
		Rutas n;
		try {
			while(rs.next()) {
			 n=new Rutas();
			 n.setIdRuta(rs.getInt(1));
			 n.setTitulo(rs.getString("Titulo"));
			 rutas.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar Rutas");
		}
		return rutas;
	}

	public ArrayList<Restaurante> getRestaurantes() {
		ArrayList<Restaurante> restaurantes=new ArrayList<>();
		Restaurante n;
		try {
			while(rs.next()) {
			 n=new Restaurante();
			 n.setIdRestaurante(rs.getInt(1));
			 n.setTitulo(rs.getString("Titulo"));
			 restaurantes.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar Restaurantes");
		}
		return restaurantes;
	}

	public ArrayList<Parques> getParques() {
		ArrayList<Parques> parques=new ArrayList<>();
		Parques n;
		try {
			while(rs.next()) {
			 n=new Parques();
			 n.setIdParque(rs.getInt(1));
			 n.setTitulo(rs.getString("Titulo"));
			 parques.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar Parques");
		}
		return parques;
	}

	public ArrayList<InformacionWebScraper> getIWS() {
		ArrayList<InformacionWebScraper> IWSs=new ArrayList<>();
		InformacionWebScraper n;
		try {
			while(rs.next()) {
			 n=new InformacionWebScraper();
			 n.setNombre(rs.getString(1));
			 n.setEstado(rs.getString("Estado"));
			 IWSs.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar IWS");
		}
		return IWSs;
	}

	public ArrayList<Hoteles> getHoteles() {
		ArrayList<Hoteles> hoteles=new ArrayList<>();
		Hoteles n;
		try {
			while(rs.next()) {
			 n=new Hoteles();
			 n.setIdHoteles(rs.getInt(1));
			 n.setTitulo(rs.getString("Titulo"));
			 hoteles.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar hoteles");
		}
		return hoteles;
	}

	public ArrayList<Eventos> getEventos() {
		ArrayList<Eventos> eventos=new ArrayList<>();
		Eventos n;
		try {
			while(rs.next()) {
			 n=new Eventos();
			 n.setIdEvento(rs.getInt(1));
			 n.setNombre(rs.getString("Nombre"));
			 eventos.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar eventos");
		}
		return eventos;
	}

	public ArrayList<Deportes> getDeportes() {
		ArrayList<Deportes> deportes=new ArrayList<>();
		Deportes n;
		try {
			while(rs.next()) {
			 n=new Deportes();
			 n.setIdDeportes(rs.getInt(1));
			 n.setTitulo(rs.getString("Titulo"));
			 deportes.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar deportes");
		}
		return deportes;
	}

	public ArrayList<Cultura> getCultura() {
		ArrayList<Cultura> culturas=new ArrayList<>();
		Cultura n;
		try {
			while(rs.next()) {
			 n=new Cultura();
			 n.setIdCultura(rs.getInt(1));
			 n.setTitulo(rs.getString(2));
			 culturas.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar cultura");
		}
		return culturas;
	}

	public ArrayList<Actividades> getActividades() {
		ArrayList<Actividades> actividades=new ArrayList<>();
		Actividades n;
		try {
			while(rs.next()) {
			 n=new Actividades();
			 n.setIdActividad(rs.getInt(1));
			 n.setTitulo(rs.getString("Titulo"));
			 actividades.add(n);
			}
		}catch (Exception e) {
			System.out.println("Error al cargar actividad");
		}
		return actividades;
	}

	public void IntroducirCuerponoticia(Cuerponoticia n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into cuerponoticias(texto,idNoticia) values(?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTexto());
	            ps.setInt(2, n.getNoticia().getIdNoticia());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	
	}

	public void IntroducirActividad(Actividades n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into actividades(Titulo,Precio,Url,Direccion,Imagen,Tipo) values(?,?,?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getPrecio());
	            ps.setString(3, n.getUrl());
	            ps.setString(4, n.getDireccion());
	            ps.setBlob(5, n.getImagen());
	            ps.setString(6, n.getTipo());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	}

	public void IntroducirCultura(Cultura n) {
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into cultura(Titulo,Url,Imagen,Direccion) values(?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getUrl());
	            ps.setBlob(3, n.getImagen());
	            ps.setString(4, n.getDireccion());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
		
	}

	public void IntroducirDeporte(Deportes n) {
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into deportes(Titulo,Url,Imagen,Direccion) values(?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getUrl());
	            
	            ps.setBlob(3, n.getImagen());
	            ps.setString(4, n.getDireccion());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
		
	}

	public void IntroducirEvento(Eventos n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		  //   sql = "insert into eventos(Nombre,Url,Precio,Direccion,Tipo,Fecha,Imagen,FechaFin) values(?,?,?,?,?,?,?,?)";
		     sql = "insert into eventos(Nombre,Url,Precio,Direccion,Tipo,Fecha,Imagen,Hora) values(?,?,?,?,?,?,?,?)";
		           
		     ps = con.prepareStatement(sql);
	            ps.setString(1, n.getNombre());
	            ps.setString(2, n.getUrl());
	            ps.setString(3, n.getPrecio());
	            ps.setString(4, n.getDireccion());
	            
	            ps.setString(5, n.getTipo());
	            ps.setString(6, n.getFecha());
	            ps.setBlob(7, n.getImagen());
	            ps.setString(8, n.getHora());
	           // ps.setTimestamp(3, new java.sql.Timestamp(n.getFechaFin().getTime()) );
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	}

	public void IntroducirHotel(Hoteles n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into hoteles(Titulo,Url,Imagen,Direccion) values(?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getUrl());
	            ps.setBlob(3, n.getImagen());
	            ps.setString(4, n.getDireccion());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	}

	public void IntroducirParque(Parques n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into parques(Titulo,Url,Imagen,Direccion) values(?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getUrl());
	            ps.setBlob(3, n.getImagen());
	            ps.setString(4, n.getDireccion());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	}

	public void IntroducirRestaurante(Restaurante n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into restaurantes(Titulo,Url,Imagen,Direccion) values(?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getUrl());
	            ps.setBlob(3, n.getImagen());
	            ps.setString(4, n.getDireccion());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	}

	public void IntroducirRuta(Rutas n) {
		// TODO Auto-generated method stub
		try {
			 PreparedStatement ps;
		     String sql;
		     sql = "insert into rutas(Titulo,Url,Imagen,Direccion) values(?,?,?,?)";
	            ps = con.prepareStatement(sql);
	            ps.setString(1, n.getTitulo());
	            ps.setString(2, n.getUrl());
	            ps.setBlob(3, n.getImagen());
	            ps.setString(4, n.getDireccion());
	            ps.executeUpdate();
			System.out.println("Element inserted");
			
		}
		catch(SQLException e) {
			System.out.println(e);
			System.out.println("Error when inserting");
		}
	}
		
		public void IntroducirRutaDistancia(Rutas n) {
			// TODO Auto-generated method stub
			try {
				 PreparedStatement ps;
			     String sql;
			     sql = "insert into rutas(Titulo,Url,Imagen,Direccion,Distancia) values(?,?,?,?,?)";
		            ps = con.prepareStatement(sql);
		            ps.setString(1, n.getTitulo());
		            ps.setString(2, n.getUrl());
		            ps.setBlob(3, n.getImagen());
		            ps.setString(4, n.getDireccion());
		            ps.setString(5, n.getDistancia());
		            ps.executeUpdate();
				System.out.println("Element inserted");
				
			}
			catch(SQLException e) {
				System.out.println(e);
				System.out.println("Error when inserting");
			}
	}

	
	





}
