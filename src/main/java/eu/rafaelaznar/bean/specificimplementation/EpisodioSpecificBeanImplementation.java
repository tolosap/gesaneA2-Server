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
package eu.rafaelaznar.bean.specificimplementation;

import com.google.gson.annotations.Expose;
import eu.rafaelaznar.bean.genericimplementation.TableGenericBeanImplementation;
import eu.rafaelaznar.bean.helper.MetaBeanHelper;
import eu.rafaelaznar.bean.meta.publicinterface.MetaObjectBeanInterface;
import eu.rafaelaznar.bean.meta.publicinterface.MetaPropertyBeanInterface;
import eu.rafaelaznar.helper.EnumHelper;
import eu.rafaelaznar.helper.constant.RegexConstants;
import java.util.Date;

/**
 *
 * @author a022583952e
 */
@MetaObjectBeanInterface(
        TableName = "episodio",
        SingularDescription = "Episodio",
        PluralDescription = "Episodios",
        Icon = "fa fa-eercast",
        Type = EnumHelper.SourceType.Table
)
public class EpisodioSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.firma",
            LongName = "Fecha de la firma",
            Description = "Fecha de la firma",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            //IsRequired = true,
            IsVisible = false
    )
    private Date fecha_firma;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.inicio",
            LongName = "Fecha inicio",
            Description = "Fecha inicio del episodio",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = true,
            IsVisible = false
    )
    private Date fecha_inicio;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.alta",
            LongName = "Fecha del alta",
            Description = "Fecha alta del episodio",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = false
    )
    private Date fecha_alta;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Importe",
            LongName = "Importe",
            Description = "Importe del episodio",
            Type = EnumHelper.FieldType.Decimal
    // RegexHelp = "un numero correcto",
    //IsRequired = true
    )
    private Double importe;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Fin.",
            LongName = "Finalizado",
            Description = "Episodio finalizado",
            Type = EnumHelper.FieldType.Boolean,
            //IsRequired = true
            IsVisible = false
    )
    private Integer finalizado;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Bloq.",
            LongName = "Bloqueado",
            Description = "Episodio bloqueado",
            Type = EnumHelper.FieldType.Boolean,
            IsRequired = true,
            IsVisible = false
    )
    private Integer bloqueado;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_servicio = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Serv",
            LongName = "Servicio episodio",
            Description = "Servicio del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "servicio",
            Width = 4
    )
    private MetaBeanHelper obj_servicio = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_paciente = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Paci.",
            LongName = "Paciente",
            Description = "Paciente del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "paciente",
            Width = 4
    )
    private MetaBeanHelper obj_paciente = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_factura = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Fact.",
            LongName = "Factura",
            Description = "Factura del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "factura",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_factura = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_dependencia = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Dep.",
            LongName = "Dependencia",
            Description = "Dependencia del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "dependencia",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_dependencia = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_medico = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Med.",
            LongName = "Médico",
            Description = "Médico del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "medico",
            Width = 4
    )
    private MetaBeanHelper obj_medico = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_tipoepisodio = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Tipo Ep.",
            LongName = "Tipo episodio",
            Description = "Tipo del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "tipoepisodio",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_tipoepisodio = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_episodio = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Ep.",
            LongName = "Episodio",
            Description = "Episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "episodio",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_episodio = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_circunstanciasalta = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Circ.",
            LongName = "Circunstancias alta",
            Description = "Circunstancias de alta del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "circunstanciasalta",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_circunstanciasalta = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_destinoalta = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Des Alt.",
            LongName = "Destino Alta",
            Description = "Destino alta del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "destinoalta",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_destinoalta = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_modalidadepisodio = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Mod Epi.",
            LongName = "Modalidad episodio",
            Description = "Modalidad del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "modalidadepisodio",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_modalidadepisodio = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_usuario = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Usu.",
            LongName = "Usuario",
            Description = "Usuario del episodio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "usuario",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_usuario = null;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Mot Ingr.",
            LongName = "Motivo Ingreso",
            Description = "Motivo del ingreso",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100
    )
    private String motivo_ingreso;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Antec.",
            LongName = "Antecedentes",
            Description = "Antecedentes",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String antecedentes;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Hist. Act..",
            LongName = "Historia Actual",
            Description = "Historia Actual",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String historia_actual;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Exp Fis.",
            LongName = "Exploracion Fisica",
            Description = "Exploración Física",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String exploracion_fisica;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Evo Com.",
            LongName = "Evolución Comentario",
            Description = "Evolución Comentario",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String evolucion_comentarios;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Diag Pr.",
            LongName = "Diagnóstico Principal",
            Description = "Diasnóstico Principal",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true,
            Width = 3,
            MaxLength = 100
    )
    private String diagnostico_principal;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Diag Sec.",
            LongName = "Diagnóstico Secundario",
            Description = "Diagnóstico Secundario",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String diagnostico_secundarios;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Proc.",
            LongName = "Procedimientos",
            Description = "Procedimientos",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String procedimientos;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tra reco.",
            LongName = "Tratamiento recomendaciones",
            Description = "Recomendaciones del tratamiento",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100
    )
    private String tratamiento_recomendaciones;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tra far.",
            LongName = "Tratamiento farmacos",
            Description = "Farmacos del tratamiento",
            Type = EnumHelper.FieldType.String,
            //IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String tratamiento_farmacos;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Rec.",
            LongName = "Recomendaciones",
            Description = "Recomendaciones",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String recomendaciones;

