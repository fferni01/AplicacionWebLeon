/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Rutas;
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
public class RutasFacade extends AbstractFacade<Rutas> implements RutasFacadeLocal {

    @PersistenceContext(unitName = "leonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RutasFacade() {
        super(Rutas.class);
    }
   
    public boolean noExiste(Rutas Rutas){
         String consultaJPQL = "FROM Rutas p WHERE p.Titulo=:param1 ";
        Query query = em.createQuery(consultaJPQL);
        query.setParameter("param1", Rutas.getTitulo());
        List<Rutas> resultado = query.getResultList();
        if(!resultado.isEmpty()){
        System.out.println("Titulo: "+resultado.get(0).getTitulo());
        return false;
        }else{
     
        return true;
        }
    }
}
