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

import eu.rafaelaznar.bean.helper.ReplyBeanHelper;
import eu.rafaelaznar.helper.EncodingHelper;
import eu.rafaelaznar.service.specificimplementation.CategoriaprofesionalSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.CentroSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.CentrosanitarioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.TipoepisodioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.CircunstanciasaltaSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.CursoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.DependenciaSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.EspecialidadSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.DestinoaltaSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.EpisodioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.FacturaSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.GrupoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.ModalidadepisodioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.PacienteSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.ServicioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.TipopagoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.SexoSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.TipodependenciaSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.TiposervicioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.TipousuarioSpecificServiceImplementation;
import eu.rafaelaznar.service.specificimplementation.UsuarioSpecificServiceImplementation;
import javax.servlet.http.HttpServletRequest;

public class ServiceFactory {

    public static ReplyBeanHelper executeMethodService(HttpServletRequest oRequest) throws Exception {
        String ob = oRequest.getParameter("ob");
        String op = oRequest.getParameter("op");
        ReplyBeanHelper oReplyBean = null;
        switch (ob) {
            case "usuario":
                UsuarioSpecificServiceImplementation oUsuarioService = new UsuarioSpecificServiceImplementation(oRequest);
                switch (op) {

                    case "getallobjectsmetadata":
                        oReplyBean = oUsuarioService.getallobjectsmetadata();
                        break;
                    case "getmetadata":
                        oReplyBean = oUsuarioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oUsuarioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oUsuarioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oUsuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oUsuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oUsuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oUsuarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oUsuarioService.getCount();
                        break;
                    case "login":
                        oReplyBean = oUsuarioService.login();
                        break;
                    case "logout":
                        oReplyBean = oUsuarioService.logout();
                        break;
                    case "getsessionstatus":
                        oReplyBean = oUsuarioService.getSessionStatus();
                        break;
                    case "getcountx":
                        oReplyBean = oUsuarioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oUsuarioService.getPageX();
                        break;
                    case "setpass":
                        oReplyBean = oUsuarioService.setPass();
                        break;
                    case "getsessionuserlevel":
                        oReplyBean = oUsuarioService.getSessionUserLevel();
                        break;
                    case "checklogin":
                        oReplyBean = oUsuarioService.checklogin();
                        break;
                    case "setalumno":
                        oReplyBean = oUsuarioService.setalumno();
                        break;
                    case "getidcurso":
                        oReplyBean = oUsuarioService.getidcurso();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "tipousuario":
                TipousuarioSpecificServiceImplementation oTipousuarioService = new TipousuarioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTipousuarioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTipousuarioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTipousuarioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTipousuarioService.get();
                        break;
                    case "set":
                        oReplyBean = oTipousuarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipousuarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipousuarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipousuarioService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "grupo":
                GrupoSpecificServiceImplementation oGrupoService = new GrupoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "check":
                        oReplyBean = oGrupoService.check();
                        break;
                    case "getmetadata":
                        oReplyBean = oGrupoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oGrupoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oGrupoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oGrupoService.get();
                        break;
                    case "set":
                        oReplyBean = oGrupoService.set();
                        break;
                    case "remove":
                        oReplyBean = oGrupoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oGrupoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oGrupoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oGrupoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oGrupoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "curso":
                CursoSpecificServiceImplementation oCursoService = new CursoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oCursoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oCursoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oCursoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oCursoService.get();
                        break;
                    case "set":
                        oReplyBean = oCursoService.set();
                        break;
                    case "remove":
                        oReplyBean = oCursoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oCursoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oCursoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oCursoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oCursoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "centrosanitario":
                CentrosanitarioSpecificServiceImplementation oCentrosanitarioService = new CentrosanitarioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oCentrosanitarioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oCentrosanitarioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oCentrosanitarioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oCentrosanitarioService.get();
                        break;
                    case "set":
                        oReplyBean = oCentrosanitarioService.set();
                        break;
                    case "remove":
                        oReplyBean = oCentrosanitarioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oCentrosanitarioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oCentrosanitarioService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oCentrosanitarioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oCentrosanitarioService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "centro":
                CentroSpecificServiceImplementation oCentroService = new CentroSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oCentroService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oCentroService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oCentroService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oCentroService.get();
                        break;
                    case "set":
                        oReplyBean = oCentroService.set();
                        break;
                    case "remove":
                        oReplyBean = oCentroService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oCentroService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oCentroService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oCentroService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oCentroService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "especialidad":
                EspecialidadSpecificServiceImplementation oEspecialidadService = new EspecialidadSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oEspecialidadService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oEspecialidadService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oEspecialidadService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oEspecialidadService.get();
                        break;
                    case "set":
                        oReplyBean = oEspecialidadService.set();
                        break;
                    case "remove":
                        oReplyBean = oEspecialidadService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oEspecialidadService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oEspecialidadService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "destinoalta":
                DestinoaltaSpecificServiceImplementation oDestinoaltaService = new DestinoaltaSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oDestinoaltaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oDestinoaltaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oDestinoaltaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oDestinoaltaService.get();
                        break;
                    case "set":
                        oReplyBean = oDestinoaltaService.set();
                        break;
                    case "remove":
                        oReplyBean = oDestinoaltaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oDestinoaltaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oDestinoaltaService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oDestinoaltaService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oDestinoaltaService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "tipopago":
                TipopagoSpecificServiceImplementation oTipopagoService = new TipopagoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTipopagoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTipopagoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTipopagoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTipopagoService.get();
                        break;
                    case "set":
                        oReplyBean = oTipopagoService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipopagoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipopagoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipopagoService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "circunstanciasalta":
                CircunstanciasaltaSpecificServiceImplementation oCircunstanciasaltaService = new CircunstanciasaltaSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oCircunstanciasaltaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oCircunstanciasaltaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oCircunstanciasaltaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oCircunstanciasaltaService.get();
                        break;
                    case "set":
                        oReplyBean = oCircunstanciasaltaService.set();
                        break;
                    case "remove":
                        oReplyBean = oCircunstanciasaltaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oCircunstanciasaltaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oCircunstanciasaltaService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "sexo":
                SexoSpecificServiceImplementation oSexoService = new SexoSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oSexoService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oSexoService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oSexoService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oSexoService.get();
                        break;
                    case "set":
                        oReplyBean = oSexoService.set();
                        break;
                    case "remove":
                        oReplyBean = oSexoService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oSexoService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oSexoService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oSexoService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oSexoService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "tipoepisodio":
                TipoepisodioSpecificServiceImplementation oTipoepisodioService = new TipoepisodioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTipoepisodioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTipoepisodioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTipoepisodioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTipoepisodioService.get();
                        break;
                    case "set":
                        oReplyBean = oTipoepisodioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipoepisodioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipoepisodioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipoepisodioService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "tiposervicio":
                TiposervicioSpecificServiceImplementation oTiposervicioService = new TiposervicioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTiposervicioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTiposervicioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTiposervicioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTiposervicioService.get();
                        break;
                    case "set":
                        oReplyBean = oTiposervicioService.set();
                        break;
                    case "remove":
                        oReplyBean = oTiposervicioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTiposervicioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTiposervicioService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oTiposervicioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oTiposervicioService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "modalidadepisodio":
                ModalidadepisodioSpecificServiceImplementation oModalidadepisodioService = new ModalidadepisodioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oModalidadepisodioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oModalidadepisodioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oModalidadepisodioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oModalidadepisodioService.get();
                        break;
                    case "set":
                        oReplyBean = oModalidadepisodioService.set();
                        break;
                    case "remove":
                        oReplyBean = oModalidadepisodioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oModalidadepisodioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oModalidadepisodioService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "tipodependencia":
                TipodependenciaSpecificServiceImplementation oTipodependenciaService = new TipodependenciaSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oTipodependenciaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oTipodependenciaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oTipodependenciaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oTipodependenciaService.get();
                        break;
                    case "set":
                        oReplyBean = oTipodependenciaService.set();
                        break;
                    case "remove":
                        oReplyBean = oTipodependenciaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oTipodependenciaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oTipodependenciaService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "servicio":
                ServicioSpecificServiceImplementation oServicioService = new ServicioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oServicioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oServicioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oServicioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oServicioService.get();
                        break;
                    case "set":
                        oReplyBean = oServicioService.set();
                        break;
                    case "remove":
                        oReplyBean = oServicioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oServicioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oServicioService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
            case "factura":
                FacturaSpecificServiceImplementation oFacturaService = new FacturaSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oFacturaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oFacturaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oFacturaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oFacturaService.get();
                        break;
                    case "set":
                        oReplyBean = oFacturaService.set();
                        break;
                    case "remove":
                        oReplyBean = oFacturaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oFacturaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oFacturaService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oFacturaService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oFacturaService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "paciente":
                PacienteSpecificServiceImplementation oPacienteService = new PacienteSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oPacienteService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oPacienteService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oPacienteService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oPacienteService.get();
                        break;
                    case "set":
                        oReplyBean = oPacienteService.set();
                        break;
                    case "remove":
                        oReplyBean = oPacienteService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oPacienteService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oPacienteService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oPacienteService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oPacienteService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            case "categoriaprofesional":
                CategoriaprofesionalSpecificServiceImplementation oCategoriaService = new CategoriaprofesionalSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oCategoriaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oCategoriaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oCategoriaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oCategoriaService.get();
                        break;
                    case "set":
                        oReplyBean = oCategoriaService.set();
                        break;
                    case "remove":
                        oReplyBean = oCategoriaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oCategoriaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oCategoriaService.getCount();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
                
                case "episodio":
                EpisodioSpecificServiceImplementation oEpisodioService = new EpisodioSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oEpisodioService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oEpisodioService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oEpisodioService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oEpisodioService.get();
                        break;
                    case "set":
                        oReplyBean = oEpisodioService.set();
                        break;
                    case "remove":
                        oReplyBean = oEpisodioService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oEpisodioService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oEpisodioService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oEpisodioService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oEpisodioService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;
                
                case "dependencia":
                DependenciaSpecificServiceImplementation oDependenciaService = new DependenciaSpecificServiceImplementation(oRequest);
                switch (op) {
                    case "getmetadata":
                        oReplyBean = oDependenciaService.getMetaData();
                        break;
                    case "getobjectmetadata":
                        oReplyBean = oDependenciaService.getObjectMetaData();
                        break;
                    case "getpropertiesmetadata":
                        oReplyBean = oDependenciaService.getPropertiesMetaData();
                        break;
                    case "get":
                        oReplyBean = oDependenciaService.get();
                        break;
                    case "set":
                        oReplyBean = oDependenciaService.set();
                        break;
                    case "remove":
                        oReplyBean = oDependenciaService.remove();
                        break;
                    case "getpage":
                        oReplyBean = oDependenciaService.getPage();
                        break;
                    case "getcount":
                        oReplyBean = oDependenciaService.getCount();
                        break;
                    case "getcountx":
                        oReplyBean = oDependenciaService.getCountX();
                        break;
                    case "getpagex":
                        oReplyBean = oDependenciaService.getPageX();
                        break;
                    default:
                        oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Operation not found : Please contact your administrator"));
                        break;
                }
                break;

            default:
                oReplyBean = new ReplyBeanHelper(500, EncodingHelper.quotate("Object not found : Please contact your administrator"));
                break;
        }
        return oReplyBean ;
    }
   }
