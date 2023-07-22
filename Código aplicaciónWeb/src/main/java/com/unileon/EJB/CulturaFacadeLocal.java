/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Cultura;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface CulturaFacadeLocal {

    void create(Cultura cultura);

    void edit(Cultura cultura);

    void remove(Cultura cultura);

    Cultura find(Object id);

    List<Cultura> findAll();

    List<Cultura> findRange(int[] range);

    int count();

    public boolean noExiste(Cultura Cultura);
    
}
