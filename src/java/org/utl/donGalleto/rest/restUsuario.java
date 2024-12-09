/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.donGalleto.rest;

import com.google.gson.Gson;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import org.utl.donGalleto.DAO.daoUsuarios;
import org.utl.donGalleto.controller.controllerLogin;
import org.utl.donGalleto.model.Usuario;

/**
 *
 * @author Sonrie
 */
@Path("inicioSesion")
public class restUsuario {
    
     @Path("autenticar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticar(
            @FormParam("usuario") String usuario,
            @FormParam("contrasena") String contrasena
    ) {
        try {
            controllerLogin controllerLogin = new controllerLogin(usuario, contrasena);
            boolean autenticado = controllerLogin.verificarCredenciales();
            
            if (autenticado) {
                // Usuario autenticado, devolver respuesta con estado HTTP 200 (OK)
                return Response.ok().build();
            } else {
                // Usuario no autenticado, devolver respuesta con estado HTTP 401 (Unauthorized)
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Error en el servidor, devolver respuesta con estado HTTP 500 (Internal Server Error)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }


    
 
}
