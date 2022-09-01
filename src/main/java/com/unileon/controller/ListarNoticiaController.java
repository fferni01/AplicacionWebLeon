/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.controller;

import com.unileon.EJB.CuerponoticiaFacadeLocal;
import com.unileon.EJB.NoticiaFacadeLocal;
import com.unileon.modelo.Cuerponoticia;
import com.unileon.modelo.Noticia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Usuario
 */
@Named
@ViewScoped
public class ListarNoticiaController implements Serializable {

    @Inject
    private NoticiasController NoticiasCon;

    private Noticia noticia;
    private List<Cuerponoticia> cn;
    List<Cuerponoticia> cuerpo = new ArrayList<>();
    @EJB
    private NoticiaFacadeLocal NoticiaEJB;
    @EJB
    private CuerponoticiaFacadeLocal CuerpNoticiaEJB;

    @PostConstruct
    public void init() {
        noticia = NoticiasCon.getNoticia();
        cn = CuerpNoticiaEJB.findAll();
        obtenCuerpo();
    }

    public Noticia getNoticia() {
        return noticia;
    }

    public void setNoticia(Noticia noticia) {
        this.noticia = noticia;
    }

    public String obtenNoticia() {

        return "/resources/Imagenes/Noticias/" + noticia.getIdNoticia() + ".jpg";
    }

    private void obtenCuerpo() {

        for (int i = 0; i < cn.size(); i++) {
            if (noticia.getIdNoticia() == cn.get(i).getNoticia().getIdNoticia()) {
                cuerpo.add(cn.get(i));
            }
        }

    }

    public List<Cuerponoticia> getCuerpo() {
        return cuerpo;
    }

    

}
