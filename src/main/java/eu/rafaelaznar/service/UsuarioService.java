package eu.rafaelaznar.service;

import com.google.gson.Gson;
import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.bean.UsuarioBean;
import eu.rafaelaznar.connection.ConnectionInterface;
import eu.rafaelaznar.dao.UsuarioDao;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.Log4j;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuarioService implements EmptyServiceInterface, ViewServiceInterface, TableServiceInterface {

    HttpServletRequest oRequest = null;

    public UsuarioService(HttpServletRequest request) {
        oRequest = request;
    }

    private Boolean checkPermission(String strMethodName) {
        UsuarioBean oUsuarioBean = (UsuarioBean) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            return true;
        } else {
            return false;
        }
    }

    /*
    * http://127.0.0.1:8081/carrito-server/json?ob=usuario&op=get&id=1
     */
    @Override
    public ReplyBean get() throws Exception {
        int id = Integer.parseInt(oRequest.getParameter("id"));
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            UsuarioBean oBean = new UsuarioBean(id);
            UsuarioDao oDao = new UsuarioDao(oConnection);
            oBean = oDao.get(oBean, AppConfigurationHelper.getJsonMsgDepth());
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
    }

    @Override
    public ReplyBean getpage() throws Exception {
        int np = Integer.parseInt(oRequest.getParameter("np"));
        int rpp = Integer.parseInt(oRequest.getParameter("rpp"));
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        ArrayList<UsuarioBean> aloBean = null;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            UsuarioDao oDao = new UsuarioDao(oConnection);
            aloBean = oDao.getPage(rpp, np);
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
    }

    @Override
    public ReplyBean getcount() throws Exception {
        if (this.checkPermission("getcount")) {
            Long lResult;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            ReplyBean oReplyBean = null;
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                UsuarioDao oDao = new UsuarioDao(oConnection);
                lResult = oDao.getCount();
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

    @Override
    public ReplyBean set() throws Exception {
        String jason = oRequest.getParameter("jason");
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        UsuarioBean oBean = new UsuarioBean();
        Gson oGson = AppConfigurationHelper.getGson();
        oBean = oGson.fromJson(jason, oBean.getClass());
        if (oBean == null) {
            throw new Exception("Bean null en service set");
        }
        int iResult = 0;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            UsuarioDao oDao = new UsuarioDao(oConnection);
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
    }

    @Override
    public ReplyBean remove() throws Exception {
        int id = Integer.parseInt(oRequest.getParameter("id"));
        Boolean iResult = false;
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        try {
            oPooledConnection = AppConfigurationHelper.getSourceConnection();
            oConnection = oPooledConnection.newConnection();
            UsuarioDao oDao = new UsuarioDao(oConnection);
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
    }

    public ReplyBean login() throws Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBean oReplyBean = null;
        UsuarioBean oUsuarioBean = new UsuarioBean();
        oUsuarioBean.setLogin(oRequest.getParameter("user"));
        oUsuarioBean.setPass(oRequest.getParameter("pass"));
        if (!oUsuarioBean.getLogin().equalsIgnoreCase("") || !oUsuarioBean.getPass().equalsIgnoreCase("")) {
            try {
                oPooledConnection = AppConfigurationHelper.getSourceConnection();
                oConnection = oPooledConnection.newConnection();
                UsuarioDao oDao = new UsuarioDao(oConnection);
                oUsuarioBean = oDao.getFromLoginAndPass(oUsuarioBean);
                HttpSession oSession = oRequest.getSession();
                oSession.setAttribute("user", oUsuarioBean);
                Gson oGson = AppConfigurationHelper.getGson();
                String strJson = oGson.toJson(oUsuarioBean);
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
        }
        return oReplyBean;
    }

    public ReplyBean logout() throws Exception {
        HttpSession oSession = oRequest.getSession();
        oSession.invalidate();
        ReplyBean oReplyBean = new ReplyBean(200, "Session is closed");
        return oReplyBean;
    }

    public ReplyBean check() throws Exception {
        ReplyBean oReplyBean = null;
        UsuarioBean oUsuarioBean = null;
        try {
            HttpSession oSession = oRequest.getSession();
            oUsuarioBean = (UsuarioBean) oSession.getAttribute("user");
            Gson oGson = AppConfigurationHelper.getGson();
            String strJson = oGson.toJson(oUsuarioBean);
            oReplyBean = new ReplyBean(200, strJson);
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4j.errorLog(msg, ex);
            throw new Exception(msg, ex);
        }
        return oReplyBean;
    }
}
