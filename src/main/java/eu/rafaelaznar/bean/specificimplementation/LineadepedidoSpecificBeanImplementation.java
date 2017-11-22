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
package eu.rafaelaznar.bean.specificimplementation;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;




public class LineadepedidoSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    private Integer cantidad;

    @Expose(serialize = false)
    private Integer id_pedido = 0;
    @Expose(deserialize = false)
    private PedidoSpecificBeanImplementation obj_pedido = null;

    @Expose(serialize = false)
    private Integer id_producto = 0;
    @Expose(deserialize = false)
    private ProductoSpecificBeanImplementation obj_producto = null;

    public LineadepedidoSpecificBeanImplementation() {
    }

    public LineadepedidoSpecificBeanImplementation(Integer id) {
        super(id);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public PedidoSpecificBeanImplementation getObj_pedido() {
        return obj_pedido;
    }

    public void setObj_pedido(PedidoSpecificBeanImplementation obj_pedido) {
        this.obj_pedido = obj_pedido;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    public ProductoSpecificBeanImplementation getObj_producto() {
        return obj_producto;
    }

    public void setObj_producto(ProductoSpecificBeanImplementation obj_producto) {
        this.obj_producto = obj_producto;
    }

//    @Override
//    public String getColumns() {
//        String strColumns = "";
//        strColumns += "id,";
//        strColumns += "cantidad,";
//        strColumns += "id_pedido,";
//        strColumns += "id_producto";
//        return strColumns;
//    }
//
//    @Override
//    public String getValues() {
//        String strColumns = "";
//        strColumns += id + ",";
//        strColumns += cantidad + ",";
//        strColumns += id_pedido + ",";
//        strColumns += id_producto;
//        return strColumns;
//    }
//
//    @Override
//    public String toPairs() {
//        String strPairs = "";
//        strPairs += "cantidad=" + cantidad + ",";
//        strPairs += "id_pedido=" + id_pedido + ",";
//        strPairs += "id_producto=" + id_producto;
//        return strPairs;
//    }
//
//    @Override
//    public GenericBeanInterface fill(ResultSet oResultSet, Connection oConnection, UsuarioSpecificBeanImplementation oPuserBean_security, Integer expand) throws SQLException, Exception {
//        this.setId(oResultSet.getInt("id"));
//        this.setCantidad(oResultSet.getInt("cantidad"));
//        this.setId_pedido(oResultSet.getInt("id_pedido"));
//        this.setId_producto(oResultSet.getInt("id_producto"));
//        if (expand > 0) {
//            PedidoSpecificBeanImplementation oPedidoBean = new PedidoSpecificBeanImplementation();
//            PedidoSpecificDaoImplementation oPedidoDao = new PedidoSpecificDaoImplementation(oConnection, oPuserBean_security, null);
//            oPedidoBean = (PedidoSpecificBeanImplementation) oPedidoDao.get(oResultSet.getInt("id_pedido"), expand - 1);
//            this.setObj_pedido(oPedidoBean);
//        } else {
//            this.setId_pedido(oResultSet.getInt("id_pedido"));
//        }
//
//        if (expand > 0) {
//            ProductoSpecificBeanImplementation oProductoBean = new ProductoSpecificBeanImplementation();
//            ProductoSpecificDaoImplementation oProductoDao = new ProductoSpecificDaoImplementation(oConnection, oPuserBean_security, null);
//            oProductoBean = (ProductoSpecificBeanImplementation) oProductoDao.get(oResultSet.getInt("id_producto"), expand - 1);
//            this.setObj_producto(oProductoBean);
//        } else {
//            this.setId_pedido(oResultSet.getInt("id_producto"));
//        }
//
//        return this;
//    }

}
