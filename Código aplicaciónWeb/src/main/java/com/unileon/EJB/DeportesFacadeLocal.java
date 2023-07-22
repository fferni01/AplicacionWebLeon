/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Deportes;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface DeportesFacadeLocal {

    void create(Deportes deportes);

    void edit(Deportes deportes);

    void remove(Deportes deportes);

    Deportes find(Object id);

    List<Deportes> findAll();

    List<Deportes> findRange(int[] range);

    int count();

    public boolean noExiste(Deportes Deportes);
    
}
