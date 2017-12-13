/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.service.specificimplementation;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.specificimplementation.UsuarioSpecificBeanImplementation;
import eu.rafaelaznar.service.genericimplementation.TableGenericServiceImplementation;
import javax.servlet.http.HttpServletRequest;

public class DestinoaltaSpecificServiceImplementation extends TableGenericServiceImplementation {

    public DestinoaltaSpecificServiceImplementation(HttpServletRequest request) {
        super(request);
    }

    @Override
    protected Boolean checkPermission(String strMethodName) {
        strMethodName.toLowerCase();
        MetaBeanHelper oUsuarioBean = (MetaBeanHelper) oRequest.getSession().getAttribute("user");
        UsuarioSpecificBeanImplementation oUser = (UsuarioSpecificBeanImplementation) oUsuarioBean.getBean();
        if (oUsuarioBean != null && oUser.getId_tipousuario() == 1) {
            return true;
        } else if (oUsuarioBean != null && oUser.getId_tipousuario() >= 3 && oUser.getId_tipousuario() <= 5) {
            switch (strMethodName) {
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
                case "getpagex":
                    return true;
                case "getcountx":
                    return true;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }
}
