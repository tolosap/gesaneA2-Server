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
package eu.rafaelaznar.dao;

import eu.rafaelaznar.bean.UsuarioBean;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.FilterBeanHelper;
import eu.rafaelaznar.helper.Log4j;
import eu.rafaelaznar.helper.SqlBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class UsuarioDao extends GenericTableDao {

    public UsuarioDao(Connection oPooledConnection, UsuarioBean oPuserBean_security, String strWhere) {
        super("usuario", oPooledConnection, oPuserBean_security, strWhere);
    }

    public UsuarioBean getFromLoginAndPass(UsuarioBean oUsuarioBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
 
        strSQL += " AND login='" + oUsuarioBean.getLogin() + "'";
        strSQL += " AND pass='" + oUsuarioBean.getPass() + "'";
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean.setId(oResultSet.getInt("id"));
                oUsuarioBean.setDni(oResultSet.getString("dni"));
                oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                oUsuarioBean.setPrimer_apellido(oResultSet.getString("primer_apellido"));
                oUsuarioBean.setSegundo_apellido(oResultSet.getString("segundo_apellido"));
                oUsuarioBean.setLogin(oResultSet.getString("login"));
                oUsuarioBean.setPass(oResultSet.getString("pass"));
                oUsuarioBean.setEmail(oResultSet.getString("email"));
                oUsuarioBean.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
                //pendiente la expansión ************************* %%%%%%
            } else {
                throw new Exception("UsuarioDao getFromLoginAndPass error");
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4j.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }

        }
        return oUsuarioBean;

    }
}
