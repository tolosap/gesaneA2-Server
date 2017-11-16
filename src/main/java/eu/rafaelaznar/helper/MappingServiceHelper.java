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

import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.service.specificimplementation.LineadepedidoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.PedidoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.ProductoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.TipousuarioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.UsuarioSpecificServiceImplementation;
import javax.servlet.http.HttpServletRequest;

public class MappingServiceHelper {

    public static ReplyBean executeMethodService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;
        switch (ob) {
            case "usuario":
                UsuarioSpecificServiceImplementation oUsuarioService = new UsuarioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oUsuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oUsuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oUsuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oUsuarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oUsuarioService.getCount();
                        break;
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "getsessionstatus":
                        oReplyBean = oUsuarioService.getSessionStatus();
                        break;
                    case "getcountx":
                        oReplyBean = oUsuarioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oUsuarioService.getPageX();
                        break;
                    case "setpass":
                        oReplyBean = oUsuarioService.setPass();
                        break;
                    case "getsessionuserlevel":
                        oReplyBean = oUsuarioService.getSessionUserLevel();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "tipousuario":
                TipousuarioSpecificServiceImplementation oTipousuarioService = new TipousuarioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oTipousuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oTipousuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipousuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipousuarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipousuarioService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "pedido":
                PedidoSpecificServiceImplementation oPedidoService = new PedidoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oPedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oPedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oPedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oPedidoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oPedidoService.getCount();
                        break;

                    case "getcountx":
                        oReplyBean = oPedidoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oPedidoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "producto":
                ProductoSpecificServiceImplementation oProductoService = new ProductoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oProductoService.get();
                        break;
                    case "set":
                        oReplyBean = oProductoService.set();
                        break;
                    case "remove":
                        oReplyBean = oProductoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oProductoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oProductoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oProductoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oProductoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "linea_pedido":
                LineadepedidoSpecificServiceImplementation oLineadepedidoService = new LineadepedidoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "get":
                        oReplyBean = oLineadepedidoService.get();
                        break;
                    case "set":
                        oReplyBean = oLineadepedidoService.set();
                        break;
                    case "remove":
                        oReplyBean = oLineadepedidoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oLineadepedidoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oLineadepedidoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oLineadepedidoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oLineadepedidoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            default:
                oReplyBean = new ReplyBean(500, EncodingUtilHelper.quotate("Object not found : Please contact your administrator"));
                break;
        }
        return oReplyBean;
    }
}
