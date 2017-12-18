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
package eu.rafaelaznar.service.genericimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.dao.publicinterface.MetaDaoInterface;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.helper.GsonHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import eu.rafaelaznar.factory.DaoFactory;
import eu.rafaelaznar.service.publicinterface.MetaServiceInterface;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public abstract class MetaGenericServiceImplementation implements MetaServiceInterface {

    protected HttpServletRequest oRequest = null;
    protected String ob = null;

    public MetaGenericServiceImplementation(HttpServletRequest request) {
        oRequest = request;
        ob = oRequest.getParameter("ob");
    }

    protected Boolean checkPermission(String strMethodName) {
        MetaBeanHelper oUsuarioBean = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ReplyBeanHelper getMetaData() throws Exception {
        ReplyBeanHelper oReplyBean = null;
        if (this.checkPermission("getMetaData")) {
            String data = null;
            try {
                MetaDaoInterface oDao = DaoFactory.getDao(ob, null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                HashMap alMeta = new HashMap();
                alMeta.put("metaObject", oDao.getObjectMetaData());
                alMeta.put("metaProperties", oDao.getPropertiesMetaData());
                oReplyBean = new ReplyBeanHelper(200, GsonHelper.getGson().toJson(alMeta));
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

    @Override
    public ReplyBeanHelper getObjectMetaData() throws Exception {
        ReplyBeanHelper oReplyBean = null;
        if (this.checkPermission("getObjectMetaData")) {
            String data = null;
            try {
                MetaDaoInterface oDao = DaoFactory.getDao(ob, null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                String strJson = GsonHelper.getGson().toJson(oDao.getObjectMetaData());
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

    @Override
    public ReplyBeanHelper getPropertiesMetaData() throws Exception {
        ReplyBeanHelper oReplyBean = null;
        if (this.checkPermission("getPropertiesMetaData")) {
            String data = null;
            try {
                MetaDaoInterface oDao = DaoFactory.getDao(ob, null, (MetaBeanHelper) oRequest.getSession().getAttribute("user"), null);
                String strJson = GsonHelper.getGson().toJson(oDao.getPropertiesMetaData());
                oReplyBean = new ReplyBeanHelper(200, strJson);
            } catch (Exception ex) {
                String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
                Log4jHelper.errorLog(msg, ex);
                throw new Exception(msg, ex);
            }
            return oReplyBean;
        } else {
            return new ReplyBeanHelper(401, EncodingHelper.quotate("Unauthorized"));
        }
    }

}
