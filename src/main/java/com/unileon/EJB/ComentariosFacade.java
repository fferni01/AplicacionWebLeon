/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Comentarios;
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
public class ComentariosFacade extends AbstractFacade<Comentarios> implements ComentariosFacadeLocal {

    @PersistenceContext(unitName = "leonPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentariosFacade() {
        super(Comentarios.class);
    }
     public List<Comentarios> obtenComentariosRutas(Rutas ruta){
         
         
        String consultaJPQL = "FROM Comentarios u WHERE u.Ruta=:param1";
        Query query = em.createQuery(consultaJPQL);
         query.setParameter("param1", ruta);
        List<Comentarios> resultado = query.getResultList();
        if(!resultado.isEmpty())
            return null;
       
        return(resultado);
     }

    
}
