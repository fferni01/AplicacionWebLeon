/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Cuerponoticia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
public class CuerponoticiaFacade extends AbstractFacade<Cuerponoticia> implements CuerponoticiaFacadeLocal {

    @PersistenceContext(unitName = "leonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuerponoticiaFacade() {
        super(Cuerponoticia.class);
    }
    
}
