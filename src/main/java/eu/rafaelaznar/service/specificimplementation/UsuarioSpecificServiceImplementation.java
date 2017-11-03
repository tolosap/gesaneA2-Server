/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * generic-carrito-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/generic-carrito-server
 * 
 * generic-carrito-server is distributed under the MIT License (MIT)
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
package eu.rafaelaznar.service.specificimplementation;

import eu.rafaelaznar.service.genericimplementation.GenericTableService;
import com.google.gson.Gson;
import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.connection.ConnectionInterface;
import eu.rafaelaznar.dao.specificimplementation.UsuarioSpecificDaoImplementation;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.EncodingUtilHelper;
import eu.rafaelaznar.helper.Log4jConfigurationHelper;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioSpecificServiceImplementation extends GenericTableService {

    public UsuarioSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    public ReplyBean login() throws Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        UsuarioSpecificBeanImplementation oUsuarioBean = new UsuarioSpecificBeanImplementation();
        oUsuarioBean.setLogin(oRequest.getParameter("user"));
        oUsuarioBean.setPassword(oRequest.getParameter("pass"));
        if (!oUsuarioBean.getLogin().equalsIgnoreCase("") || !oUsuarioBean.getPassword().equalsIgnoreCase("")) {
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                UsuarioSpecificDaoImplementation oDao = new UsuarioSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                oUsuarioBean = oDao.getFromLoginAndPass(oUsuarioBean);
                HttpSession oSession = oRequest.getSession();
                oSession.setAttribute("user", oUsuarioBean);
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(oUsuarioBean);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jConfigurationHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
        }
        return oReplyBean;
    }

    public ReplyBean logout() throws Exception {
        HttpSession oSession = oRequest.getSession();
        oSession.invalidate();
        ReplyBean oReplyBean = new ReplyBean(200, EncodingUtilHelper.quotate("Session is closed"));
        return oReplyBean;
    }

    public ReplyBean getSessionStatus() throws Exception {
        ReplyBean oReplyBean = null;
        UsuarioSpecificBeanImplementation oUsuarioBean = null;
        try {
            HttpSession oSession = oRequest.getSession();
            oUsuarioBean = (UsuarioSpecificBeanImplementation) oSession.getAttribute("user");
            if (oUsuarioBean != null) {
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(oUsuarioBean);
                oReplyBean = new ReplyBean(200, strJson);
            } else {
                oReplyBean = new ReplyBean(401, null);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jConfigurationHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return oReplyBean;
    }

    public ReplyBean getSessionUserLevel() {
        ReplyBean oReplyBean = null;
        String strAnswer = null;
        UsuarioSpecificBeanImplementation oUserBean = (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user");
        Map<Integer, String> map = new HashMap<>();
        if (oUserBean == null) {
            oReplyBean = new ReplyBean(401, EncodingUtilHelper.quotate("Unauthorized"));
        } else {
            oReplyBean = new ReplyBean(200, EncodingUtilHelper.quotate(oUserBean.getId_tipousuario().toString()));
        }
        return oReplyBean;
    }

    public ReplyBean setPass() throws Exception {
        if (this.checkPermission("passchange")) {
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            String oldPass = oRequest.getParameter("old");
            String newPass = oRequest.getParameter("new");
            ReplyBean oReplyBean = null;
            Integer iResult = 0;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                oConnection.setAutoCommit(false);
                UsuarioSpecificDaoImplementation oUserDao = new UsuarioSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                UsuarioSpecificBeanImplementation oSessionUsuarioBean = (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user");
                if (oSessionUsuarioBean.getPassword().equalsIgnoreCase(oldPass)) {
                    oSessionUsuarioBean.setPassword(newPass);
                    iResult = oUserDao.set(oSessionUsuarioBean);
                    if (iResult >= 1) {
                        oReplyBean = new ReplyBean(200, EncodingUtilHelper.quotate(iResult.toString()));
                    } else {
                        oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Server error during password change operation"));
                    }
                } else {
                    oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate(iResult.toString()));
                }
                oConnection.commit();
            } catch (Exception ex) {
                if (oConnection != null) {
                    oConnection.rollback();
                }
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jConfigurationHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (oPooledConnection != null) {
                    oPooledConnection.disposeConnection();
                }
            }
            return oReplyBean;
        } else {
            return new ReplyBean(401, EncodingUtilHelper.quotate("Unauthorized"));
        }
    }

}
