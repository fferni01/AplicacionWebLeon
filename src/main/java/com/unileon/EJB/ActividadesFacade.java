/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Actividades;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
public class ActividadesFacade extends AbstractFacade<Actividades> implements ActividadesFacadeLocal {

    @PersistenceContext(unitName = "leonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActividadesFacade() {
        super(Actividades.class);
    }
     @Override
    public boolean noExiste(Actividades Actividad){
         String consultaJPQL = "FROM Actividades a WHERE a.Titulo=:param1 ";
        Query query = em.createQuery(consultaJPQL);
        query.setParameter("param1", Actividad.getTitulo());
        List<Actividades> resultado = query.getResultList();
        if(!resultado.isEmpty()){
        return false;
        }else{
     
        return true;
        }
    }
    
   public List<Actividades> obtentipo(String a){
         String consultaJPQL = "FROM Actividades a WHERE a.Tipo=:param1 ";
        Query query = em.createQuery(consultaJPQL);
        query.setParameter("param1", a);
        List<Actividades> resultado = query.getResultList();
        return resultado;
   }
}
