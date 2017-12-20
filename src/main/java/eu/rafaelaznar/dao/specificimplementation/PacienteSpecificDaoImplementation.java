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
package eu.rafaelaznar.dao.specificimplementation;

import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.CentrosanitarioSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.PacienteSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.dao.genericimplementation.TableGenericDaoImplementation;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.helper.Log4jHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PacienteSpecificDaoImplementation extends TableGenericDaoImplementation {

    private Integer idUsuario;

    public PacienteSpecificDaoImplementation(Connection oPooledConnection, MetaBeanHelper oPuserBean_security, String strWhere) throws Exception {
        super("paciente", oPooledConnection, oPuserBean_security, strWhere);

        UsuarioSpecificBeanImplementation oUsuario = (UsuarioSpecificBeanImplementation) oPuserBean_security.getBean();
        MetaBeanHelper oMetaBeanHelper = oUsuario.getObj_centrosanitario();
        CentrosanitarioSpecificBeanImplementation oCentrosanitario = (CentrosanitarioSpecificBeanImplementation) oMetaBeanHelper.getBean();
        Integer idCentrosanitario = oCentrosanitario.getId();
        Integer idUsuario = oUsuario.getId();
        //SQL peta
        strSQL = "SELECT * FROM paciente p, usuario u WHERE p.id_usuario = u.id AND u.id_centrosanitario = " + idCentrosanitario;
    }

    @Override
    public Integer set(TableGenericBeanImplementation oBean) throws Exception {
        PreparedStatement oPreparedStatement = null;
        ResultSet oResultSet = null;
        Integer iResult = 0;
        Boolean insert = true;
        PacienteSpecificBeanImplementation oPaciente = (PacienteSpecificBeanImplementation) oBean;
        try {
            if (oBean.getId() == null || oBean.getId() == 0) {
                strSQL = "INSERT INTO " + ob;
                strSQL += "(" + oBean.getColumns() + ")";
                strSQL += " VALUES ";
                strSQL += "(" + EncodingHelper.quotate(oPaciente.getDni()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getNombre()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getPrimer_apellido()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getSegundo_apellido()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getDireccion()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getCiudad()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getCodigo_postal()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getProvincia()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getPais()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getEmail()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getTelefono1()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getTelefono2()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getNombre_padre()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getNombre_madre()) + ",";
                strSQL += EncodingHelper.stringifyDate(oPaciente.getFecha_nacimiento()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getCiudad_nacimiento()) + ",";
                strSQL += EncodingHelper.quotate(oPaciente.getPais_nacimiento()) + ",";
                strSQL += oPaciente.getSip_aseguradora() + ",";
                strSQL += oPaciente.getId_tipopago() + ",";
                strSQL += oPaciente.getId_sexo() + ",";
                strSQL += idUsuario + ")";

                oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
                iResult = oPreparedStatement.executeUpdate();

                // modificar campo de usuario | añadir variable idUsuario (id_usuario=idUsuario);
            } else {

                insert = false;
                strSQL = "UPDATE " + ob;
                strSQL += " SET ";
                strSQL += oBean.toPairs();

                strSQL += "SELECT COUNT() FROM " + ob + " p, usuario u, grupo g "
                        + "WHERE g.id_usuario = ? AND u.id_grupo = g.id AND "
                        + "u.id = p.id_usuario AND p.id = ?";
//                () from usuario u,paciente p, grupo g where g.id_usuario =  ? (IDPROFESORENSESION) and  u.id_grupo = g.id and u
//                .id = p.id_usuario and p.id =  ? (IDPACIENTEAMODIFICAR);

                oPreparedStatement = oConnection.prepareStatement(strSQL, Statement.RETURN_GENERATED_KEYS);
                oPreparedStatement.setInt(1, idUsuario);
                oPreparedStatement.setInt(2, oBean.getId());
                iResult = oPreparedStatement.executeUpdate();
            }
            if (iResult < 1) {
                String msg = this.getClass().getName() + ": set";
                Log4jHelper.errorLog(msg);
                throw new Exception(msg);
            }
            if (insert) {
                oResultSet = oPreparedStatement.getGeneratedKeys();
                oResultSet.next();
                iResult = oResultSet.getInt(1);
            }
        } catch (Exception ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
            Log4jHelper.errorLog(msg, ex);
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
}
