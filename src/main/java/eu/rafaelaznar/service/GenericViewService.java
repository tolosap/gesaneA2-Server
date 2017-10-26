/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.service;

import com.google.gson.Gson;
import eu.rafaelaznar.bean.GenericViewBean;
import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.bean.UsuarioBean;
import eu.rafaelaznar.connection.ConnectionInterface;
import eu.rafaelaznar.dao.DaoViewInterface;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.FilterBeanHelper;
import eu.rafaelaznar.helper.Log4j;
import eu.rafaelaznar.helper.MappingDaoHelper;
import eu.rafaelaznar.helper.ParameterCook;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;


/**
 *
 * @author raznara
 */
public class GenericViewService implements ViewServiceInterface {

    HttpServletRequest oRequest = null;
    String ob = null;

    public GenericViewService(HttpServletRequest request, String obj) {
        oRequest = request;
        ob = obj;
    }

    private Boolean checkPermission(String ob, String strMethodName) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
    * http://127.0.0.1:8081/carrito-server/json?ob=xxxxxx&op=getpage&np=1&rpp=10
     */
    @Override
    public ReplyBean getpage() throws Exception {
        if (this.checkPermission(ob, "getpage")) {
            int np = Integer.parseInt(oRequest.getParameter("np"));
            int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
            String strOrder = oRequest.getParameter("order");
            String strFilter = oRequest.getParameter("filter");
            LinkedHashMap<String, String> hmOrder = ParameterCook.getOrderParams(strOrder);
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            ArrayList<GenericViewBean> aloBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                DaoViewInterface oDao = MappingDaoHelper.getDao(ob, oConnection);
                aloBean = oDao.getPage(rpp, np, hmOrder, alFilter);
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(aloBean);
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
    * http://127.0.0.1:8081/carrito-server/json?ob=xxxxxxxx&op=getcount
     */
    @Override
    public ReplyBean getcount() throws Exception {
        if (this.checkPermission(ob, "getcount")) {
            Long lResult;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            String strFilter = oRequest.getParameter("filter");
            ArrayList<FilterBeanHelper> alFilter = ParameterCook.getFilterParams(strFilter);
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                DaoViewInterface oDao = MappingDaoHelper.getDao(ob, oConnection);                
                lResult = oDao.getCount(alFilter);
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(lResult);
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
