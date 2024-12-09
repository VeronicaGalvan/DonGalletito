/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.donGalleto.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.Consumes;
import java.sql.SQLException;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.donGalleto.DAO.daorecetas;
import org.utl.donGalleto.model.recetas;
/**
 *
 * @author Sonrie
 */
@Path("receta")
public class restReceta {
    
     @Path("getall")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        String out;

        try {
            daorecetas cp = new daorecetas();
            List<recetas> libros = cp.getAllRecetas();
            Gson gs = new Gson();
            out = gs.toJson(libros);
        } catch (Exception ex) {
            out = "{\"error\":\"" + ex.toString() + "\"}";
        }

        return Response.status(Response.Status.OK).entity(out).build();
    }

    @Path("insert")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response insert(
            @FormParam("nombreReceta") String nombreReceta,
            @FormParam("receta") String receta) {
        try {
            // Asegúrate de que los parámetros no sean nulos o vacíos
            if (nombreReceta == null || receta == null || nombreReceta.isEmpty() || receta.isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\":\"Todos los campos son obligatorios\"}").build();
            }

            // Llamar al DAO para insertar la receta
            daorecetas daoRecetas = new daorecetas();
            daoRecetas.insertReceta(nombreReceta, receta);

            // Respuesta exitosa
            String jsonResponse = "{ \"message\": \"Receta insertada exitosamente\" }";
            return Response.status(Response.Status.CREATED).entity(jsonResponse).build();
        } catch (Exception e) {
            e.printStackTrace(); // Para otros errores
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"" + e.getMessage() + "\"}").build();
        }
    }
}
