/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * trolleyes-server3: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/trolleyes-server3
 * 
 * trolleyes-server3 is distributed under the MIT License (MIT)
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

import eu.rafaelaznar.service.genericimplementation.TableGenericServiceImplementation;
import com.google.gson.Gson;
import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.connection.publicinterface.ConnectionInterface;
import eu.rafaelaznar.dao.specificimplementation.UsuarioSpecificDaoImplementation;
import eu.rafaelaznar.factory.ConnectionFactory;
import eu.rafaelaznar.helper.ConnectionHelper;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.helper.GsonHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioSpecificServiceImplementation extends TableGenericServiceImplementation {

    public UsuarioSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    public ReplyBeanHelper login() throws Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBeanHelper oReplyBean = null;
        UsuarioSpecificBeanImplementation oUsuarioBean = new UsuarioSpecificBeanImplementation();
        oUsuarioBean.setLogin(oRequest.getParameter("user"));
        oUsuarioBean.setPassword(oRequest.getParameter("pass"));
        if (!oUsuarioBean.getLogin().equalsIgnoreCase("") || !oUsuarioBean.getPassword().equalsIgnoreCase("")) {
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName());
                oConnection = oPooledConnection.newConnection();
                UsuarioSpecificDaoImplementation oDao = new UsuarioSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                oUsuarioBean = oDao.getFromLoginAndPass(oUsuarioBean);
                HttpSession oSession = oRequest.getSession();
                oSession.setAttribute("user", oUsuarioBean);
                Gson oGson = GsonHelper.getGson();
                String strJson = oGson.toJson(oUsuarioBean);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jHelper.errorLog(msg, ex);
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

    public ReplyBeanHelper logout() throws Exception {
        HttpSession oSession = oRequest.getSession();
        oSession.invalidate();
        ReplyBeanHelper oReplyBean = new ReplyBeanHelper(200, EncodingHelper.quotate("Session is closed"));
        return oReplyBean;
    }

    public ReplyBeanHelper getSessionStatus() throws Exception {
        ReplyBeanHelper oReplyBean = null;
        UsuarioSpecificBeanImplementation oUsuarioBean = null;
        try {
            HttpSession oSession = oRequest.getSession();
            oUsuarioBean = (UsuarioSpecificBeanImplementation) oSession.getAttribute("user");
            if (oUsuarioBean != null) {
                Gson oGson = GsonHelper.getGson();
                String strJson = oGson.toJson(oUsuarioBean);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } else {
                oReplyBean = new ReplyBeanHelper(401, null);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return oReplyBean;
    }

    public ReplyBeanHelper getSessionUserLevel() {
        ReplyBeanHelper oReplyBean = null;
        String strAnswer = null;
        UsuarioSpecificBeanImplementation oUserBean = (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user");
        Map<Integer, String> map = new HashMap<>();
        if (oUserBean == null) {
            oReplyBean = new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        } else {
            oReplyBean = new ReplyBeanHelper(200, EncodingHelper.quotate(oUserBean.getId_tipousuario().toString()));
        }
        return oReplyBean;
    }

    public ReplyBeanHelper setPass() throws Exception {
        if (this.checkPermission("passchange")) {
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            String oldPass = oRequest.getParameter("old");
            String newPass = oRequest.getParameter("new");
            ReplyBeanHelper oReplyBean = null;
            Integer iResult = 0;
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName());
                oConnection = oPooledConnection.newConnection();
                oConnection.setAutoCommit(false);
                UsuarioSpecificDaoImplementation oUserDao = new UsuarioSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                UsuarioSpecificBeanImplementation oSessionUsuarioBean = (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user");
                if (oSessionUsuarioBean.getPassword().equalsIgnoreCase(oldPass)) {
                    oSessionUsuarioBean.setPassword(newPass);
                    iResult = oUserDao.set(oSessionUsuarioBean);
                    if (iResult >= 1) {
                        oReplyBean = new ReplyBeanHelper(200, EncodingHelper.quotate(iResult.toString()));
                    } else {
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Server error during password change operation"));
                    }
                } else {
                    oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate(iResult.toString()));
                }
                oConnection.commit();
            } catch (Exception ex) {
                if (oConnection != null) {
                    oConnection.rollback();
                }
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jHelper.errorLog(msg, ex);
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
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

}
