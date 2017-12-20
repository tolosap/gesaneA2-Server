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
package eu.rafaelaznar.factory;

import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.dao.publicinterface.MetaDaoInterface;
import eu.rafaelaznar.dao.specificimplementation.CategoriaprofesionalSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.CentroSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.CentrosanitarioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.TipoepisodioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.CircunstanciasaltaSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.CursoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.EspecialidadSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.DestinoaltaSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.FacturaSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.GrupoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.ModalidadepisodioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.PacienteSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.ServicioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.TipopagoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.SexoSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.TipodependenciaSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.TiposervicioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.TipousuarioSpecificDaoImplementation;
import eu.rafaelaznar.dao.specificimplementation.UsuarioSpecificDaoImplementation;
import java.sql.Connection;

public class DaoFactory {

    public static MetaDaoInterface getDao(String ob, Connection oConnection, MetaBeanHelper oPuserBean_security, String strWhere) throws Exception {
        MetaDaoInterface oDao = null;

        switch (ob) {
            case "usuario":
                oDao = (MetaDaoInterface) new UsuarioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "tipousuario":
                oDao = (MetaDaoInterface) new TipousuarioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "grupo":
                oDao = (MetaDaoInterface) new GrupoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "curso":
                oDao = (MetaDaoInterface) new CursoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "centrosanitario":
                oDao = (MetaDaoInterface) new CentrosanitarioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "centro":
                oDao = (MetaDaoInterface) new CentroSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "especialidad":
                oDao = (MetaDaoInterface) new EspecialidadSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "destinoalta":
                oDao = (MetaDaoInterface) new DestinoaltaSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "tipopago":
                oDao = (MetaDaoInterface) new TipopagoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "circunstanciasalta":
                oDao = (MetaDaoInterface) new CircunstanciasaltaSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "sexo":
                oDao = (MetaDaoInterface) new SexoSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "tipoepisodio":
                oDao = (MetaDaoInterface) new TipoepisodioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "tiposervicio":
                oDao = (MetaDaoInterface) new TiposervicioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "modalidadepisodio":
                oDao = (MetaDaoInterface) new ModalidadepisodioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "tipodependencia":
                oDao = (MetaDaoInterface) new TipodependenciaSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "factura":
                oDao = (MetaDaoInterface) new FacturaSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "servicio":
                oDao = (MetaDaoInterface) new ServicioSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "paciente":
                oDao = (MetaDaoInterface) new PacienteSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            case "categoriaprofesional":
                oDao = (MetaDaoInterface) new CategoriaprofesionalSpecificDaoImplementation(oConnection, oPuserBean_security, strWhere);
                break;
            default:
                //oReplyBean = new ReplyBean(500, "Object not found : Please contact your administrator");
                break;
        }
        return oDao;
    }

}
