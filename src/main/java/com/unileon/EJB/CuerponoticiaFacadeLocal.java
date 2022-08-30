/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Cuerponoticia;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface CuerponoticiaFacadeLocal {

    void create(Cuerponoticia cuerponoticia);

    void edit(Cuerponoticia cuerponoticia);

    void remove(Cuerponoticia cuerponoticia);

    Cuerponoticia find(Object id);

    List<Cuerponoticia> findAll();

    List<Cuerponoticia> findRange(int[] range);

    int count();
    
}
