/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Comentarios;
import com.unileon.modelo.Rutas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ComentariosFacadeLocal {

    void create(Comentarios comentarios);

    void edit(Comentarios comentarios);

    void remove(Comentarios comentarios);

    Comentarios find(Object id);

    List<Comentarios> findAll();

    List<Comentarios> findRange(int[] range);

    int count();

    public List<Comentarios> obtenComentariosRutas(Rutas ruta);
    
}
