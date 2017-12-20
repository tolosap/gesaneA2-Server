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

@MetaObjectBeanInterface(
        TableName = "servicio",
        SingularDescription = "Servicio",
        PluralDescription = "Servicios",
        Icon = "fa fa-strikethrough",
        Type = EnumHelper.SourceType.Table
)
public class ServicioSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Serv.",
            LongName = "Servicio",
            Description = "Servicios del hospital",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cod.",
            LongName = "Código",
            Description = "Código del servicio",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = "[^a-z0-9-]",
            RegexHelp = "letras mayúsculas y números",
            IsForeignKeyDescriptor = true,
            MaxLength = 50
    )
    private String codigo;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_tiposervicio = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Tipo",
            LongName = "Tipo servicio",
            Description = "Tipo de servicio",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "tiposervicio",
            Width = 4
    )
    private MetaBeanHelper obj_tiposervicio = null;

    //    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es servicio de los episodios",
//            LongName = "Es servicio de los episodios",
//            Description = "Es servicio de los episodios",
//            Type = EnumHelper.FieldType.Link,
//            References = "episodio"
//    )
//    private Integer link_episodio = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es servicio de los medicos",
//            LongName = "Es servicio de los medicos",
//            Description = "Es servicio de los medicos",
//            Type = EnumHelper.FieldType.Link,
//            References = "medico"
//    )
//    private Integer link_medico = null;
    public ServicioSpecificBeanImplementation() {
    }

    public ServicioSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public Integer getId_tiposervicio() {
        return id_tiposervicio;
    }

    public void setId_tiposervicio(Integer id_tiposervicio) {
        this.id_tiposervicio = id_tiposervicio;
    }

    public MetaBeanHelper getObj_tiposervicio() {
        return obj_tiposervicio;
    }

    public void setObj_tiposervicio(MetaBeanHelper obj_tiposervicio) {
        this.obj_tiposervicio = obj_tiposervicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
