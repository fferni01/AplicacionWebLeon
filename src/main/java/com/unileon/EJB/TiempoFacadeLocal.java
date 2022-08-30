/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Tiempo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface TiempoFacadeLocal {

    void create(Tiempo tiempo);

    void edit(Tiempo tiempo);

    void remove(Tiempo tiempo);

    Tiempo find(Object id);

    List<Tiempo> findAll();

    List<Tiempo> findRange(int[] range);

    int count();
    
}
