/*
 * Copyright (c) 2017-2018 
 *
 * by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com) & DAW students
 * 
 * GESANE: Free Open Source Health Management System
 *
 * Sources at:
 *                            https://github.com/rafaelaznar/gesane-server
 *                            https://github.com/rafaelaznar/gesane-client
 *                            https://github.com/rafaelaznar/gesane-database
 *
 * GESANE is distributed under the MIT License (MIT)
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

import com.google.gson.Gson;
import eu.rafaelaznar.service.genericimplementation.TableGenericServiceImplementation;
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.connection.publicinterface.ConnectionInterface;
import eu.rafaelaznar.dao.specificimplementation.UsuarioSpecificDaoImplementation;
import eu.rafaelaznar.factory.ConnectionFactory;
import eu.rafaelaznar.helper.constant.ConnectionConstants;
import eu.rafaelaznar.dao.publicinterface.MetaDaoInterface;
import eu.rafaelaznar.dao.specificimplementation.GrupoSpecificDaoImplementation;
import eu.rafaelaznar.factory.DaoFactory;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.helper.GsonHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import eu.rafaelaznar.helper.RandomHelper;
import eu.rafaelaznar.helper.constant.ConfigurationConstants;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioSpecificServiceImplementation extends TableGenericServiceImplementation {

    public UsuarioSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    public ReplyBeanHelper getallobjectsmetadata() throws Exception {
        ReplyBeanHelper oReplyBean = null;
        HashMap hmObjectsMetaData = new HashMap();
        MetaDaoInterface oDao = null;

        oDao = DaoFactory.getDao("usuario", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("usuario", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("tipousuario", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("tipousuario", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("centro", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("centro", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("centrosanitario", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("centrosanitario", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("curso", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("curso", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("grupo", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("grupo", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("tipoepisodio", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("tipoepisodio", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("especialidad", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("especialidad", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("destinoalta", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("destinoalta", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("tipopago", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("tipopago", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("sexo", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("sexo", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("circunstanciasalta", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("circunstanciasalta", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("modalidadepisodio", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("modalidadepisodio", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("tipodependencia", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("tipodependencia", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("tiposervicio", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("tiposervicio", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("factura", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("factura", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("servicio", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("servicio", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("paciente", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("paciente", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("categoriaprofesional", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("categoriaprofesional", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("episodio", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("episodio", oDao.getObjectMetaData());
        oDao = DaoFactory.getDao("dependencia", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("dependencia", oDao.getObjectMetaData());

        oDao = DaoFactory.getDao("medico", null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
        hmObjectsMetaData.put("medico", oDao.getObjectMetaData());
        String strJson = GsonHelper.getGson().toJson(hmObjectsMetaData);
        oReplyBean = new ReplyBeanHelper(200, strJson);
        return oReplyBean;
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
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                UsuarioSpecificDaoImplementation oDao = new UsuarioSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                MetaBeanHelper oMetaBeanHelper = oDao.getFromLoginAndPass(oUsuarioBean);
                HttpSession oSession = oRequest.getSession();
                oSession.setAttribute("user", oMetaBeanHelper);
                String strJson = GsonHelper.getGson().toJson(oMetaBeanHelper);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
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
            MetaBeanHelper oMetaBeanHelper = (MetaBeanHelper) oSession.getAttribute("user");
            if (oMetaBeanHelper != null) {
                String strJson = GsonHelper.getGson().toJson(oMetaBeanHelper);
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } else {
                oReplyBean = new ReplyBeanHelper(401, null);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return oReplyBean;
    }

    public ReplyBeanHelper getSessionUserLevel() {
        ReplyBeanHelper oReplyBean = null;
        String strAnswer = null;
        MetaBeanHelper oUserBean = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
        Map<Integer, String> map = new HashMap<>();
        if (oUserBean == null) {
            oReplyBean = new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        } else {
            oReplyBean = new ReplyBeanHelper(200, EncodingHelper.quotate(((UsuarioSpecificBeanImplementation) oUserBean.getBean()).getId_tipousuario().toString()));
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
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                oConnection.setAutoCommit(false);
                UsuarioSpecificDaoImplementation oUserDao = new UsuarioSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                MetaBeanHelper oSessionUsuarioBeanMeta = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
                UsuarioSpecificBeanImplementation oSessionUsuarioBean = (UsuarioSpecificBeanImplementation) oSessionUsuarioBeanMeta.getBean();
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
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
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

    public ReplyBeanHelper checklogin() throws SQLException, Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBeanHelper oReplyBean = null;
        UsuarioSpecificBeanImplementation oPuser = null;
        String strAnswer = null;
        String strCode = "200";
        try {
            oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
            oConnection = oPooledConnection.newConnection();
            String login = oRequest.getParameter("login");
            if (!login.isEmpty()) {
                try {
                    UsuarioSpecificDaoImplementation oUserDao = new UsuarioSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                    if (oUserDao.getIDfromUser(login) == 0) {
                        oReplyBean = new ReplyBeanHelper(200, EncodingHelper.quotate("OK"));
                    } else {
                        oReplyBean = new ReplyBeanHelper(403, EncodingHelper.quotate("Server error during checklogin operation"));
                    }
                } catch (Exception ex) {
                    String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                    Log4jHelper.errorLog(msg, ex);
                    throw new Exception(msg, ex);
                }
            }
        } catch (Exception ex) {
            if (oConnection != null) {
                oConnection.rollback();
            }
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
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
    }

    public ReplyBeanHelper setalumno() throws Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBeanHelper oReplyBean = null;
        UsuarioSpecificBeanImplementation oPuser = null;
        try {
            oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
            oConnection = oPooledConnection.newConnection();
            UsuarioSpecificBeanImplementation oUser = new UsuarioSpecificBeanImplementation();
            UsuarioSpecificDaoImplementation oUserDao = new UsuarioSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
            Gson oGson = GsonHelper.getGson();
            oUser = oGson.fromJson(oRequest.getParameter("json"), oUser.getClass());
            oUser.setId_tipousuario(4);
            oUser.setActivo(0);
            oUser.setValidado(0);
            java.util.Date dt = new java.util.Date();
            oUser.setFecha_alta(dt);
            oUser.setToken(RandomHelper.getToken(ConfigurationConstants.tokenSize));
            Integer iResult = oUserDao.set(oUser);
            if (iResult >= 1) {
                oReplyBean = new ReplyBeanHelper(200, EncodingHelper.quotate(iResult.toString()));
            } else {
                oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Server error during new setalumno operation"));
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
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
    }

    public ReplyBeanHelper getidcurso() throws SQLException, Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBeanHelper oReplyBean = null;
        UsuarioSpecificBeanImplementation oPuser = null;
        try {
            oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
            oConnection = oPooledConnection.newConnection();
            String codigo = oRequest.getParameter("codigo");
            if (!codigo.isEmpty()) {
                UsuarioSpecificDaoImplementation oUserDao = new UsuarioSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                GrupoSpecificDaoImplementation oGrupoDao = new GrupoSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                MetaBeanHelper oGrupoMBH = oGrupoDao.get(oUserDao.getIDfromCodigoGrupo(codigo), 2);
                oReplyBean = new ReplyBeanHelper(200, GsonHelper.getGson().toJson(oGrupoMBH));
            }
        } catch (Exception ex) {
            if (oConnection != null) {
                oConnection.rollback();
            }
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
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
    }

}
