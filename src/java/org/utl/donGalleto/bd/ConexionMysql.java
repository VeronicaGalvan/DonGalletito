/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.donGalleto.bd;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Sonrie
 */
public class ConexionMysql implements AutoCloseable {
     private Connection conn;

    public Connection open() {
        String user = "root";
        String password = "Veronicagalvan";
        String url = "jdbc:mysql://127.0.0.1:3306/donGalleto";
        String parametros = "?useSSL=false&useUnicode=true&characterEncoding=utf-8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url + parametros, user, password);
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
                // Puedes elegir lanzar una excepción específica aquí si es necesario
                throw new RuntimeException(e);
            }
        }
    }
}
