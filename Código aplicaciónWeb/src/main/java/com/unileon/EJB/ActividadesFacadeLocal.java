/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.Actividades;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface ActividadesFacadeLocal {

    void create(Actividades actividades);

    void edit(Actividades actividades);

    void remove(Actividades actividades);

    Actividades find(Object id);

    List<Actividades> findAll();

    List<Actividades> findRange(int[] range);

    int count();

    public boolean noExiste(Actividades Actividad);

    public List<Actividades> obtentipo(String rutas_Guiadas_León);

    
    
}
