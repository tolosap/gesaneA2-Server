/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * trolleyes-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/trolleyes-server
 * 
 * trolleyes-server is distributed under the MIT License (MIT)
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
package eu.rafaelaznar.helper;

import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.dao.specificimplementation.TipousuarioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.UsuarioSpecificDaoImplementation;
import java.sql.Connection;
import eu.rafaelaznar.dao.publicinterface.ViewDaoInterface;
import eu.rafaelaznar.dao.specificimplementation.LineadepedidoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.PedidoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.ProductoSpecificDaoImplementation;

public class MappingDaoHelper {

    public static ViewDaoInterface getDao(String ob, Connection oConnection, UsuarioSpecificBeanImplementation oPuserBean_security, String strWhere) throws Exception {
        ViewDaoInterface oDao = null;

        switch (ob) {
            case "usuario":
                oDao = (ViewDaoInterface) new UsuarioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "tipousuario":
                oDao = (ViewDaoInterface) new TipousuarioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "pedido":
                oDao = (ViewDaoInterface) new PedidoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "producto":
                oDao = (ViewDaoInterface) new ProductoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "linea_pedido":
                oDao = (ViewDaoInterface) new LineadepedidoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            default:
                //oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;
        }
        return oDao;
    }

}
