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

/**
 *
 * @author a022583952e
 */
@MetaObjectBeanInterface(
        TableName = "dependencia",
        SingularDescription = "Dependencia",
        PluralDescription = "Dependencias",
        Icon = "fa fa-square",
        Type = EnumHelper.SourceType.Table
)
public class DependenciaSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cod.",
            LongName = "Código",
            Description = "Código de la dependencia",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = "[^a-z0-9-]",
            RegexHelp = "letras mayúsculas y números",
            IsForeignKeyDescriptor = true,
            MaxLength = 50
    )
    private String codigo;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Dep.",
            LongName = "Dependencia",
            Description = "Dependencias del hospital",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedSentence,
            RegexHelp = RegexConstants.capitalizedSentence_Help,
            IsForeignKeyDescriptor = true
    )
    private String descripcion = "";

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_tipodependencia = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Tipo",
            LongName = "Tipo dependencia",
            Description = "Tipo de dependencia",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "tipodependencia",
            Width = 4
    )
    private MetaBeanHelper obj_tipodependencia = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_centrosanitario = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Cent.",
            LongName = "Centro sanitario",
            Description = "Centro sanitario",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "centrosanitario",
            Width = 4
    )
    private MetaBeanHelper obj_centrosanitario = null;

//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es dependencia de los procedimientos",
//            LongName = "Es dependencia de los procedimientos",
//            Description = "Es dependencia de los procedimientos",
//            Type = EnumHelper.FieldType.Link,
//            References = "procedimiento"
//    )
//    private Integer link_procedimiento = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es dependencia de la analitica",
//            LongName = "Es dependencia de la analitica",
//            Description = "Es procedimiento de la analitica",
//            Type = EnumHelper.FieldType.Link,
//            References = "analitica"
//    )
//    private Integer link_analitica = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es dependencia de la imagen",
//            LongName = "Es dependencia de la imagen",
//            Description = "Es dependencia de la imagen",
//            Type = EnumHelper.FieldType.Link,
//            References = "imagen"
//    )
//    private Integer link_imagen = null;  
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Es dependencia del episodio",
//            LongName = "Es dependencia del episodio",
//            Description = "Es dependencia del episodio",
//            Type = EnumHelper.FieldType.Link,
//            References = "episodio"
//    )
//    private Integer link_episodio = null;
    public DependenciaSpecificBeanImplementation() {
    }

    public DependenciaSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_tipodependencia() {
        return id_tipodependencia;
    }

    public void setId_tipodependencia(Integer id_tipodependencia) {
        this.id_tipodependencia = id_tipodependencia;
    }

    public Integer getId_centrosanitario() {
        return id_centrosanitario;
    }

    public void setId_centrosanitario(Integer id_centrosanitario) {
        this.id_centrosanitario = id_centrosanitario;
    }

    public MetaBeanHelper getObj_tipodependencia() {
        return obj_tipodependencia;
    }

    public void setObj_tipodependencia(MetaBeanHelper obj_tipodependencia) {
        this.obj_tipodependencia = obj_tipodependencia;
    }

    public MetaBeanHelper getObj_centrosanitario() {
        return obj_centrosanitario;
    }

    public void setObj_centrosanitario(MetaBeanHelper obj_centrosanitario) {
        this.obj_centrosanitario = obj_centrosanitario;
    }

}
