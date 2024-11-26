/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.donGalleto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.donGalleto.bd.ConexionMysql;
import org.utl.donGalleto.model.recetas;

/**
 *
 * @author Sonrie
 */
public class daorecetas {
    
   public void insertReceta(String nombreReceta, String receta) {
    String query = "INSERT INTO recetas (nombreReceta, receta) VALUES (?, ?)";
    try (Connection conn = new ConexionMysql().open(); PreparedStatement pstm = conn.prepareStatement(query)) {
        pstm.setString(1, nombreReceta);
        pstm.setString(2, receta);  // Si receta es el nombre del archivo o la ruta

        int affectedRows = pstm.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("No se insertó ninguna receta.");
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Imprimir el error para depuración
        throw new RuntimeException("Error al insertar la receta", e);
    }
}


    // Método para obtener todas las recetas de la base de datos
    public List<recetas> getAllRecetas() throws SQLException {
        String query = "SELECT * FROM recetas";
        ConexionMysql cm = new ConexionMysql();
        Connection conn = cm.open();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<recetas> recetas = new ArrayList<>();
        while (rs.next()) {
            recetas.add(fill(rs));
        }
        rs.close();
        pstmt.close();
        cm.close();
        return recetas;
    }

    // Método para mapear los datos de la tabla a un objeto Receta
    public recetas fill(ResultSet rs) throws SQLException {
        recetas receta = new recetas();
        receta.setIdReceta(rs.getInt("idReceta"));
        receta.setNombreReceta(rs.getString("nombreReceta"));
        receta.setReceta(rs.getString("receta"));
        return receta;
    
}
}