//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Episodio del procedimiento",
//            LongName = "Episodio del procedimiento",
//            Description = "Episodio del procedimiento",
//            Type = EnumHelper.FieldType.Link,
//            References = "procedimiento"
//    )
//    private Integer link_procedimiento = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Episodio de la analitica",
//            LongName = "Episodio de la analitica",
//            Description = "Episodio de la analitica",
//            Type = EnumHelper.FieldType.Link,
//            References = "analitica"
//    )
//    private Integer link_analitica = null;
    //    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Episodio de la prueba informada",
//            LongName = "Episodio de la prueba informada",
//            Description = "Episodio de la prueba informada",
//            Type = EnumHelper.FieldType.Link,
//            References = "prueba_informada"
//    )
//    private Integer link_prueba_informada = null;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Subepisodios",
            LongName = "Subepisodios del episodio",
            Description = "Subepisodios del episodio",
            Type = EnumHelper.FieldType.Link,
            References = "episodio"
    )
    private Integer link_episodio = null;

    //    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Episodio del tratamiento",
//            LongName = "Episodio del tratamiento",
//            Description = "Episodio del tratamiento",
//            Type = EnumHelper.FieldType.Link,
//            References = "tratamiento"
//    )
//    private Integer link_tratamiento = null;
    //    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Episodio del episodio diagnóstico",
