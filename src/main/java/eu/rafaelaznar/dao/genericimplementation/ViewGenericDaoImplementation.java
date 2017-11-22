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
package eu.rafaelaznar.dao.genericimplementation;

import eu.rafaelaznar.bean.genericimplementation.ViewGenericBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.bean.helper.FilterBeanHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import eu.rafaelaznar.factory.BeanFactory;
import eu.rafaelaznar.helper.SqlHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import eu.rafaelaznar.bean.publicinterface.GenericBeanInterface;
import eu.rafaelaznar.dao.publicinterface.ViewDaoInterface;

public abstract class ViewGenericDaoImplementation extends MetaGenericDaoImplementation implements ViewDaoInterface<ViewGenericBeanImplementation> {

    public ViewGenericDaoImplementation(String ob, Connection oPooledConnection, UsuarioSpecificBeanImplementation oPuserBean_security, String strWhere) {
        super(ob, oPooledConnection, oPuserBean_security, strWhere);
    }

    @Override
    public Long getCount(ArrayList<FilterBeanHelper> alFilter) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strCountSQL += SqlHelper.buildSqlFilter(alFilter);
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strCountSQL);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
            } else {
                String msg = this.getClass().getName() + ": getcount";
                Log4jHelper.errorLog(msg);
                throw new Exception(msg);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    @Override
    public ArrayList<ViewGenericBeanImplementation> getPage(int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter, int expand) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += SqlHelper.buildSqlFilter(alFilter);
        strSQL1 += SqlHelper.buildSqlOrder(hmOrder);
        strSQL1 += SqlHelper.buildSqlLimit(this.getCount(alFilter), intRegsPerPag, intPage);
        ArrayList<ViewGenericBeanImplementation> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                GenericBeanInterface oBean = BeanFactory.getBean(ob);
                oBean = (ViewGenericBeanImplementation) oBean.fill(oResultSet, oConnection, oPuserSecurity, expand);
                aloBean.add((ViewGenericBeanImplementation) oBean);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return aloBean;
    }

    @Override
    public ArrayList<ViewGenericBeanImplementation> getPageX(int id_foreign, String ob_foreign, int intRegsPerPag, int intPage, LinkedHashMap<String, String> hmOrder, ArrayList<FilterBeanHelper> alFilter, int expand) throws Exception {
        String strSQL1 = strSQL;
        strSQL1 += " and id_" + ob_foreign + "=" + id_foreign + " ";
        strSQL1 += SqlHelper.buildSqlFilter(alFilter);
        strSQL1 += SqlHelper.buildSqlOrder(hmOrder);
        strSQL1 += SqlHelper.buildSqlLimit(getCount(alFilter), intRegsPerPag, intPage);
        ArrayList<ViewGenericBeanImplementation> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                GenericBeanInterface oBean = BeanFactory.getBean(ob);
                oBean = (ViewGenericBeanImplementation) oBean.fill(oResultSet, oConnection, oPuserSecurity, expand);
                aloBean.add((ViewGenericBeanImplementation) oBean);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return aloBean;
    }

    @Override
    public Long getCountX(int id_foreign, String ob_foreign, ArrayList<FilterBeanHelper> alFilter) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT COUNT(*) FROM " + ob;
        strSQL += " WHERE 1=1 ";
        strSQL += " and id_" + ob_foreign + "=" + id_foreign + " ";
        strSQL += SqlHelper.buildSqlFilter(alFilter);
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
            } else {
                String msg = this.getClass().getName() + ": getCountxtipousuario";
                Log4jHelper.errorLog(msg);
                throw new Exception(msg);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

}
