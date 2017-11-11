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
package eu.rafaelaznar.dao.genericimplementation;

import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.helper.Log4jConfigurationHelper;
import eu.rafaelaznar.helper.MappingBeanHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import eu.rafaelaznar.dao.publicinterface.TableDaoInterface;

public abstract class TableGenericDaoImplementation extends ViewGenericDaoImplementation implements TableDaoInterface<TableGenericBeanImplementation> {

    public TableGenericDaoImplementation(String ob, Connection oPooledConnection, UsuarioSpecificBeanImplementation oPuserBean_security, String strWhere) {
        super(ob, oPooledConnection, oPuserBean_security, strWhere);
    }

    @Override
    public TableGenericBeanImplementation get(int id, int intExpand) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL += " AND id=" + id;
        TableGenericBeanImplementation oBean = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                oBean = (TableGenericBeanImplementation) MappingBeanHelper.getBean(ob);
                oBean = (TableGenericBeanImplementation) oBean.fill(oResultSet, oConnection, oPuserSecurity, intExpand);

            } else {
                oBean = null;
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jConfigurationHelper.errorLog(msg, ex);
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
    public Integer set(TableGenericBeanImplementation oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        Integer iResult = 0;
        Boolean insert = true;
        try {
            if (oBean.getId() == null || oBean.getId() == 0) {
                strSQL = "INSERT INTO " + ob;
                strSQL += "(" + oBean.getColumns() + ")";
                strSQL += " VALUES ";
                strSQL += "(" + oBean.getValues() + ")";
            } else {
                insert = false;
                strSQL = "UPDATE " + ob;
                strSQL += " SET ";
                strSQL += oBean.toPairs();
                strSQL += " WHERE id=" + oBean.getId();
            }
            oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            iResult = oPreparedStatement.executeUpdate();
            if (iResult < 1) {
                String msg = this.getClass().getName() + ": set";
                Log4jConfigurationHelper.errorLog(msg);
                throw new Exception(msg);
            }
            if (insert) {
                oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                iResult = oResultSet.getInt(1);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jConfigurationHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (insert) {
                if (oResultSet != null) {
                    oResultSet.close();
                }
            }
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

    @Override
    public int remove(Integer id) throws Exception {
        int iResult = 0;
        strSQL = "DELETE FROM " + ob + " WHERE id=?";
        PreparedStatement oPreparedStatement = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oPreparedStatement.setInt(1, id);
            iResult = oPreparedStatement.executeUpdate();
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName();
            Log4jConfigurationHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oPreparedStatement != null) {
                oPreparedStatement.close();
            }
        }
        return iResult;
    }

}
