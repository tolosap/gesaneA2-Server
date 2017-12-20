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
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.TipousuarioSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.connection.publicinterface.ConnectionInterface;
import eu.rafaelaznar.dao.specificimplementation.PacienteProfesorSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.PacienteSpecificDaoImplementation;
import eu.rafaelaznar.factory.BeanFactory;
import eu.rafaelaznar.factory.ConnectionFactory;
import eu.rafaelaznar.helper.GsonHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import eu.rafaelaznar.helper.constant.ConnectionConstants;
import eu.rafaelaznar.service.genericimplementation.TableGenericServiceImplementation;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

public class PacienteSpecificServiceImplementation extends TableGenericServiceImplementation {

    public PacienteSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    @Override
    protected Boolean checkPermission(String strMethodName) {
        MetaBeanHelper oUsuarioBean = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            UsuarioSpecificBeanImplementation oUsuario = (UsuarioSpecificBeanImplementation) oUsuarioBean.getBean();
            MetaBeanHelper oMetaBeanHelper = oUsuario.getObj_tipousuario();
            TipousuarioSpecificBeanImplementation oTipousuario = (TipousuarioSpecificBeanImplementation) oMetaBeanHelper.getBean();
            Integer idTipousuario = oTipousuario.getId();

            String strMethod = strMethodName.toLowerCase();
            switch (idTipousuario) {
                case 1:
                    return true;
                case 2:
                    return false;
                case 3:
                    switch (strMethod) {
                        case "getmetadata":
                            return true;
                        case "getobjectmetadata":
                            return true;
                        case "getpropertiesmetadata":
                            return true;
                        case "get":
                            return true;
                        case "setnew":
                            return true;
                        case "setedit":
                            Connection oConnection = null;
                            ConnectionInterface oPooledConnection = null;
                             {
                                try {
                                    oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                                    oConnection = oPooledConnection.newConnection();
                                    PacienteProfesorSpecificDaoImplementation oDao = new PacienteProfesorSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                                    String jason = oRequest.getParameter("json");
                                    TableGenericBeanImplementation oBean = (TableGenericBeanImplementation) BeanFactory.getBean(ob);
                                    Gson oGson = GsonHelper.getGson();
                                    oBean = oGson.fromJson(jason, oBean.getClass());
                                    oDao.checkUpdate(oBean.getId());
                                } catch (Exception ex) {
                                    String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                                    Log4jHelper.errorLog(msg, ex);
                                } finally {
                                    if (oConnection != null) {
                                        try {
                                            oConnection.close();
                                        } catch (SQLException ex) {
                                            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                                            Log4jHelper.errorLog(msg, ex);
                                        }
                                    }
                                    if (oPooledConnection != null) {
                                        try {
                                            oPooledConnection.disposeConnection();
                                        } catch (Exception ex) {
                                            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                                            Log4jHelper.errorLog(msg, ex);
                                        }
                                    }
                                }
                            }
                            return true;
                        case "remove":
                            return true;
                        case "getpage":
                            return true;
                        case "getcount":
                            return true;
                    }
                    break;
                case 4:
                    switch (strMethod) {
                        case "getmetadata":
                            return true;
                        case "getobjectmetadata":
                            return true;
                        case "getpropertiesmetadata":
                            return true;
                        case "get":
                            return true;
                        case "set":
                            return true;
                        case "remove":
                            return false;
                        case "getpage":
                            return true;
                        case "getcount":
                            return true;
                    }
                    break;
                case 5:
                    switch (strMethod) {
                        case "getmetadata":
                            return true;
                        case "getobjectmetadata":
                            return true;
                        case "getpropertiesmetadata":
                            return true;
                        case "get":
                            return true;
                        case "set":
                            return false;
                        case "remove":
                            return false;
                        case "getpage":
                            return true;
                        case "getcount":
                            return true;
                    }
                    break;
                default:
                    return false;
            }

        }
        return false;
    }

}
