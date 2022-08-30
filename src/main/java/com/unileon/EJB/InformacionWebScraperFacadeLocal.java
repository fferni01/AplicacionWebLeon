/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unileon.EJB;

import com.unileon.modelo.InformacionWebScraper;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Usuario
 */
@Local
public interface InformacionWebScraperFacadeLocal {

    void create(InformacionWebScraper informacionWebScraper);

    void edit(InformacionWebScraper informacionWebScraper);

    void remove(InformacionWebScraper informacionWebScraper);

    InformacionWebScraper find(Object id);

    List<InformacionWebScraper> findAll();

    List<InformacionWebScraper> findRange(int[] range);

    int count();
    
}
