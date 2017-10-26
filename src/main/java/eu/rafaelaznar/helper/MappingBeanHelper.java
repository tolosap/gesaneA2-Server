/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.helper;

import eu.rafaelaznar.bean.GenericBeanInterface;
import eu.rafaelaznar.bean.TipousuarioBean;
import eu.rafaelaznar.bean.UsuarioBean;

/**
 *
 * @author raznara
 */
public class MappingBeanHelper {

    public static GenericBeanInterface getBean(String ob) {
        GenericBeanInterface oBean = null;

        switch (ob) {
            case "usuario":
                oBean = new UsuarioBean();
                break;
            case "tipousuario":
                oBean = new TipousuarioBean();
                break;
            default:

                //oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;
        }
        return oBean;
    }
}
