/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.dao.specificimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.dao.genericimplementation.TableGenericDaoImplementation;
import java.sql.Connection;

/**
 *
 * @author a0y3770325h
 */
public class TipodependenciaSpecificDaoImplementation extends TableGenericDaoImplementation {

    public TipodependenciaSpecificDaoImplementation(Connection oPooledConnection, MetaBeanHelper oPuserBean_security, String strWhere) throws Exception {
        super("tipodependencia", oPooledConnection, oPuserBean_security, strWhere);
    }
}
