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
        TableName = "medico",
        SingularDescription = "Medico",
        PluralDescription = "Medicos",
        Icon = "fa fa-user-md",
        Type = EnumHelper.SourceType.Table
)
public class MedicoSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_servicio = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "serv.",
            LongName = "Servicio",
            Description = "Servicio del médico",
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
    private Integer id_especialidad = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Esp.",
            LongName = "Especialidad",
            Description = "Especialidad del médico",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "especialidad",
            Width = 4
    )
    private MetaBeanHelper obj_especialidad = null;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Dni.",
            LongName = "Dni",
            Description = "Dni del médico",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.dni,
            RegexHelp = RegexConstants.dni_Help,
            IsForeignKeyDescriptor = false,
            MaxLength = 50
    )
    private String dni;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Nombre completo",
            LongName = "Nombre completo",
            Description = "Nombre completo del médico",
            Type = EnumHelper.FieldType.Calculated,
            IsForeignKeyDescriptor = true,
            Width = 3,
            MaxLength = 100
    )
    private String nombrecompleto;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Nombre",
            LongName = "Nombre",
            Description = "Nombre del medico",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String nombre;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "1er. Ap.",
            LongName = "Primer Apellido",
            Description = "Primer Apellido del medico",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String primer_apellido;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "2º Ap.",
            LongName = "Segundo Apellido",
            Description = "Segundo Apellido del medico",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String segundo_apellido;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.Na",
            LongName = "Fecha de nacimiento",
            Description = "Fecha de nacimiento del medico",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = true
    )
    private Date fecha_nacimiento;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Nº Col.",
            LongName = "Número colegiado",
            Description = "Número colegiado del medico",
            Type = EnumHelper.FieldType.String,
            IsRequired = true,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String num_colegiado;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "email",
            LongName = "Correo electrónico",
            Description = "Correo electrónico del médico",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.email,
            RegexHelp = RegexConstants.email_Help,
            MaxLength = 50,
            IsVisible = false
    )
    private String email;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_categoriaprofesional = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Cat.",
            LongName = "Categoria profesional",
            Description = "Categoria profesional del médico",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "categoriaprofesional",
            Width = 4
    )
    private MetaBeanHelper obj_categoriaprofesional = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_centrosanitario = 0;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Centr.",
            LongName = "Centro Sanitario",
            Description = "Centro sanitario del médico",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "centrosanitario",
            Width = 4
    )
    private MetaBeanHelper obj_centrosanitario = null;

//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Procedimientos del medico",
//            LongName = "Procedimientos del medico",
//            Description = "Procedimientos del medico",
//            Type = EnumHelper.FieldType.Link,
//            References = "procedimiento"
//    )
//    private Integer link_procedimiento = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Médico que hace la analítica",
//            LongName = "Médico que hace la analítica",
//            Description = "Médico que hace la analítica",
//            Type = EnumHelper.FieldType.Link,
//            References = "analitica"
//    )
//    private Integer link_analitica = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Episodio del médico",
//            LongName = "Episodio del médico",
//            Description = "Episodio del médico",
//            Type = EnumHelper.FieldType.Link,
//            References = "episodio"
//    )
//    private Integer link_episodio = null;
//    @Expose(deserialize = false)
//    @MetaPropertyBeanInterface(
//            ShortName = "Prueba informada del médico",
//            LongName = "Prueba informada del médico",
//            Description = "Prueba informada del médico",
//            Type = EnumHelper.FieldType.Link,
//            References = "prueba_informada"
//    )
//    private Integer link_prueba_informada = null;
    public MedicoSpecificBeanImplementation() {
    }

    public MedicoSpecificBeanImplementation(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(Integer id_servicio) {
        this.id_servicio = id_servicio;
    }

    public Integer getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(Integer id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getNum_colegiado() {
        return num_colegiado;
    }

    public void setNum_colegiado(String num_colegiado) {
        this.num_colegiado = num_colegiado;
    }

    public Integer getId_categoriaprofesional() {
        return id_categoriaprofesional;
    }

    public void setId_categoriaprofesional(Integer id_categoriaprofesional) {
        this.id_categoriaprofesional = id_categoriaprofesional;
    }

    public Integer getId_centrosanitario() {
        return id_centrosanitario;
    }

    public void setId_centrosanitario(Integer id_centrosanitario) {
        this.id_centrosanitario = id_centrosanitario;
    }

    public MetaBeanHelper getObj_servicio() {
        return obj_servicio;
    }

    public void setObj_servicio(MetaBeanHelper obj_servicio) {
        this.obj_servicio = obj_servicio;
    }

    public MetaBeanHelper getObj_especialidad() {
        return obj_especialidad;
    }

    public void setObj_especialidad(MetaBeanHelper obj_especialidad) {
        this.obj_especialidad = obj_especialidad;
    }

    public MetaBeanHelper getObj_categoriaprofesional() {
        return obj_categoriaprofesional;
    }

    public void setObj_categoriaprofesional(MetaBeanHelper obj_categoriaprofesional) {
        this.obj_categoriaprofesional = obj_categoriaprofesional;
    }

    public MetaBeanHelper getObj_centrosanitario() {
        return obj_centrosanitario;
    }

    public void setObj_centrosanitario(MetaBeanHelper obj_centrosanitario) {
        this.obj_centrosanitario = obj_centrosanitario;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

     @Override
    public void ComputeCalculatedFields() {
        this.nombrecompleto = this.nombre + " " + this.primer_apellido + " " + this.segundo_apellido;
    }
    
}
