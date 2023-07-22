/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

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
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.inject.Named;
import org.primefaces.model.ResponsiveOption;

/**
 *
 * @author Usuario
 */
@Named
@RequestScoped ///mayby applicationScoped
public class PrincipalController implements Serializable {

    private List<Noticia> LNoticia;
    private List<Parques> LParques;
    private List<Cultura> LCultura;
    private List<Deportes> LDeportes;
    private List<Eventos> LEventos;
    private List<Hoteles> LHoteles;
    private List<Rutas> LRutas;
    private List<Actividades> LActividades;

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
    private List<ResponsiveOption> responsiveOptions;
Usuario usuario;
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

        LNoticia = procesaLN();
        LParques = ParqueEJB.findAll();
        LCultura = CulturaEJB.findAll();
        LDeportes = DeportesEJB.findAll();
        LEventos = EventosEJB.findAll();
        LHoteles = HotelesEJB.findAll();
        LRutas = RutasEJB.findAll();
        LActividades = procesaLA();
        
        
        responsiveOptions = new ArrayList<>();
        responsiveOptions.add(new ResponsiveOption("1024px", 3, 3));
        responsiveOptions.add(new ResponsiveOption("768px", 2, 2));
        responsiveOptions.add(new ResponsiveOption("560px", 1, 1));
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }
    
    public byte[] obtenActividad(Actividades Actividad){
        
     
      return Actividad.getImagen();
    }
       public boolean espacio(Actividades Actividad){
        
        if(Actividad.getTitulo().length()<38)
         return true;
        
        return false;
        
    }
    public String obtenFecha(Noticia n) {

        return fechaProcesada(n.getFecha());

    }

    public String obtenFecha() {

        return fechaProcesada(noticia.getFecha());

    }

    public String fechaProcesada(Date fecha) {
        if (fecha != null) {
            String fechaP = fecha.toString();
            String[] a = fechaP.split(" ");
            String nueva = a[2] + " de " + a[1] + " de " + a[a.length - 1];
            System.out.println(nueva);
            return nueva;
        }
        return "a";
    }

    public List<ResponsiveOption> getResponsiveOptions() {
        return responsiveOptions;
    }

    public List<Noticia> getLNoticia() {
        return LNoticia;
    }

    public void setLNoticia(List<Noticia> LNoticia) {
        this.LNoticia = LNoticia;
    }

    public List<Parques> getLParques() {
        return LParques;
    }

    public void setLParques(List<Parques> LParques) {
        this.LParques = LParques;
    }

    public List<Cultura> getLCultura() {
        return LCultura;
    }

    public void setLCultura(List<Cultura> LCultura) {
        this.LCultura = LCultura;
    }

    public List<Deportes> getLDeportes() {
        return LDeportes;
    }

    public void setLDeportes(List<Deportes> LDeportes) {
        this.LDeportes = LDeportes;
    }

    public List<Eventos> getLEventos() {
        return LEventos;
    }

    public void setLEventos(List<Eventos> LEventos) {
        this.LEventos = LEventos;
    }

    public List<Hoteles> getLHoteles() {
        return LHoteles;
    }

    public void setLHoteles(List<Hoteles> LHoteles) {
        this.LHoteles = LHoteles;
    }

    public List<Rutas> getLRutas() {
        return LRutas;
    }

    public void setLRutas(List<Rutas> LRutas) {
        this.LRutas = LRutas;
    }

    public List<Actividades> getLActividades() {
        return LActividades;
    }

    public void setLActividades(List<Actividades> LActividades) {
        this.LActividades = LActividades;
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public Parques getParque() {
        return parque;
    }

    public void setParque(Parques parque) {
        this.parque = parque;
    }

    public Cultura getCultura() {
        return Cultura;
    }

    public void setCultura(Cultura Cultura) {
        this.Cultura = Cultura;
    }

    public Deportes getDeportes() {
        return Deportes;
    }

    public void setDeportes(Deportes Deportes) {
        this.Deportes = Deportes;
    }

    public Eventos getEventos() {
        return Eventos;
    }

    public void setEventos(Eventos Eventos) {
        this.Eventos = Eventos;
    }

    public Hoteles getHoteles() {
        return Hoteles;
    }

    public void setHoteles(Hoteles Hoteles) {
        this.Hoteles = Hoteles;
    }

    public Rutas getRutas() {
        return Rutas;
    }

    public void setRutas(Rutas Rutas) {
        this.Rutas = Rutas;
    }

    public Actividades getActividad() {
        return Actividad;
    }

    public void setActividad(Actividades Actividad) {
        this.Actividad = Actividad;
    }
     public boolean combruebaUs() {
        if(usuario.getTipo()==0){
            return true;
        }else{
            return false;
        }
    }
  /*    public void eliminarContenido(){
         NoticiaEJB.remove();
         listaNoticia= NoticiaEJB.findAll();
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha Eliminado Noticia"));
     }*/

    private List<Actividades> procesaLA() {
        List<Actividades> procesaLA= ActividadesEJB.findAll();
        List<Actividades> resultado= new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            resultado.add(procesaLA.get(i));
        }
        return resultado;
    }

    private List<Noticia> procesaLN() {
        List<Noticia> procesaLA= NoticiaEJB.findAll();
        List<Noticia> resultado= new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            resultado.add(procesaLA.get(i));
        }
        return resultado;
    }

}
