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
package eu.rafaelaznar.service;

import com.google.gson.Gson;
import eu.rafaelaznar.bean.GenericTableBean;
import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.bean.UsuarioBean;
import eu.rafaelaznar.connection.ConnectionInterface;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.Log4j;
import eu.rafaelaznar.helper.MappingBeanHelper;
import eu.rafaelaznar.helper.MappingDaoHelper;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;
import eu.rafaelaznar.dao.DaoTableInterface;

public class GenericTableService extends GenericViewService implements TableServiceInterface {

    public GenericTableService(HttpServletRequest request, String obj) {
        super(request, obj);
    }

    private Boolean checkPermission(String strMethodName) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ReplyBean get() throws Exception {
        if (this.checkPermission("get")) {
            int id = Integer.parseInt(oRequest.getParameter("id"));
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                DaoTableInterface oDao = (DaoTableInterface) MappingDaoHelper.getDao(ob, oConnection, (UsuarioBean) oRequest.getSession().getAttribute("userBean"), null);

                GenericTableBean oBean = (GenericTableBean) oDao.get(id, AppConfigurationHelper.getJsonMsgDepth());
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(oBean);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
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
            return new ReplyBean(401, "Unauthorized");
        }
    }

    /*
    * http://127.0.0.1:8081/carrito-server/json?ob=usuario&op=set (datos aparte)
     */
    @Override
    public ReplyBean set() throws Exception {
        if (this.checkPermission("set")) {
            String jason = oRequest.getParameter("jason");
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;

            GenericTableBean oBean = (GenericTableBean) MappingBeanHelper.getBean(ob);

            Gson oGson = AppConfigurationHelper.getGson();
            oBean = oGson.fromJson(jason, oBean.getClass());
            if (oBean == null) {
                throw new Exception("Bean null en service set");
            }
            int iResult = 0;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                DaoTableInterface oDao = (DaoTableInterface) MappingDaoHelper.getDao(ob, oConnection, (UsuarioBean) oRequest.getSession().getAttribute("userBean"), null);

                iResult = oDao.set(oBean);
                String strJson = oGson.toJson(iResult);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
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
            return new ReplyBean(401, "Unauthorized");
        }
    }

    /*
    * http://127.0.0.1:8081/carrito-server/json?ob=usuario&op=remove&id=1
     */
    @Override
    public ReplyBean remove() throws Exception {
        if (this.checkPermission("remove")) {
            int id = Integer.parseInt(oRequest.getParameter("id"));
            Boolean iResult = false;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();

                DaoTableInterface oDao = (DaoTableInterface) MappingDaoHelper.getDao(ob, oConnection, (UsuarioBean) oRequest.getSession().getAttribute("userBean"), null);

                iResult = oDao.remove(id);
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(iResult);
                oReplyBean = new ReplyBean(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4j.errorLog(msg, ex);
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
            return new ReplyBean(401, "Unauthorized");
        }
    }

}
