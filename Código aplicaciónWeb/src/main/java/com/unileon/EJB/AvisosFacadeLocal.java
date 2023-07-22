/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Avisos;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface AvisosFacadeLocal {

    void create(Avisos avisos);

    void edit(Avisos avisos);

    void remove(Avisos avisos);

    Avisos find(Object id);

    List<Avisos> findAll();

    List<Avisos> findRange(int[] range);

    int count();
    
}
