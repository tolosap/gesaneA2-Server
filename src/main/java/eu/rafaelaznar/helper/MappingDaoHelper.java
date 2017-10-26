/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.helper;

import eu.rafaelaznar.dao.DaoViewInterface;
import eu.rafaelaznar.dao.TipousuarioDao;
import eu.rafaelaznar.dao.UsuarioDao;
import java.sql.Connection;

public class MappingDaoHelper {

    public static DaoViewInterface getDao(String ob, Connection oConnection) throws Exception {
        DaoViewInterface oDao = null;

        switch (ob) {
            case "usuario":
                oDao = new UsuarioDao(oConnection);
                break;
            case "tipousuario":
                oDao = new TipousuarioDao(oConnection);
                break;
            default:
                //oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;
        }
        return oDao;
    }

    
}
