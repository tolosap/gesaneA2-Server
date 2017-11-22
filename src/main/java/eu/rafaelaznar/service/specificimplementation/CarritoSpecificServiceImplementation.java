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

import com.google.gson.Gson;
import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.CarritoSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.LineadepedidoSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.PedidoSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.ProductoSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.connection.publicinterface.ConnectionInterface;
import eu.rafaelaznar.dao.specificimplementation.LineadepedidoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.PedidoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.ProductoSpecificDaoImplementation;
import eu.rafaelaznar.factory.ConnectionFactory;
import eu.rafaelaznar.helper.ConfigurationHelper;
import eu.rafaelaznar.helper.ConnectionHelper;
import eu.rafaelaznar.helper.GsonHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;

public class CarritoSpecificServiceImplementation {

    HttpServletRequest oRequest = null;

    public CarritoSpecificServiceImplementation(HttpServletRequest request) {
        oRequest = request;
    }

    private Boolean checkPermission(String strMethodName) throws Exception {
        UsuarioSpecificBeanImplementation oUsuarioBean = (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            return true;
        } else {
            return false;
        }
    }

    private CarritoSpecificBeanImplementation find(Integer id, ArrayList<CarritoSpecificBeanImplementation> alCarrito) {
        if (alCarrito != null) {
            Iterator<CarritoSpecificBeanImplementation> iterator = alCarrito.iterator();
            while (iterator.hasNext()) {
                CarritoSpecificBeanImplementation oCarritoBean = iterator.next();
                if (oCarritoBean.getObj_producto().getId() == id) {
                    return oCarritoBean;
                }
            }
        }
        return null;
    }

