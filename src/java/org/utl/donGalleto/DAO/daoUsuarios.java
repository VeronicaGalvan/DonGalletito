package org.utl.donGalleto.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.utl.donGalleto.bd.ConexionMysql;
import org.utl.donGalleto.model.Usuario;

/**
 *
 * @author Sonrie
 */
public class daoUsuarios {

    public void insertUsuarios(String usuario, String contrasena) {
        String query = "INSERT INTO Usuarios (usuario, contrasena) VALUES (?,?)";
        try (Connection conn = new ConexionMysql().open(); PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, usuario);
            pstm.setString(2, contrasena);

            int affectedRows = pstm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se insertó ningún usuario.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error para depuración
            throw new RuntimeException("Error al insertar el usuario", e);
        }
    }

    public List<Usuario> getAllUsuarios() throws SQLException {
        String query = "select * from Usuarios";
        ConexionMysql cm = new ConexionMysql();
        Connection conn = cm.open();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        List<Usuario> usuario = new ArrayList<>();
        while (rs.next()) {
            usuario.add(fill(rs));
        }
        rs.close();
        pstmt.close();
        cm.close();
        return usuario;
    }

    public Usuario fill(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(rs.getInt("idUsuario"));
        usuario.setUsuario(rs.getString("usuario"));
        usuario.setContrasena(rs.getString("contrasena"));
        return usuario;

    }

}
