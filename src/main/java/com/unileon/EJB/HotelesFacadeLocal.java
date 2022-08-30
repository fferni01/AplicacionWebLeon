/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Hoteles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface HotelesFacadeLocal {

    void create(Hoteles hoteles);

    void edit(Hoteles hoteles);

    void remove(Hoteles hoteles);

    Hoteles find(Object id);

    List<Hoteles> findAll();

    List<Hoteles> findRange(int[] range);

    int count();

    public boolean noExiste(Hoteles Hoteles);
    
}
