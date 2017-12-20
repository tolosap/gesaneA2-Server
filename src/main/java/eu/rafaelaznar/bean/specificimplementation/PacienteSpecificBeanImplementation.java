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

@MetaObjectBeanInterface(
        TableName = "paciente",
        SingularDescription = "Paciente",
        PluralDescription = "Pacientes",
        Icon = "fa fa-address-card-o",
        Type = EnumHelper.SourceType.Table
)
public class PacienteSpecificBeanImplementation extends TableGenericBeanImplementation {

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Nombre completo",
            LongName = "Nombre completo",
            Description = "Nombre completo del usuario",
            Type = EnumHelper.FieldType.Calculated,
            IsForeignKeyDescriptor = true,
            Width = 3,
            MaxLength = 100
    )
    private String nombrecompleto;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Dni",
            LongName = "Dni Completo",
            Description = "Documento Nacional de Identidad",
            Type = EnumHelper.FieldType.String,
            IsForeignKeyDescriptor = true,
            Width = 3,
            MaxLength = 100
    )
    private String dni;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Nombre",
            LongName = "Nombre",
            Description = "Nombre del usuario",
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
            Description = "Primer Apellido del usuario",
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
            Description = "Segundo Apellido del usuario",
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
            ShortName = "Dir.",
            LongName = "Dirección",
            Description = "Dirección del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.direction,
            RegexHelp = RegexConstants.directionSentence_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String direccion;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Ciudad",
            LongName = "Ciudad",
            Description = "Ciudad del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String ciudad;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cod. Postal",
            LongName = "Código Postal",
            Description = "Código Postal del paciente",
            Type = EnumHelper.FieldType.Integer,
            RegexPattern = "[0-9]{5,5}",
            RegexHelp = "5 dígitos",
            IsRequired = false,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 5,
            IsVisible = false
    )
    private String codigo_postal;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Provincia",
            LongName = "Provincia",
            Description = "Provincia del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String provincia;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "País",
            LongName = "País",
            Description = "País del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String pais;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "email",
            LongName = "Correo electrónico",
            Description = "Correo electrónico del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.email,
            RegexHelp = RegexConstants.email_Help,
            MaxLength = 50,
            IsVisible = false
    )
    private String email;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tel.1",
            LongName = "Teléfono 1",
            Description = "Primer teléfono del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 20,
            IsVisible = false
    )
    private String telefono1;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Tel.2",
            LongName = "Teléfono 2",
            Description = "Segundo teléfono del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 20,
            IsVisible = false
    )
    private String telefono2;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "N. Padre",
            LongName = "Nombre Padre",
            Description = "Nombre del padre del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String nombre_padre;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "N. Madre",
            LongName = "Nombre Madre",
            Description = "Nombre del madre del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String nombre_madre;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "F.Na",
            LongName = "Fecha de nacimiento",
            Description = "Fecha de nacimiento del paciente",
            Type = EnumHelper.FieldType.Date,
            RegexHelp = "una fecha correcta",
            IsRequired = true,
            IsVisible = false
    )
    private Date fecha_nacimiento;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Cdad. Na.",
            LongName = "Ciudad de nacimiento",
            Description = "Ciudad de nacimiento del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String ciudad_nacimiento;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "País Na.",
            LongName = "País de Nacimiento",
            Description = "País de nacimiento del paciente",
            Type = EnumHelper.FieldType.String,
            IsRequired = false,
            RegexPattern = RegexConstants.capitalizedName,
            RegexHelp = RegexConstants.capitalizedName_Help,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String pais_nacimiento;

    @Expose
    @MetaPropertyBeanInterface(
            ShortName = "Sip",
            LongName = "Sip aseguradora",
            Description = "Sip del paciente",
            Type = EnumHelper.FieldType.Integer,
            RegexPattern = "[0-9]{10,10}",
            RegexHelp = "10 dígitos",
            IsRequired = true,
            IsForeignKeyDescriptor = false,
            Width = 3,
            MaxLength = 100,
            IsVisible = false
    )
    private String sip_aseguradora;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_tipopago = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Tipo",
            LongName = "Tipo pago",
            Description = "Tipo de pago",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "tipopago",
            Width = 4,
            IsVisible = false
    )
    private MetaBeanHelper obj_tipopago = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_sexo = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Sexo",
            LongName = "Sexo del paciente",
            Description = "Sexo del paciente",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "sexo",
            Width = 4
    )
    private MetaBeanHelper obj_sexo = null;

    @Expose(serialize = false)
    @MetaPropertyBeanInterface(
            Type = EnumHelper.FieldType.ForeignId
    )
    private Integer id_usuario = 0;
    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Usuario asociado",
            LongName = "Usuario asociado",
            Description = "Usuario del paciente",
            Type = EnumHelper.FieldType.ForeignObject,
            IsRequired = true,
            References = "usuario",
            Width = 4
    )
    private MetaBeanHelper obj_usuario = null;

    @Expose(deserialize = false)
    @MetaPropertyBeanInterface(
            ShortName = "Episodios",
            LongName = "Episodios del paciente",
            Description = "Episodios del paciente",
            Type = EnumHelper.FieldType.Link,
            References = "episodio"
    )
    private Integer link_episodio = null;

    @Override
    public void ComputeCalculatedFields() {
        this.nombrecompleto = this.nombre + " " + this.primer_apellido + " " + this.segundo_apellido;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getNombre_padre() {
        return nombre_padre;
    }

    public void setNombre_padre(String nombre_padre) {
        this.nombre_padre = nombre_padre;
    }

    public String getNombre_madre() {
        return nombre_madre;
    }

    public void setNombre_madre(String nombre_madre) {
        this.nombre_madre = nombre_madre;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getCiudad_nacimiento() {
        return ciudad_nacimiento;
    }

    public void setCiudad_nacimiento(String ciudad_nacimiento) {
        this.ciudad_nacimiento = ciudad_nacimiento;
    }

    public String getPais_nacimiento() {
        return pais_nacimiento;
    }

    public void setPais_nacimiento(String pais_nacimiento) {
        this.pais_nacimiento = pais_nacimiento;
    }

    public String getSip_aseguradora() {
        return sip_aseguradora;
    }

    public void setSip_aseguradora(String sip_aseguradora) {
        this.sip_aseguradora = sip_aseguradora;
    }

    public Integer getId_tipopago() {
        return id_tipopago;
    }

    public void setId_tipopago(Integer id_tipopago) {
        this.id_tipopago = id_tipopago;
    }

    public MetaBeanHelper getObj_tipopago() {
        return obj_tipopago;
    }

    public void setObj_tipopago(MetaBeanHelper obj_tipopago) {
        this.obj_tipopago = obj_tipopago;
    }

    public Integer getId_sexo() {
        return id_sexo;
    }

    public void setId_sexo(Integer id_sexo) {
        this.id_sexo = id_sexo;
    }

    public MetaBeanHelper getObj_sexo() {
        return obj_sexo;
    }

    public void setObj_sexo(MetaBeanHelper obj_sexo) {
        this.obj_sexo = obj_sexo;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public MetaBeanHelper getObj_usuario() {
        return obj_usuario;
    }

    public void setObj_usuario(MetaBeanHelper obj_usuario) {
        this.obj_usuario = obj_usuario;
    }

}
