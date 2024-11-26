
package org.utl.donGalleto.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.utl.donGalleto.bd.ConexionMysql;

/**
 *
 * @author Sonrie
 */
public class controllerLogin {

    private String usuario;
    private String contrasena;

    public controllerLogin(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public boolean verificarCredenciales() {
        String query = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasena = ?"; // "contrasena" en lugar de "contraseña"
        try (Connection conn = new ConexionMysql().open(); PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, usuario);
            pstm.setString(2, contrasena);  // "contraseña" sin ñ

            ResultSet rs = pstm.executeQuery();
            boolean existeUsuario = rs.next();  // Verifica si el usuario existe
            rs.close();
            return existeUsuario;
        } catch (SQLException e) {
            e.printStackTrace(); // Si hay error en la conexión o SQL
            return false;
        }
    }

}
