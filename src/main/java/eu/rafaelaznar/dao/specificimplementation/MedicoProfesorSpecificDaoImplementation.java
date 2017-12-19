/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.dao.specificimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.dao.genericimplementation.TableGenericDaoImplementation;
import eu.rafaelaznar.helper.Log4jHelper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author a022583952e
 */
public class MedicoProfesorSpecificDaoImplementation  {
       

    public MedicoProfesorSpecificDaoImplementation() {
        
    }

    
    
     public MetaBeanHelper getIDfromCentroMedico(int intCode) throws Exception {        
        Statement oStatement = null;
        ResultSet oResultSet = null;
        MetaBeanHelper oMetaBeanHelper = null;
        Connection oConnection = null;
        try {
            oStatement = (Statement) oConnection.createStatement();
            String strSQL = "SELECT id FROM medico WHERE id_centrosanitario =" + intCode;
            oMetaBeanHelper = (MetaBeanHelper) oStatement.executeQuery(strSQL);
            //oMetaBeanHelper = (MetaBeanHelper) oResultSet;           
        } catch (SQLException ex) {
            String msg = this.getClass().getName() + ":" + (ex.getStackTrace()[0]).getMethodName() + " ob:" + ob;
            Log4jHelper.errorLog(msg, ex);
            throw new Exception(msg, ex);
        } finally {
            if (oResultSet != null) {
                oResultSet.close();
            }
            if (oStatement != null) {
                oStatement.close();
            }
        }
        return oMetaBeanHelper;
    }
    
}
