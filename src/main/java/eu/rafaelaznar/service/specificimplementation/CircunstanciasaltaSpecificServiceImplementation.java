/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.service.specificimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.TipousuarioSpecificBeanImplementation;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.service.genericimplementation.MetaGenericServiceImplementation;
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
        String strMethod = strMethodName.toLowerCase();
        MetaBeanHelper oUsuarioBean = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
        UsuarioSpecificBeanImplementation oUser = (UsuarioSpecificBeanImplementation) oUsuarioBean.getBean();
        if (oUsuarioBean != null && oUser.getId_tipousuario() == 1) {
            return true;
        } else if (oUsuarioBean != null && oUser.getId_tipousuario() == 3 || oUser.getId_tipousuario() == 4 || oUser.getId_tipousuario() == 5) {
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
                case "getcountx":
                    return true;
                case "getpagex":
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
