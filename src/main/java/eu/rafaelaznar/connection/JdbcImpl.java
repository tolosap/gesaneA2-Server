/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.connection;

import java.sql.Connection;
import java.sql.SQLException;


public class JdbcImpl implements ConnectionInterface {

    private Connection con;

    @Override
    public Connection newConnection() throws Exception {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlOdbc = "jdbc:mysql://localhost:3306/usuariodb";
            con = (java.sql.DriverManager.getConnection(urlOdbc, "root", "bitnami"));
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se ha podido establecer la conexion" + e.getMessage());
        }
    }

    @Override
    public void disposeConnection() throws Exception {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("No se ha podido cerrar la conexion" + e.getMessage());
        }
    }

}
