/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Restaurante;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface RestauranteFacadeLocal {

    void create(Restaurante restaurante);

    void edit(Restaurante restaurante);

    void remove(Restaurante restaurante);

    Restaurante find(Object id);

    List<Restaurante> findAll();

    List<Restaurante> findRange(int[] range);

    int count();
    
}
