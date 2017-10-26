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
package eu.rafaelaznar.dao;

import eu.rafaelaznar.bean.GenericBeanInterface;
import eu.rafaelaznar.bean.GenericTableBean;
import eu.rafaelaznar.bean.UsuarioBean;
import eu.rafaelaznar.helper.Log4j;
import eu.rafaelaznar.helper.MappingBeanHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class GenericTableDao implements DaoTableInterface<GenericTableBean> {

    private String strTable = null;
    private String strSQL = null;

    private String strCountSQL = null;
    private Connection oConnection = null;
    private UsuarioBean oPuserSecurity = null;

    public GenericTableDao(String ob, Connection oPooledConnection, UsuarioBean oPuserBean_security, String strWhere) {
        oConnection = oPooledConnection;
        oPuserSecurity = oPuserBean_security;
        if (strWhere != null) {
            strSQL += strWhere;
            strCountSQL += strWhere;
        }
        strTable = ob;
        strSQL = "select * from " + strTable + " WHERE 1=1 ";
        strCountSQL = "select * from " + strTable + " WHERE 1=1 ";
    }

    @Override
    public GenericTableBean get(int id, int intExpand) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL += " AND id=" + id;
        GenericTableBean oBean = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                oBean = (GenericTableBean) MappingBeanHelper.getBean(strTable);
                oBean = (GenericTableBean) oBean.fill(oResultSet, oConnection, oPuserSecurity, intExpand);

            } else {
                oBean = null;
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4j.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }

        }
        return oBean;
    }

    @Override
    public Integer set(GenericTableBean oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        Integer iResult = 0;
        Boolean insert = true;
        try {
            if (oBean.getId() == null || oBean.getId() == 0) {
                strSQL = "INSERT INTO " + strTable;
                strSQL += "(" + oBean.getColumns() + ")";
                strSQL += " VALUES ";
                strSQL += "(" + oBean.getValues() + ")";
            } else {
                insert = false;
                strSQL = "UPDATE " + strTable;
                strSQL += " SET ";
                strSQL += oBean.toPairs();
                strSQL += "WHERE id=" + oBean.getId();
            }
            oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            iResult = oPreparedStatement.executeUpdate();
            if (iResult < 1) {
                String msg = this.getClass().getName() + ": set";
                Log4j.errorLog(msg);
                throw new Exception(msg);
            }
            if (insert) {
                ResultSet oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                iResult = oResultSet.getInt(1);
            }

        } catch (Exception ex) {
            throw new Exception();
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        boolean iResult = false;
        strSQL = "DELETE FROM " + strTable + " WHERE id=?";
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iResult = oPreparedStatement.execute();
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4j.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

}