    public ReplyBeanHelper add() throws Exception {
        if (this.checkPermission("add")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBeanHelper oReplyBean = null;
            CarritoSpecificBeanImplementation oCarritoBeanEnCarrito = null;
            int id = Integer.parseInt(oRequest.getParameter("id"));
            int cantidad = Integer.parseInt(oRequest.getParameter("cantidad"));
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            if (alCarrito == null) {
                alCarrito = new ArrayList<CarritoSpecificBeanImplementation>();
            }
            oCarritoBeanEnCarrito = find(id, alCarrito);
            if (oCarritoBeanEnCarrito != null) {
                oCarritoBeanEnCarrito.setCantidad(oCarritoBeanEnCarrito.getCantidad() + cantidad);
            } else {
                try {
                    oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName());
                    oConnection = oPooledConnection.newConnection();
                    CarritoSpecificBeanImplementation oCarritoBean = new CarritoSpecificBeanImplementation();
                    oCarritoBean.setCantidad(cantidad);
                    ProductoSpecificDaoImplementation oProductoDao = new ProductoSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                    ProductoSpecificBeanImplementation oProductoBeanAdd = (ProductoSpecificBeanImplementation) oProductoDao.get(id, ConfigurationHelper.getJsonMsgDepth());
                    oCarritoBean.setId_producto(oProductoBeanAdd.getId());
                    oCarritoBean.setObj_producto(oProductoBeanAdd);
                    alCarrito.add(oCarritoBean);
                } catch (Exception ex) {
                    String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                    Log4jHelper.errorLog(msg, ex);
                    throw new Exception(msg, ex);
                } finally {
                    if (oConnection != null) {
                        oConnection.close();
                    }
                    if (ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName()) != null) {
                        ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName()).disposeConnection();
                    }
                }
            }
            oRequest.getSession().setAttribute("carrito",alCarrito);            
            oReplyBean = new ReplyBeanHelper(200, GsonHelper.getGson().toJson(alCarrito));
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper remove() throws Exception {
        if (this.checkPermission("remove")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            int id = Integer.parseInt(oRequest.getParameter("id"));
            ReplyBeanHelper oReplyBean = null;
            CarritoSpecificBeanImplementation oCarritoBeanEnCarrito = find(id, alCarrito);
            alCarrito.remove(oCarritoBeanEnCarrito);
            Gson oGson = GsonHelper.getGson();
            String strJson = oGson.toJson(alCarrito);
            oReplyBean = new ReplyBeanHelper(200, strJson);
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper list() throws Exception {
        if (this.checkPermission("list")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBeanHelper oReplyBean = null;
            Gson oGson = GsonHelper.getGson();
            String strJson = oGson.toJson(alCarrito);
            oReplyBean = new ReplyBeanHelper(200, strJson);
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper buy() throws Exception {
        if (this.checkPermission("buy")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            UsuarioSpecificBeanImplementation oUsuarioBean = (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user");
            ReplyBeanHelper oReplyBean = null;
            Connection oConnection = null;
            ConnectionInterface oPooledConnection = null;
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName());
                oConnection = oPooledConnection.newConnection();
                if (alCarrito != null && alCarrito.size() > 0) {
                    oConnection.setAutoCommit(false);
                    PedidoSpecificBeanImplementation oPedidoBean = new PedidoSpecificBeanImplementation();
                    oPedidoBean.setId_usuario(oUsuarioBean.getId());
                    oPedidoBean.setFecha(Calendar.getInstance().getTime());
                    PedidoSpecificDaoImplementation oPedidoDao = new PedidoSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                    oPedidoBean.setId(oPedidoDao.set(oPedidoBean));
                    //--                              
                    Iterator<CarritoSpecificBeanImplementation> iterator = alCarrito.iterator();
                    while (iterator.hasNext()) {
                        CarritoSpecificBeanImplementation oCarritoBean = iterator.next();
                        ProductoSpecificBeanImplementation oProductoBeanDeCarrito = oCarritoBean.getObj_producto();
                        ProductoSpecificDaoImplementation oProductoDao = new ProductoSpecificDaoImplementation(oConnection, (UsuarioSpecificBeanImplementation) oRequest.getSession().getAttribute("user"), null);
                        ProductoSpecificBeanImplementation oProductoBeanDeDB = (ProductoSpecificBeanImplementation) oProductoDao.get(oProductoBeanDeCarrito.getId(), ConfigurationHelper.getJsonMsgDepth());
                        if (oProductoBeanDeDB.getExistencias() > oCarritoBean.getCantidad()) {
                            LineadepedidoSpecificBeanImplementation oLineadepedidoBean = new LineadepedidoSpecificBeanImplementation();
                            oLineadepedidoBean.setCantidad(oCarritoBean.getCantidad());
                            oLineadepedidoBean.setId_pedido(oPedidoBean.getId());
                            oLineadepedidoBean.setId_producto(oProductoBeanDeCarrito.getId());
                            LineadepedidoSpecificDaoImplementation oLineadepedidoDao = new LineadepedidoSpecificDaoImplementation(oConnection, oUsuarioBean, null);
                            oLineadepedidoBean.setId(oLineadepedidoDao.set(oLineadepedidoBean));
                            oProductoBeanDeCarrito.setExistencias(oProductoBeanDeCarrito.getExistencias() - oCarritoBean.getCantidad());
                            oProductoDao.set(oProductoBeanDeCarrito);
                        }
                    }
                    alCarrito.clear();
                    oConnection.commit();
                }

            } catch (Exception ex) {
                oConnection.rollback();
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            } finally {
                if (oConnection != null) {
                    oConnection.close();
                }
                if (ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName()) != null) {
                    ConnectionFactory.getSourceConnection(ConnectionHelper.getSourceConnectionName()).disposeConnection();
                }
            }
            return oReplyBean = new ReplyBeanHelper(200, "Compra realizada correctamente");
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

    public ReplyBeanHelper empty() throws Exception {
        if (this.checkPermission("empty")) {
            ArrayList<CarritoSpecificBeanImplementation> alCarrito = (ArrayList) oRequest.getSession().getAttribute("carrito");
            ReplyBeanHelper oReplyBean = null;
            alCarrito.clear();
            Gson oGson = GsonHelper.getGson();
            String strJson = oGson.toJson(alCarrito);
            oReplyBean = new ReplyBeanHelper(200, strJson);
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, "Unauthorized operation");
        }
    }

}
