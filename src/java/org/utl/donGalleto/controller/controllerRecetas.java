/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.donGalleto.controller;

import org.utl.donGalleto.DAO.daorecetas;
import org.utl.donGalleto.cqrs.cqrsRecetas;
import org.utl.donGalleto.model.recetas;

/**
 *
 * @author Sonrie
 */
public class controllerRecetas {
    
    private daorecetas daorecetas;
    private cqrsRecetas cqrsRecetas;

    public controllerRecetas(daorecetas daorecetas, cqrsRecetas cqrsRecetas) {
        this.daorecetas = daorecetas;
        this.cqrsRecetas = cqrsRecetas;
    }
    
    
     public String insert(recetas recetas) throws Exception {
        return cqrsRecetas.handleInsertReceta(recetas);
    }
     
       public void getAll(recetas recetas) throws Exception {
        daorecetas.getAllRecetas();
    }
}
