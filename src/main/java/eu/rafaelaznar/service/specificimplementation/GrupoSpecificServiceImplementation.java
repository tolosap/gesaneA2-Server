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

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.GrupoSpecificBeanImplementation;
import eu.rafaelaznar.connection.publicinterface.ConnectionInterface;
import eu.rafaelaznar.dao.specificimplementation.GrupoSpecificDaoImplementation;
import eu.rafaelaznar.factory.ConnectionFactory;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import eu.rafaelaznar.helper.constant.ConnectionConstants;
import eu.rafaelaznar.service.genericimplementation.TableGenericServiceImplementation;
import java.sql.Connection;
import javax.servlet.http.HttpServletRequest;

public class GrupoSpecificServiceImplementation extends TableGenericServiceImplementation {

    public GrupoSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    public ReplyBeanHelper check() throws Exception {
        Connection oConnection = null;
        ConnectionInterface oPooledConnection = null;
        ReplyBeanHelper oReplyBean = null;
        String codigo = oRequest.getParameter("codigo");
        if (!codigo.isEmpty()) {
            try {
                oPooledConnection = ConnectionFactory.getSourceConnection(ConnectionConstants.connectionName);
                oConnection = oPooledConnection.newConnection();
                GrupoSpecificDaoImplementation oGrupoDao = new GrupoSpecificDaoImplementation(oConnection, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                GrupoSpecificBeanImplementation oGrupo = new GrupoSpecificBeanImplementation();
                oGrupo.setCodigo(codigo);
                oGrupo = (GrupoSpecificBeanImplementation) oGrupoDao.getFromCodigo(oGrupo).getBean();
                if (oGrupo.getId() > 0) {
                    oReplyBean = new ReplyBeanHelper(200,  EncodingHelper.quotate("OK"));
                } else {
                    oReplyBean = new ReplyBeanHelper(403,  EncodingHelper.quotate("Bad authentication"));
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

        }
        return oReplyBean;
    }
}