//            LongName = "Episodio del episodio diagnóstico",
//            Description = "Episodio del episodio diagnóstico",
//            Type = EnumHelper.FieldType.Link,
//            References = "episodiodiagnostico"
//    )
//    private Integer link_episodiodiagnostico = null;
    public EpisodioSpecificBeanImplementation() {
    }

    public EpisodioSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public Date getFecha_firma() {
        return fecha_firma;
    }

    public void setFecha_firma(Date fecha_firma) {
        this.fecha_firma = fecha_firma;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Integer getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Integer finalizado) {
        this.finalizado = finalizado;
    }

    public Integer getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(Integer bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String antecedentes) {
        this.antecedentes = antecedentes;
    }

    public String getProcedimientos() {
        return procedimientos;
    }

    public void setProcedimientos(String procedimientos) {
        this.procedimientos = procedimientos;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Integer getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(Integer id_paciente) {
        this.id_paciente = id_paciente;
    }

    public Integer getId_factura() {
        return id_factura;
    }

    public void setId_factura(Integer id_factura) {
        this.id_factura = id_factura;
    }

    public Integer getId_dependencia() {
        return id_dependencia;
    }

    public void setId_dependencia(Integer id_dependencia) {
        this.id_dependencia = id_dependencia;
    }

    public Integer getId_medico() {
        return id_medico;
    }

    public void setId_medico(Integer id_medico) {
        this.id_medico = id_medico;
    }

    public Integer getId_tipoepisodio() {
        return id_tipoepisodio;
    }

    public void setId_tipoepisodio(Integer id_tipoepisodio) {
        this.id_tipoepisodio = id_tipoepisodio;
    }

    public Integer getId_episodio() {
        return id_episodio;
    }

    public void setId_episodio(Integer id_episodio) {
        this.id_episodio = id_episodio;
    }

    public Integer getId_circunstanciasalta() {
        return id_circunstanciasalta;
    }

    public void setId_circunstanciasalta(Integer id_circunstanciasalta) {
        this.id_circunstanciasalta = id_circunstanciasalta;
    }

    public Integer getId_destinoalta() {
        return id_destinoalta;
    }

    public void setId_destinoalta(Integer id_destinoalta) {
        this.id_destinoalta = id_destinoalta;
    }

    public Integer getId_modalidadepisodio() {
        return id_modalidadepisodio;
    }

    public void setId_modalidadepisodio(Integer id_modalidadepisodio) {
        this.id_modalidadepisodio = id_modalidadepisodio;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getMotivo_ingreso() {
        return motivo_ingreso;
    }

    public void setMotivo_ingreso(String motivo_ingreso) {
        this.motivo_ingreso = motivo_ingreso;
    }

    public String getHistoria_actual() {
        return historia_actual;
    }

    public void setHistoria_actual(String historia_actual) {
        this.historia_actual = historia_actual;
    }

    public String getExploracion_fisica() {
        return exploracion_fisica;
    }

    public void setExploracion_fisica(String exploracion_fisica) {
        this.exploracion_fisica = exploracion_fisica;
    }

    public String getEvolucion_comentarios() {
        return evolucion_comentarios;
    }

    public void setEvolucion_comentarios(String evolucion_comentarios) {
        this.evolucion_comentarios = evolucion_comentarios;
    }

    public String getDiagnostico_principal() {
        return diagnostico_principal;
    }

    public void setDiagnostico_principal(String diagnostico_principal) {
        this.diagnostico_principal = diagnostico_principal;
    }

    public String getDiagnostico_secundarios() {
        return diagnostico_secundarios;
    }

    public void setDiagnostico_secundarios(String diagnostico_secundarios) {
        this.diagnostico_secundarios = diagnostico_secundarios;
    }

    public String getTratamiento_recomendaciones() {
        return tratamiento_recomendaciones;
    }

    public void setTratamiento_recomendaciones(String tratamiento_recomendaciones) {
        this.tratamiento_recomendaciones = tratamiento_recomendaciones;
    }

    public String getTratamiento_farmacos() {
        return tratamiento_farmacos;
    }

    public void setTratamiento_farmacos(String tratamiento_farmacos) {
        this.tratamiento_farmacos = tratamiento_farmacos;
    }

    public MetaBeanHelper getObj_servicio() {
        return obj_servicio;
    }

    public void setObj_servicio(MetaBeanHelper obj_servicio) {
        this.obj_servicio = obj_servicio;
    }

    public MetaBeanHelper getObj_paciente() {
        return obj_paciente;
    }

    public void setObj_paciente(MetaBeanHelper obj_paciente) {
        this.obj_paciente = obj_paciente;
    }

    public MetaBeanHelper getObj_factura() {
        return obj_factura;
    }

    public void setObj_factura(MetaBeanHelper obj_factura) {
        this.obj_factura = obj_factura;
    }

    public MetaBeanHelper getObj_dependencia() {
        return obj_dependencia;
    }

    public void setObj_dependencia(MetaBeanHelper obj_dependencia) {
        this.obj_dependencia = obj_dependencia;
    }

    public MetaBeanHelper getObj_medico() {
        return obj_medico;
    }

    public void setObj_medico(MetaBeanHelper obj_medico) {
        this.obj_medico = obj_medico;
    }

    public MetaBeanHelper getObj_tipoepisodio() {
        return obj_tipoepisodio;
    }

    public void setObj_tipoepisodio(MetaBeanHelper obj_tipoepisodio) {
        this.obj_tipoepisodio = obj_tipoepisodio;
    }

    public MetaBeanHelper getObj_episodio() {
        return obj_episodio;
    }

    public void setObj_episodio(MetaBeanHelper obj_episodio) {
        this.obj_episodio = obj_episodio;
    }

    public MetaBeanHelper getObj_circunstanciasalta() {
        return obj_circunstanciasalta;
    }

    public void setObj_circunstanciasalta(MetaBeanHelper obj_circunstanciasalta) {
        this.obj_circunstanciasalta = obj_circunstanciasalta;
    }

    public MetaBeanHelper getObj_destinoalta() {
        return obj_destinoalta;
    }

    public void setObj_destinoalta(MetaBeanHelper obj_destinoalta) {
        this.obj_destinoalta = obj_destinoalta;
    }

    public MetaBeanHelper getObj_modalidadepisodio() {
        return obj_modalidadepisodio;
    }

    public void setObj_modalidadepisodio(MetaBeanHelper obj_modalidadepisodio) {
        this.obj_modalidadepisodio = obj_modalidadepisodio;
    }

    public MetaBeanHelper getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(MetaBeanHelper obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

}
