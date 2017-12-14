/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.service.specificimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;

import eu.rafaelaznar.bean.specificimplementation.TipousuarioSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;

import eu.rafaelaznar.service.genericimplementation.TableGenericServiceImplementation;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author a022583952e
 */
public class CircunstanciasaltaSpecificServiceImplementation extends TableGenericServiceImplementation {

    public CircunstanciasaltaSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

      @Override
     protected Boolean checkPermission(String strMethodName) {
        MetaBeanHelper oUsuarioBean = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
        if (oUsuarioBean != null) {
            UsuarioSpecificBeanImplementation oUsuario = (UsuarioSpecificBeanImplementation) oUsuarioBean.getBean();
            MetaBeanHelper oMetaBeanHelper = oUsuario.getObj_tipousuario();
            TipousuarioSpecificBeanImplementation oTipousuario = (TipousuarioSpecificBeanImplementation) oMetaBeanHelper.getBean();
            Integer idTipousuario = oTipousuario.getId();

            String strMethod = strMethodName.toLowerCase();
            if (idTipousuario == 1) {
                return true;
            } else {
                if (idTipousuario == 3
                        || idTipousuario == 4
                        || idTipousuario == 5) {

                    switch (strMethod) {
                        case "getmetadata":
                            return true;
                        case "getobjectmetadata":
                            return true;
                        case "getpropertiesmetadata":
                            return true;
                        case "get":
                            return true;
                        case "set":
                            return false;
                        case "remove":
                            return false;
                        case "getpage":
                            return true;
                        case "getcount":
                            return true;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return false;
    }
}
