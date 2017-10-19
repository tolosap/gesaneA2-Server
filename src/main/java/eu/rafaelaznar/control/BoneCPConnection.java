/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.control;

import eu.rafaelaznar.connection.BoneCPImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author raznara
 */
public class BoneCPConnection extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BoneCPConnection</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BoneCPConnection at " + request.getContextPath() + "</h1>");

            Class.forName("com.mysql.jdbc.Driver");

            String ob = request.getParameter("ob");
            String op = request.getParameter("op");

            if ("set".equalsIgnoreCase(op)) {
                BoneCPImpl oBone = new BoneCPImpl();
                Connection oConnection = oBone.newConnection();
                if ("usuario".equalsIgnoreCase(ob)) {
                    try {
                        oConnection.setAutoCommit(false);                                              
                        Statement st = oConnection.createStatement();
                        String strDni = request.getParameter("dni");
                        String strNombre = request.getParameter("nombre");
                        String strPrimer_apellido = request.getParameter("primerapellido");
                        st.executeUpdate("INSERT INTO usuario (`id`,`dni`,`nombre`,`primer_apellido`) VALUES(null,'" + strDni + "','" + strNombre + "','" + strPrimer_apellido + "')");
                        st.executeUpdate("INSERT INTO usuario (`id`,`dni`,`nombre`,`primer_apellido`) VALUES(null,'" + strDni + "','" + strPrimer_apellido + "','" + strNombre + "')");
                        oConnection.commit();
                        st.close();
                    } catch (Exception ex) {
                        oConnection.rollback();
                    } finally {
                        oConnection.close();
                    }
                }
            }
            if ("getall".equalsIgnoreCase(op)) {
                BoneCPImpl oBone = new BoneCPImpl();
                Connection oConnection = oBone.newConnection();
                if ("usuario".equalsIgnoreCase(ob)) {
                    try {
                        oConnection.setAutoCommit(false);
                        Statement st = oConnection.createStatement();
                        ResultSet resultSet = st.executeQuery("SELECT * FROM usuario");
                        out.print("<table>");
                        while (resultSet.next()) {
                            out.print("<tr><td>" + resultSet.getInt("id") + "</td><td>" + resultSet.getString("nombre") + "</td><td>" + resultSet.getString("primer_apellido") + "</td></tr>");
                        }
                        out.print("</table>");
                        oConnection.commit();
                        st.close();
                    } catch (Exception ex) {
                        oConnection.rollback();
                    } finally {
                        oConnection.close();
                    }
                }
            }

            if ("get".equalsIgnoreCase(op)) {
                out.println("<h2>Conectando ... </h2>");
                BoneCPImpl oBone = new BoneCPImpl();
                Connection oConnection = oBone.newConnection();
                out.println("<h2>Conectado ... </h2>");
                if ("usuario".equalsIgnoreCase(ob) || "tipousuario".equalsIgnoreCase(ob)) {
                    String id = request.getParameter("id");
                    PreparedStatement stmt = oConnection.prepareStatement("SELECT * FROM " + ob + " where id=?");
                    stmt.setInt(1, Integer.parseInt(id));
                    ResultSet resultSet = stmt.executeQuery();
                    out.print("<table>");
                    while (resultSet.next()) {
                        if ("usuario".equalsIgnoreCase(ob)) {
                            out.print("<tr><td>" + resultSet.getInt("id") + "</td><td>" + resultSet.getString("nombre") + "</td><td>" + resultSet.getString("primer_apellido") + "</td></tr>");
                        }
                        if ("tipousuario".equalsIgnoreCase(ob)) {
                            out.print("<tr><td>" + resultSet.getInt("id") + "</td><td>" + resultSet.getString("descripcion") + "</td></tr>");
                        }
                    }
                    out.print("</table>");
                    stmt.close();
                }
                oBone.disposeConnection();
                out.println("<h2>Desconectando ... </h2>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BoneCPConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BoneCPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BoneCPConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BoneCPConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
