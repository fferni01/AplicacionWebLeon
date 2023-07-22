/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Parques;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ParquesFacadeLocal {

    void create(Parques parques);

    void edit(Parques parques);

    void remove(Parques parques);

    Parques find(Object id);

    List<Parques> findAll();

    List<Parques> findRange(int[] range);

    int count();

    public boolean comprueba(Parques parque);
    
}
