/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * carrito-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/carrito-server
 * 
 * carrito-server is distributed under the MIT License (MIT)
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.rafaelaznar.control;

import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.connection.ConnectionInterface;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.EstadoHelper;
import eu.rafaelaznar.helper.EstadoHelper.Tipo_estado;
import eu.rafaelaznar.helper.Log4j;
import eu.rafaelaznar.helper.MappingHelper;
import static eu.rafaelaznar.helper.ParameterCook.prepareCamelCaseObject;
import eu.rafaelaznar.service.ViewServiceInterface;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class json extends HttpServlet {

    private void retardo(Integer iLast) {
        try {
            Thread.sleep(iLast);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, Exception {
        //response.setContentType("application/json;charset=UTF-8");
        ReplyBean oReplyBean = null;
        try (PrintWriter out = response.getWriter()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception ex) {
                oReplyBean = new ReplyBean(500, "Database Connection Error: Please contact your administrator");
            }
            retardo(0);
            String ob = prepareCamelCaseObject(request);
            String op = request.getParameter("op");
            if (("".equalsIgnoreCase(ob) && "".equalsIgnoreCase(op)) || (ob == null && op == null)) {
                Connection oConnection = null;
                ConnectionInterface oPooledConnection = null;
                try {
                    oPooledConnection = AppConfigurationHelper.getSourceConnection();
                    oConnection = oPooledConnection.newConnection();
                    response.setContentType("text/html;charset=UTF-8");
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Carrito server</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.print("<h1>Bienvenidos a carrito-server</h1>");
                    out.println("<h2>Servlet json at " + request.getContextPath() + "</h2>");
                    out.print("<h3>Conexión OK</h3>");
                    out.println("</body>");
                    out.println("</html>");
                } catch (Exception ex) {
                    response.setContentType("text/html;charset=UTF-8");
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Carrito server</title>");
                    out.println("</head>");
                    out.println("<body>");
                    out.print("<h1>Bienvenidos a carrito-server</h1>");
                    out.println("<h2>Servlet json at " + request.getContextPath() + "</h2>");
                    out.print("<h3>Conexión KO</h3>");
                    out.println("</body>");
                    out.println("</html>");
                } finally {
                    if (oConnection != null) {
                        oConnection.close();
                    }
                    if (oPooledConnection != null) {
                        oPooledConnection.disposeConnection();
                    }
                }
            } else {
                response.setContentType("application/json;charset=UTF-8");
                response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
                //response.setHeader("Access-Control-Allow-Methods", "PATCH, POST, GET, PUT, OPTIONS, DELETE");
                response.setHeader("Access-Control-Allow-Methods", "GET,POST");
                response.setHeader("Access-Control-Max-Age", "86400");
                response.setHeader("Access-Control-Allow-Credentials", "true");
                response.setHeader("Access-Control-Allow-Headers", "Origin, Accept, x-requested-with, Content-Type");
                try {
                    oReplyBean = (ReplyBean) MappingHelper.executeMethodService(request);
//                    String strClassName = "eu.rafaelaznar.service." + ob + "Service";
//                    ViewServiceInterface oService = (ViewServiceInterface) Class.forName(strClassName).getDeclaredConstructor(HttpServletRequest.class).newInstance(request);
//                    Method oMethodService = oService.getClass().getMethod(op);
//                    oReplyBean = (ReplyBean) oMethodService.invoke(oService);
                } catch (Exception ex) {
                    if (EstadoHelper.getTipo_estado() == Tipo_estado.Debug) {
                        out.println(ex);
                        ex.printStackTrace(out);
                    } else {
                        oReplyBean = new ReplyBean(500, "carrito-server error. Please, contact your administrator.");
                    }
                    Log4j.errorLog(this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName(), ex);
                    oReplyBean = new ReplyBean(500, "Object or Operation not found : Please contact your administrator");
                }
                out.print("{\"status\":" + oReplyBean.getCode() + ", \"json\":" + oReplyBean.getJson() + "}");
            }
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
        } catch (Exception ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(json.class.getName()).log(Level.SEVERE, null, ex);
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
