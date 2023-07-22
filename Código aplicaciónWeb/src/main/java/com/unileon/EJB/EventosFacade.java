/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Eventos;
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
public class EventosFacade extends AbstractFacade<Eventos> implements EventosFacadeLocal {

    @PersistenceContext(unitName = "leonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventosFacade() {
        super(Eventos.class);
    }
    
     public List<Eventos> obtentipo(String a){
        String consultaJPQL = "FROM Eventos a WHERE a.Tipo=:param1 ";
        Query query = em.createQuery(consultaJPQL);
        query.setParameter("param1", a);
        List<Eventos> resultado = query.getResultList();
        return resultado;
     }
    
}
