/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Favoritos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface FavoritosFacadeLocal {

    void create(Favoritos favoritos);

    void edit(Favoritos favoritos);

    void remove(Favoritos favoritos);

    Favoritos find(Object id);

    List<Favoritos> findAll();

    List<Favoritos> findRange(int[] range);

    int count();
    
}
