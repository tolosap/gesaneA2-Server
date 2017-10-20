/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.control;

import javax.servlet.http.HttpServlet;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {

    @Override
    public void init() {
 
        String prefix = getServletContext().getRealPath("/");
        String file = getInitParameter("log4j-carrito-server");
 
        if (file != null) {
            PropertyConfigurator.configure(prefix + file);
            System.out.println("Log4J Logging started: " + prefix + file);
        } else {
            System.out.println("Log4J Is not configured for your Application: " + prefix + file);
        }
    }

    
    
    
}
