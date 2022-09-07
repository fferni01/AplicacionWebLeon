/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.AvisosFacadeLocal;
import com.unileon.modelo.Avisos;
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

/**
 *
 * @author Usuario
 */
@Named
@RequestScoped
public class AvisosController implements Serializable{
    
    private List<Avisos>avisos;
    private Usuario usuario;
    private Avisos aviso;
    private Avisos avisoN;
    @EJB
    AvisosFacadeLocal AvisosEJB;
    
    @PostConstruct
    public void init(){
        avisos=AvisosEJB.findAll();
        compruebaSiHanPasadotresdias();
        avisos=AvisosEJB.findAll();
        aviso= new Avisos();
        avisoN=new Avisos();
        usuario= (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
    }

    public List<Avisos> getAvisos() {
        return avisos;
    }

    public String fechaProcesada(Date fecha) {
        String fechaP= fecha.toString();
        String[] a= fechaP.split(" ");
        String nueva= a[2]+" de "+ a[1]+" de "+a[a.length-1];
        return nueva;
        
    }

    public Avisos getAvisoN() {
        return avisoN;
    }

    public void setAvisoN(Avisos avisoN) {
        this.avisoN = avisoN;
    }
    
    public Avisos getAviso() {
        return aviso;
    }

    public void setAviso(Avisos aviso) {
        this.aviso = aviso;
        System.out.println(aviso.getAsunto());
    }
    
    public void modificar(){
        AvisosEJB.edit(aviso);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Aviso modificado"));
        avisos.clear();
        avisos=AvisosEJB.findAll();
    }
    public void eliminar(Avisos aviso){
        AvisosEJB.remove(aviso);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Aviso eliminado"));
        avisos.clear();
        avisos=AvisosEJB.findAll();
    }
    public void crear(){
          if(avisoN.getAsunto().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Faltan campos por rellenar"));
        }else if(avisoN.getDescripcion().equals("")){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Faltan campos por rellenar"));
        }else{
            avisoN.setFecha(new Date());
              AvisosEJB.create(avisoN);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Aviso creado"));
        avisos.clear();
        avisos=AvisosEJB.findAll();
        }
      
    }
    public void limpiar(){
        aviso= new Avisos();
    }
    
    public boolean crearIniciarSesion(){
        if(usuario==null){
            return true;
        }
        return false;
    }
    public boolean crearNormal(){
        if(usuario!=null){
            return true;
        }
        return false;
    }
    public boolean CompruebaUsuario(Avisos aviso){
        if(usuario==aviso.getUsuario()){
            return true;
        }
        return false;
    }
     public boolean combruebaUs() {
        if(usuario.getTipo()==0){
            return true;
        }else{
            return false;
        }
    }

    private void compruebaSiHanPasadotresdias() {
       
       
        for (int i = 0; i < avisos.size(); i++) {
            if(diasEntreDosFechas(avisos.get(i).getFecha(),new Date())>3){
                AvisosEJB.remove(avisos.get(i));
            }
        }
    }

  
     public static long diasEntreDosFechas(Date fechaDesde, Date fechaHasta){
     long startTime = fechaDesde.getTime() ;
     long endTime = fechaHasta.getTime();
     long diasDesde = (long) Math.floor(startTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora 
     long diasHasta = (long) Math.floor(endTime / (1000*60*60*24)); // convertimos a dias, para que no afecten cambios de hora
     long dias = diasHasta - diasDesde;

     return dias;

    }
    
}
