/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.UsuarioFacadeLocal;
import com.unileon.modelo.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class IniciarSesionController implements Serializable{
    private Usuario usuario;
    private String nus;
    
    @EJB
    private UsuarioFacadeLocal usuarioEJB;
    
    @PostConstruct
    public void init(){
        usuario = new Usuario();

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getNus() {
        return nus;
    }

    public void setNus(String nus) {
        this.nus = nus;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
     public String verificarUsuario(){
        Usuario nuevo;
        nuevo = usuarioEJB.consultarUsuario(usuario);
       if(nuevo == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña incorrecta"));
       }        
       else{
          if(EsAdmin(nuevo)){
              if(nuevo.getFechaAcceso()==null){
                  return "/Private/Admin/RegistrarAdmin.xhtml?faces-redirect=true";
              }else{
                  return "/Private/Usuario/PrincipalUsuario.xhtml?faces-redirect=true";
              }
               
           }else
           nuevo.setFechaAcceso(new Date());
           usuarioEJB.edit(nuevo);
            return "/Private/Usuario/PrincipalUsuario.xhtml?faces-redirect=true";
       }
        return null;
        
    }
     
    public String destruirSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("com.unileon.controller.MenuController.destruirSesion()");
        return "../../index.xhtml";
    }

    private boolean EsAdmin(Usuario nuevo) {
      if(nuevo.getTipo()==1){
          return true;
      }else{
          return false;
      }
    }
    
    public void enviarConGMail() {
    // Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el remitente también.
    String remitente = "052fer5";  //Para la dirección nomcuenta@gmail.com
    List<Usuario>users= usuarioEJB.findAll();
        if(buscausuario(users)){
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");  //El servidor SMTP de Google
    props.setProperty("mail.smtp.starttls.enable", "true"); //Para conectar de manera segura al servidor SMTP
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.setProperty("mail.smtp.port", "587"); //El puerto SMTP seguro de Google

    props.setProperty("mail.smtp.user", remitente);
    props.setProperty("mail.smtp.auth", "true");    //Usar autenticación mediante usuario y clave
   // props.setProperty("mail.smtp.clave", "miClaveDeGMail");    //La clave de la cuenta
 
   
    Session session = Session.getDefaultInstance(props);
    MimeMessage message = new MimeMessage(session);
    
     try {
        message.setFrom(new InternetAddress(remitente));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getEmail()));   //Se podrían añadir varios de la misma manera
        message.setSubject("Recuperacion de contraseña");
        message.setText("La contraseña de la cuenta es "+usuario.getPassword());
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", remitente, "uoxkznekqoalmxiv");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Se ha enviado la contraseña al correo electronico"));
                 
    }
    catch (MessagingException me) {
        me.printStackTrace();   //Si se produce un error
    }
        }else{
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario no esta registrado en la aplicacion"));
                  
        }
       
    
}

    private boolean buscausuario(List<Usuario> users) {
        System.out.println(usuario.getUsuario());
        for (int i = 0; i < users.size(); i++) {
            if (nus.equals(users.get(i).getUsuario())) {
                usuario = users.get(i);
                return true;
            }
        }
        return false;
    }
    
}
