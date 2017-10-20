/*
 * Copyright (c) 2017 by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com)
 * 
 * sissane-server: Helps you to develop easily AJAX web applications 
 *               by copying and modifying this Java Server.
 *
 * Sources at https://github.com/rafaelaznar/carrito-server
 * 
 * sissane-server is distributed under the MIT License (MIT)
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

import eu.rafaelaznar.bean.TipousuarioBean;
import eu.rafaelaznar.bean.UsuarioBean;
import eu.rafaelaznar.helper.AppConfigurationHelper;
import eu.rafaelaznar.helper.EncodingUtilHelper;
import eu.rafaelaznar.helper.Log4j;
import eu.rafaelaznar.helper.SqlBuilder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDao implements DaoTableInterface<UsuarioBean>, DaoViewInterface<UsuarioBean> {

    private String strTable = "usuario";
    private String strSQL = "select * from " + strTable + " WHERE 1=1 ";
    private Connection oConnection = null;

    public UsuarioDao(Connection oPooledConnection) {
        oConnection = oPooledConnection;
    }

    @Override
    public UsuarioBean get(UsuarioBean oBean, int intExpand) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "select * from " + strTable + " WHERE 1=1 ";
        strSQL += " AND id=" + oBean.getId();
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                oBean.setId(oResultSet.getInt("id"));
                oBean.setDni(oResultSet.getString("dni"));
                oBean.setNombre(oResultSet.getString("nombre"));
                oBean.setPrimer_apellido(oResultSet.getString("primer_apellido"));
                oBean.setSegundo_apellido(oResultSet.getString("segundo_apellido"));
                oBean.setLogin(oResultSet.getString("login"));
                oBean.setPass(oResultSet.getString("pass"));
                oBean.setEmail(oResultSet.getString("email"));
                oBean.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
                if (intExpand > 0) {
                    TipousuarioBean oTipousuario = new TipousuarioBean();
                    TipousuarioDao oTipousuarioDao = new TipousuarioDao(oConnection);
                    oTipousuario.setId(oBean.getId_tipousuario());
                    oTipousuario = oTipousuarioDao.get(oTipousuario, --intExpand);
                    oBean.setObj_tipousuario(oTipousuario);
                }
            } else {
                throw new Exception("UsuarioDao get id not found");
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
    public Integer set(UsuarioBean oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        Integer iResult = 0;
        Boolean insert = true;
        try {
            if (oBean.getId() == null || oBean.getId() <= 0) {
                strSQL = "INSERT INTO " + strTable;
                strSQL += "(";
                strSQL += "dni,";
                strSQL += "nombre,";
                strSQL += "primer_apellido,";
                strSQL += "segundo_apellido,";
                strSQL += "login,";
                strSQL += "pass,";
                strSQL += "email,";
                strSQL += "id_tipousuario";
                strSQL += ")";
                strSQL += " VALUES ";
                strSQL += "(";
                strSQL += EncodingUtilHelper.quotate(oBean.getDni()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getNombre()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getPrimer_apellido()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getSegundo_apellido()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getLogin()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getPass()) + ",";
                strSQL += EncodingUtilHelper.quotate(oBean.getEmail()) + ",";
                strSQL += oBean.getId_tipousuario();
                strSQL += ")";
            } else {
                insert = false;
                strSQL = "UPDATE " + strTable;
                strSQL += " SET ";
                strSQL += "dni=" + EncodingUtilHelper.quotate(oBean.getDni()) + ", ";
                strSQL += "nombre=" + EncodingUtilHelper.quotate(oBean.getNombre()) + ",";
                strSQL += "primer_apellido=" + EncodingUtilHelper.quotate(oBean.getPrimer_apellido()) + ",";
                strSQL += "segundo_apellido=" + EncodingUtilHelper.quotate(oBean.getSegundo_apellido()) + ",";
                strSQL += "login=" + EncodingUtilHelper.quotate(oBean.getLogin()) + ",";
                strSQL += "pass=" + EncodingUtilHelper.quotate(oBean.getPass()) + ",";
                strSQL += "email=" + oBean.getEmail() + ",";
                strSQL += "id_tipousuario=" + oBean.getId_tipousuario() + " ";
                strSQL += "WHERE id=" + oBean.getId();
            }
            oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
            iResult = oPreparedStatement.executeUpdate();
            if (iResult < 1) {
                throw new Exception("UsuarioDao set error");
            }
            if (insert) {
                ResultSet oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                iResult = oResultSet.getInt(1);
            }
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

    @Override
    public Long getCount() throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "SELECT COUNTa(*) FROM " + strTable;
        Long iResult = 0L;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery(strSQL);
            if (oResultSet.next()) {
                iResult = oResultSet.getLong("COUNT(*)");
            } else {
                throw new Exception("UsuarioDao getCount error");
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
        return iResult;
    }

    @Override
    public ArrayList<UsuarioBean> getPage(int intRegsPerPag, int intPage) throws Exception {
        String strSQL1 = strSQL + SqlBuilder.buildSqlLimit(this.getCount(), intRegsPerPag, intPage);
        ArrayList<UsuarioBean> aloBean = new ArrayList<>();
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL1);
            oResultSet = oPreparedStatement.executeQuery(strSQL1);
            while (oResultSet.next()) {
                aloBean.add(this.get(new UsuarioBean(oResultSet.getInt("id")), AppConfigurationHelper.getJsonMsgDepth()));
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
        return aloBean;
    }

    public UsuarioBean getFromLoginAndPass(UsuarioBean oUsuarioBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        strSQL = "select * from " + strTable + " WHERE 1=1 ";
        strSQL += " AND login='" + oUsuarioBean.getLogin() + "'";
        strSQL += " AND pass='" + oUsuarioBean.getPass() + "'";
        try {
            oPreparedStatement = oConnection.prepareStatement(strSQL);
            oResultSet = oPreparedStatement.executeQuery();
            if (oResultSet.next()) {
                oUsuarioBean.setId(oResultSet.getInt("id"));
                oUsuarioBean.setDni(oResultSet.getString("dni"));
                oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                oUsuarioBean.setPrimer_apellido(oResultSet.getString("primer_apellido"));
                oUsuarioBean.setSegundo_apellido(oResultSet.getString("segundo_apellido"));
                oUsuarioBean.setLogin(oResultSet.getString("login"));
                oUsuarioBean.setPass(oResultSet.getString("pass"));
                oUsuarioBean.setEmail(oResultSet.getString("email"));
                oUsuarioBean.setId_tipousuario(oResultSet.getInt("id_tipousuario"));
                //pendiente la expansión ************************* %%%%%%
            } else {
                throw new Exception("UsuarioDao getFromLoginAndPass error");
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
        return oUsuarioBean;

    }

}
