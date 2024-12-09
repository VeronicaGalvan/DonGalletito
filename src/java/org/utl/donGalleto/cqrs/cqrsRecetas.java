/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.donGalleto.cqrs;

import org.utl.donGalleto.DAO.daorecetas;
import org.utl.donGalleto.model.recetas;

/**
 *
 * @author Sonrie
 */
public class cqrsRecetas {
    
    private daorecetas daorecetas;

    public cqrsRecetas(daorecetas daorecetas) {
        this.daorecetas = daorecetas;
    }
    
    public String handleInsertReceta(recetas receta) throws Exception {
    // Validar la receta antes de insertarla
    validateReceta(receta);

    // Llamar al DAO para insertar la receta
    daorecetas daoRecetas = new daorecetas();
    daoRecetas.insertReceta(
        receta.getNombreReceta(),
        receta.getReceta()
    );
    return "Receta insertada exitosamente.";
}

// Método de validación para la receta
private void validateReceta(recetas receta) throws Exception {
    if (receta.getNombreReceta() == null || receta.getNombreReceta().isEmpty()) {
        throw new Exception("El nombre de la receta no puede estar vacío.");
    }
    if (receta.getReceta() == null || receta.getReceta().isEmpty()) {
        throw new Exception("La receta no puede estar vacía.");
    }
}

    
}
