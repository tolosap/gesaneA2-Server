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

package eu.rafaelaznar.helper;

import eu.rafaelaznar.bean.ReplyBean;
import eu.rafaelaznar.service.TipousuarioService;
import eu.rafaelaznar.service.UsuarioService;
import javax.servlet.http.HttpServletRequest;

public class MappingServiceHelper {

    public static ReplyBean executeMethodService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBean oReplyBean = null;
        switch (ob) {
            case "usuario":
                UsuarioService oUsuarioService = new UsuarioService(oRequest, ob);
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
                        oReplyBean = oUsuarioService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oUsuarioService.getcount();
                        break;
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "check":
                        oReplyBean = oUsuarioService.check();
                        break;
                    case "getcountx":
                        oReplyBean = oUsuarioService.getcountx();
                        break;
                    case "getpagex":
                        oReplyBean = oUsuarioService.getpagex();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            case "tipousuario":
                TipousuarioService oTipousuarioService = new TipousuarioService(oRequest, ob);
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
                        oReplyBean = oTipousuarioService.getpage();
                        break;
                    case "getcount":
                        oReplyBean = oTipousuarioService.getcount();
                        break;
                    default:
                        oReplyBean = new ReplyBean(500, "Operation not found : Please contact your administrator");
                        break;
                }
                break;
            default:
                oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;
        }
        return oReplyBean;
    }
}
