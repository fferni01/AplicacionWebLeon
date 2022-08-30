/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Rutas;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface RutasFacadeLocal {

    void create(Rutas rutas);

    void edit(Rutas rutas);

    void remove(Rutas rutas);

    Rutas find(Object id);

    List<Rutas> findAll();

    List<Rutas> findRange(int[] range);

    int count();

    public boolean noExiste(Rutas Rutas);
    
}
