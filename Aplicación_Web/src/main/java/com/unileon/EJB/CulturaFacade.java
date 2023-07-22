/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Cultura;
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
public class CulturaFacade extends AbstractFacade<Cultura> implements CulturaFacadeLocal {

    @PersistenceContext(unitName = "leonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CulturaFacade() {
        super(Cultura.class);
    }
     public boolean noExiste(Cultura Cultura){
           String consultaJPQL = "FROM Cultura p WHERE p.Titulo=:param1 ";
        Query query = em.createQuery(consultaJPQL);
        query.setParameter("param1", Cultura.getTitulo());
        List<Cultura> resultado = query.getResultList();
        if(!resultado.isEmpty()){
        System.out.println("Titulo: "+resultado.get(0).getTitulo());
        return false;
        }else{
     
        return true;
        }
     }
}
